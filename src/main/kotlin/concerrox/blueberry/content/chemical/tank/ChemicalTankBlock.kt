package concerrox.blueberry.content.chemical.tank

import com.simibubi.create.api.connectivity.ConnectivityHandler
import com.simibubi.create.content.equipment.wrench.IWrenchable
import com.simibubi.create.content.fluids.tankf.ChemicalTankBlockEntity
import com.simibubi.create.content.fluids.tankf.CreativeFluidTankBlockEntity.CreativeBasicChemicalTank
import com.simibubi.create.content.fluids.transfer.GenericItemEmptying
import com.simibubi.create.content.fluids.transfer.GenericItemFilling
import com.simibubi.create.foundation.block.IBE
import com.simibubi.create.foundation.blockEntity.ComparatorUtil
import com.simibubi.create.foundation.fluid.FluidHelper
import com.simibubi.create.foundation.fluid.FluidHelper.FluidExchange
import concerrox.blueberry.registry.ModBlockEntityTypes
import mekanism.api.chemical.ChemicalStack
import net.createmod.catnip.lang.Lang
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.common.util.DeferredSoundType
import net.neoforged.neoforge.fluids.FluidStack


class ChemicalTankBlock(properties: Properties, private val isCreative: Boolean) : Block(properties), IWrenchable,
    IBE<ChemicalTankBlockEntity> {

    companion object {
        @JvmField
        val TOP: BooleanProperty = BooleanProperty.create("top")

        @JvmField
        val BOTTOM: BooleanProperty = BooleanProperty.create("bottom")

        @JvmField
        val SHAPE: EnumProperty<Shape> = EnumProperty.create("shape", Shape::class.java)

        val SILENCED_METAL: SoundType = DeferredSoundType(
            0.1f,
            1.5f,
            { SoundEvents.METAL_BREAK },
            { SoundEvents.METAL_STEP },
            { SoundEvents.METAL_PLACE },
            { SoundEvents.METAL_HIT },
            { SoundEvents.METAL_FALL })

        @JvmStatic
        fun isTank(state: BlockState) = state.block is ChemicalTankBlock
    }

    init {
        registerDefaultState(
            defaultBlockState().setValue(TOP, true).setValue(BOTTOM, true).setValue(SHAPE, Shape.WINDOW)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(TOP, BOTTOM, SHAPE);
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, movedByPiston: Boolean) {
        if (oldState.block === state.block) return
        if (movedByPiston) return
        withBlockEntityDo(level, pos, ChemicalTankBlockEntity::updateConnectivity)
    }

    override fun getBlockEntityClass() = ChemicalTankBlockEntity::class.java

    override fun getBlockEntityType(): BlockEntityType<out ChemicalTankBlockEntity> =
        if (isCreative) ModBlockEntityTypes.CREATIVE_FLUID_TANK.get() else ModBlockEntityTypes.FLUID_TANK.get()

    override fun getLightEmission(state: BlockState, level: BlockGetter, pos: BlockPos): Int {
        val tankAt = ConnectivityHandler.partAt<ChemicalTankBlockEntity>(blockEntityType, level, pos)
        if (tankAt == null || !tankAt.hasLevel()) return 0
        val controllerBE = tankAt.controllerBE
        if (controllerBE == null || !controllerBE.window) return 0
        return tankAt.luminosity
    }

    override fun onWrenched(state: BlockState, context: UseOnContext): InteractionResult {
        withBlockEntityDo(context.level, context.clickedPos, ChemicalTankBlockEntity::toggleWindows)
        return InteractionResult.SUCCESS
    }

    public override fun getCollisionShape(
        pState: BlockState, pLevel: BlockGetter?, pPos: BlockPos?,
        pContext: CollisionContext
    ): VoxelShape {
        if (pContext === CollisionContext.empty()) return Block.box(0.0, 4.0, 0.0, 16.0, 16.0, 16.0);
        return pState.getShape(pLevel, pPos)
    }

    public override fun getBlockSupportShape(pState: BlockState?, pReader: BlockGetter?, pPos: BlockPos?): VoxelShape {
        return Shapes.block()
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    public override fun updateShape(
        pState: BlockState, pDirection: Direction, pNeighborState: BlockState,
        pLevel: LevelAccessor?, pCurrentPos: BlockPos?, pNeighborPos: BlockPos?
    ): BlockState {
//        if (pDirection == Direction.DOWN && pNeighborState.block !== this) withBlockEntityDo(
//            pLevel, pCurrentPos
//        ) { obj: ChemicalTankBlockEntity -> obj.updateBoilerTemperature() }
        return pState
    }


    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        val isClientSide = level.isClientSide
        if (stack.isEmpty) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
        if (!player.isCreative && !isCreative) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION

        var exchange: FluidExchange? = null
        val blockEntity = ConnectivityHandler.partAt<ChemicalTankBlockEntity>(blockEntityType, level, pos)
            ?: return ItemInteractionResult.FAIL
        // TODO: fix
        val tankCapability = level.getCapability(
            Capabilities.FluidHandler.BLOCK, blockEntity.blockPos, Direction.DOWN
        ) ?: return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
        val prevFluidInTank = tankCapability.getFluidInTank(0).copy()

        if (FluidHelper.tryEmptyItemIntoBE(level, player, hand, stack, blockEntity)) {
            exchange = FluidExchange.ITEM_TO_TANK
        } else if (FluidHelper.tryFillItemFromBE(level, player, hand, stack, blockEntity)) {
            exchange = FluidExchange.TANK_TO_ITEM
        }
        if (exchange == null) {
            if (GenericItemEmptying.canItemBeEmptied(level, stack) || GenericItemFilling.canItemBeFilled(
                    level, stack
                )
            ) return ItemInteractionResult.SUCCESS
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
        }

        var soundevent: SoundEvent? = null
        var fluidState: BlockState? = null
        val fluidInTank = tankCapability.getFluidInTank(0)

        if (exchange == FluidExchange.ITEM_TO_TANK) {
            if (isCreative && !isClientSide) {
//                val fluidInItem = GenericItemEmptying.emptyItem(level, stack, true).first
//                if (!fluidInItem.isEmpty && tankCapability is CreativeBasicChemicalTank) tankCapability.setContainedFluid(
//                    fluidInItem
//                )
            }

            val fluid = fluidInTank.fluid
            fluidState = fluid.defaultFluidState().createLegacyBlock()
            soundevent = FluidHelper.getEmptySound(fluidInTank)
        }

        if (exchange == FluidExchange.TANK_TO_ITEM) {
            if (isCreative && !isClientSide) if (tankCapability is CreativeBasicChemicalTank) tankCapability.setContainedFluid(
                ChemicalStack.EMPTY
            )

            val fluid = prevFluidInTank.fluid
            fluidState = fluid.defaultFluidState().createLegacyBlock()
            soundevent = FluidHelper.getFillSound(prevFluidInTank)
        }

        if (soundevent != null && !isClientSide) {
            var pitch =
                Mth.clamp(1 - (1f * fluidInTank.amount / (ChemicalTankBlockEntity.getCapacityMultiplier() * 16)), 0f, 1f)
            pitch /= 1.5f
            pitch += .5f
            pitch += (level.random.nextFloat() - .5f) / 4f
            level.playSound(null, pos, soundevent, SoundSource.BLOCKS, .5f, pitch)
        }

        if (!FluidStack.isSameFluidSameComponents(fluidInTank, prevFluidInTank)) {
            if (blockEntity is ChemicalTankBlockEntity) {
                val controllerBE = blockEntity.controllerBE
                if (controllerBE != null) {
                    if (fluidState != null && isClientSide) {
                        val blockParticleData = BlockParticleOption(ParticleTypes.BLOCK, fluidState)
                        var fluidLevel = fluidInTank.amount.toFloat() / tankCapability.getTankCapacity(0)

                        val reversed = fluidInTank.fluid.fluidType.isLighterThanAir
                        if (reversed) fluidLevel = 1 - fluidLevel

                        var vec = hitResult.location
                        vec = Vec3(
                            vec.x,
                            (controllerBE.blockPos.y + fluidLevel * (controllerBE.height - .5f) + .25f).toDouble(),
                            vec.z
                        )
                        val motion = player.position().subtract(vec).scale((1 / 20f).toDouble())
                        vec = vec.add(motion)
                        level.addParticle(blockParticleData, vec.x, vec.y, vec.z, motion.x, motion.y, motion.z)
                        return ItemInteractionResult.SUCCESS
                    }

                    controllerBE.sendDataImmediately()
                    controllerBE.setChanged()
                }
            }
        }

        return ItemInteractionResult.SUCCESS
    }

    override fun onRemove(
        state: BlockState, level: Level, pos: BlockPos, newState: BlockState, movedByPiston: Boolean
    ) {
        if (state.hasBlockEntity() && (state.block !== newState.block || !newState.hasBlockEntity())) {
            val blockEntity = level.getBlockEntity(pos) as? ChemicalTankBlockEntity
                ?: return
            level.removeBlockEntity(pos)
            ConnectivityHandler.splitMulti(blockEntity)
        }
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        if (mirror == Mirror.NONE) return state
        val x = mirror == Mirror.FRONT_BACK
        val shape = when (state.getValue(SHAPE)) {
            Shape.WINDOW_NE -> if (x) Shape.WINDOW_NW else Shape.WINDOW_SE
            Shape.WINDOW_NW -> if (x) Shape.WINDOW_NE else Shape.WINDOW_SW
            Shape.WINDOW_SE -> if (x) Shape.WINDOW_SW else Shape.WINDOW_NE
            Shape.WINDOW_SW -> if (x) Shape.WINDOW_SE else Shape.WINDOW_NW
            else -> return state
        }
        return state.setValue(SHAPE, shape)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        var newState = state
        for (i in 0..<rotation.ordinal) newState = rotateOnce(state)
        return newState
    }

    private fun rotateOnce(state: BlockState): BlockState {
        val shape = when (state.getValue(SHAPE)) {
            Shape.WINDOW_NE -> Shape.WINDOW_SE
            Shape.WINDOW_NW -> Shape.WINDOW_NE
            Shape.WINDOW_SE -> Shape.WINDOW_SW
            Shape.WINDOW_SW -> Shape.WINDOW_NW
            else -> return state
        }
        return state.setValue(SHAPE, shape)
    }

    override fun getSoundType(state: BlockState, level: LevelReader, pos: BlockPos, entity: Entity?): SoundType {
        return if (entity != null && entity.persistentData.contains("SilenceTankSound")) {
            SILENCED_METAL
        } else {
            super.getSoundType(state, level, pos, entity)
        }
    }

    override fun hasAnalogOutputSignal(state: BlockState) = true

    override fun getAnalogOutputSignal(state: BlockState, level: Level, pos: BlockPos): Int {
        return getBlockEntityOptional(level, pos).map { it.controllerBE }.map {
            ComparatorUtil.fractionToRedstoneLevel(it.fillState.toDouble())
        }.orElse(0)
    }

    enum class Shape : StringRepresentable {
        PLAIN, WINDOW, WINDOW_NW, WINDOW_SW, WINDOW_NE, WINDOW_SE;

        override fun getSerializedName(): String = Lang.asId(name)
    }


}