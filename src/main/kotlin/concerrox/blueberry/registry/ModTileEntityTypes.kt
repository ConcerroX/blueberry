package concerrox.blueberry.registry

import mekanism.api.functions.ConstantPredicates
import mekanism.api.providers.IBlockProvider
import mekanism.common.Mekanism
import mekanism.common.capabilities.Capabilities
import mekanism.common.integration.computer.ComputerCapabilityHelper
import mekanism.common.registration.impl.BlockRegistryObject
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister.BlockEntityTypeBuilder
import mekanism.common.registration.impl.TileEntityTypeRegistryObject
import mekanism.common.registries.MekanismTileEntityTypes
import mekanism.common.tile.base.CapabilityTileEntity
import mekanism.common.tile.transmitter.TileEntityMechanicalPipe
import mekanism.common.tile.transmitter.TileEntityTransmitter
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

object ModTileEntityTypes {

    val TILE_ENTITY_TYPES: TileEntityTypeDeferredRegister = TileEntityTypeDeferredRegister(Mekanism.MODID)

    val WINDOWED_COPPER_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_COPPER_PRESSURED_PIPE)

    val CAST_IRON_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.CAST_IRON_PRESSURED_PIPE)
    val WINDOWED_CAST_IRON_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_CAST_IRON_PRESSURED_PIPE)

    val ALUMINUM_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.ALUMINUM_PRESSURED_PIPE)
    val WINDOWED_ALUMINUM_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_ALUMINUM_PRESSURED_PIPE)

    val PLASTIC_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.PLASTIC_PRESSURED_PIPE)
    val WINDOWED_PLASTIC_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_PLASTIC_PRESSURED_PIPE)

    val WINDOWED_STEEL_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_STEEL_PRESSURED_PIPE)

    val BRASS_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.BRASS_PRESSURED_PIPE)
    val WINDOWED_BRASS_PRESSURED_PIPE: TileEntityTypeRegistryObject<TileEntityMechanicalPipe> = registerPipe(
        ModBlocks.WINDOWED_BRASS_PRESSURED_PIPE)

    private fun registerPipe(block: BlockRegistryObject<*, *>): TileEntityTypeRegistryObject<TileEntityMechanicalPipe> {
        val builder = transmitterBuilder(block) { blockProvider, pos, state ->
            TileEntityMechanicalPipe(blockProvider, pos, state)
        }.with(Capabilities.FLUID.block(), CapabilityTileEntity.FLUID_HANDLER_PROVIDER)
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE)
        }
        return builder.build()
    }

    private fun <BE : TileEntityTransmitter> transmitterBuilder(
        block: BlockRegistryObject<*, *>, factory: BlockEntityFactory<BE>
    ): BlockEntityTypeBuilder<BE> {
        return TILE_ENTITY_TYPES.builder(block) { pos, state ->
            factory.create(block, pos, state)
        }.serverTicker { level, pos, state, transmitter ->
            TileEntityTransmitter.tickServer(level, pos, state, transmitter)
        }.withSimple(Capabilities.ALLOY_INTERACTION)
            .with(Capabilities.CONFIGURABLE, TileEntityTransmitter.CONFIGURABLE_PROVIDER)
    }

    private fun interface BlockEntityFactory<BE : BlockEntity> {
        fun create(block: Holder<Block>, pos: BlockPos, state: BlockState): BE
    }

}