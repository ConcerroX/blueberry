package concerrox.blueberry.registry

import com.buuz135.functionalstorage.FunctionalStorage
import com.buuz135.functionalstorage.block.DrawerBlock
import com.buuz135.functionalstorage.util.DrawerWoodType
import com.hrznstudio.titanium.module.BlockWithTile
import com.hrznstudio.titanium.module.DeferredRegistryHelper
import com.simibubi.create.AllDisplaySources
import com.simibubi.create.api.behaviour.display.DisplaySource.displaySource
import com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour
import com.simibubi.create.content.fluids.tankf.FluidTankItem
import com.simibubi.create.content.fluids.tankf.FluidTankModel
import com.simibubi.create.content.fluids.tankf.FluidTankMovementBehavior
import com.simibubi.create.foundation.data.AssetLookup
import com.simibubi.create.foundation.data.CreateRegistrate
import com.simibubi.create.foundation.data.SharedProperties
import com.simibubi.create.foundation.data.TagGen.pickaxeOnly
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.entry.BlockEntry
import com.tterrag.registrate.util.nullness.NonNullFunction
import concerrox.blueberry.Blueberry.Companion.MOD_ID
import concerrox.blueberry.content.WallSignBlock2
import concerrox.blueberry.content.chemical.tank.ChemicalTankBlock
import concerrox.blueberry.util.new
import mekanism.common.block.transmitter.BlockLargeTransmitter
import mekanism.common.content.blocktype.BlockTypeTile
import mekanism.common.item.block.transmitter.ItemBlockMechanicalPipe
import mekanism.common.registration.impl.BlockDeferredRegister
import mekanism.common.registration.impl.BlockRegistryObject
import mekanism.common.tile.transmitter.TileEntityMechanicalPipe
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.WallSignBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.WoodType
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.BiFunction
import java.util.function.Supplier


object ModBlocks {

    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(MOD_ID)

    val REGISTRATE: Registrate = Registrate.create(MOD_ID)

    val LOGSIGN =
        BLOCKS.new("l") { WallSignBlock2(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)) }

    private val TITANIUM_REGISTRY = DeferredRegistryHelper(MOD_ID)

    val X_1_DRAWER: BlockWithTile = TITANIUM_REGISTRY.registerBlockWithTileItem("x_1_drawer", {
        DrawerBlock(
            DrawerWoodType.OAK,
            FunctionalStorage.DrawerType.X_1,
            BlockBehaviour.Properties.ofFullCopy(DrawerWoodType.OAK.planks)
        )
    }, { blockHolder ->
        Supplier { DrawerBlock.DrawerItem(blockHolder.get() as DrawerBlock, Item.Properties(), FunctionalStorage.TAB) }
    }, FunctionalStorage.TAB)

    val X_4_DRAWER: BlockWithTile = TITANIUM_REGISTRY.registerBlockWithTileItem("x_4_drawer", {
        DrawerBlock(
            DrawerWoodType.OAK,
            FunctionalStorage.DrawerType.X_4,
            BlockBehaviour.Properties.ofFullCopy(DrawerWoodType.OAK.planks)
        )
    }, { blockHolder ->
        Supplier { DrawerBlock.DrawerItem(blockHolder.get() as DrawerBlock, Item.Properties(), FunctionalStorage.TAB) }
    }, FunctionalStorage.TAB)

    init {
        FunctionalStorage.DRAWER_TYPES[FunctionalStorage.DrawerType.X_1]?.add(X_1_DRAWER)
        FunctionalStorage.DRAWER_TYPES[FunctionalStorage.DrawerType.X_4]?.add(X_4_DRAWER)
    }

//    val X_1_DRAWER_BLOCK_WITH_TILE = BlockWithTile(
//        {
//            DrawerBlock(
//                DrawerWoodType.OAK,
//                FunctionalStorage.DrawerType.X_1,
//                BlockBehaviour.Properties.ofFullCopy(DrawerWoodType.OAK.planks)
//            )
//        },
//        {
//            BlockEntityType.Builder.of<BlockEntity>(
//                DrawerBlock().tileEntityFactory,
//                *arrayOf<Block>(DrawerBlock())
//            ).build(null as Type<*>?)
//        }))
//    val X_1_DRAWER = BLOCKS.new("x_1_drawer")
//    val X_2_DRAWER = BLOCKS.new("x_2_drawer") { DrawerBlock(FunctionalStorage.DrawerType.X_2) }
//    val X_4_DRAWER = BLOCKS.new("x_4_drawer") { DrawerBlock(FunctionalStorage.DrawerType.X_4) }

    @JvmField
    @Suppress("removal")
    val FLUID_TANK: BlockEntry<ChemicalTankBlock> = REGISTRATE.block<ChemicalTankBlock>(
        "fluid_tank"
    ) { p -> ChemicalTankBlock(p, false) }.initialProperties { SharedProperties.copperMetal() }
        .properties { p -> p.noOcclusion().isRedstoneConductor { _, _, _ -> true } }.transform(pickaxeOnly())
        .blockstate { ctx, prov ->
//            FluidTankGenerator().generate(ctx, prov)
        }.onRegister(CreateRegistrate.blockModel {
            NonNullFunction { originalModel -> FluidTankModel.standard(originalModel) }
        }).transform(displaySource(AllDisplaySources.BOILER))
//        .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
        .onRegister(movementBehaviour(FluidTankMovementBehavior())).addLayer { Supplier { RenderType.cutoutMipped() } }
        .item { fluidTankBlock, properties ->
            FluidTankItem(fluidTankBlock, properties)
        }.model(AssetLookup.customBlockItemModel("_", "block_single_window")).build().register()

    @JvmField
    @Suppress("removal")
    val CREATIVE_FLUID_TANK: BlockEntry<ChemicalTankBlock> = REGISTRATE.block<ChemicalTankBlock>(
        "creative_fluid_tank"
    ) { p -> ChemicalTankBlock(p, true) }.initialProperties { SharedProperties.copperMetal() }
        .properties { p -> p.noOcclusion().isRedstoneConductor { _, _, _ -> true } }.transform(pickaxeOnly())
        .blockstate { ctx, prov ->
//            FluidTankGenerator().generate(ctx, prov)
        }.onRegister(CreateRegistrate.blockModel {
            NonNullFunction { originalModel -> FluidTankModel.standard(originalModel) }
        }).transform(displaySource(AllDisplaySources.BOILER))
//        .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
        .onRegister(movementBehaviour(FluidTankMovementBehavior())).addLayer { Supplier { RenderType.cutoutMipped() } }
        .item { fluidTankBlock, properties ->
            FluidTankItem(fluidTankBlock, properties)
        }.model(AssetLookup.customBlockItemModel("_", "block_single_window")).build().register()

    // Pickaxe only
//    val CHEMICAL_TANK = BLOCKS.new("chemical_tank") {
//        Block(Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion().isRedstoneConductor { _, _, _ -> true })
//    }

    val EXTRA_BLOCKS: BlockDeferredRegister = BlockDeferredRegister(MOD_ID)


    val WINDOWED_COPPER_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_copper_pressured_pipe", ModBlockTypes.WINDOWED_COPPER_PRESSURED_PIPE
        )

    val CAST_IRON_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "cast_iron_pressured_pipe", ModBlockTypes.CAST_IRON_PRESSURED_PIPE
        )
    val WINDOWED_CAST_IRON_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_cast_iron_pressured_pipe", ModBlockTypes.WINDOWED_CAST_IRON_PRESSURED_PIPE
        )

    val ALUMINUM_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "aluminum_pressured_pipe", ModBlockTypes.ALUMINUM_PRESSURED_PIPE
        )
    val WINDOWED_ALUMINUM_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_aluminum_pressured_pipe", ModBlockTypes.WINDOWED_ALUMINUM_PRESSURED_PIPE
        )

    val PLASTIC_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "plastic_pressured_pipe", ModBlockTypes.PLASTIC_PRESSURED_PIPE
        )
    val WINDOWED_PLASTIC_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_plastic_pressured_pipe", ModBlockTypes.WINDOWED_PLASTIC_PRESSURED_PIPE
        )

    val WINDOWED_STEEL_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_steel_pressured_pipe", ModBlockTypes.WINDOWED_STEEL_PRESSURED_PIPE
        )

    val BRASS_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "brass_pressured_pipe", ModBlockTypes.BRASS_PRESSURED_PIPE
        )
    val WINDOWED_BRASS_PRESSURED_PIPE: BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> =
        registerMechanicalPipe(
            "windowed_brass_pressured_pipe", ModBlockTypes.WINDOWED_BRASS_PRESSURED_PIPE
        )

    private fun registerMechanicalPipe(
        name: String, type: BlockTypeTile<TileEntityMechanicalPipe>
    ): BlockRegistryObject<BlockLargeTransmitter<TileEntityMechanicalPipe>, ItemBlockMechanicalPipe> {
        return registerTieredBlock(
            name,
            { BlockLargeTransmitter(type) },
            { block, props -> ItemBlockMechanicalPipe(block, props) })
    }

    private fun <BLOCK : Block?, ITEM : BlockItem?> registerTieredBlock(
        registerName: String, blockSupplier: Supplier<out BLOCK>, itemCreator: BiFunction<BLOCK, Item.Properties, ITEM>
    ): BlockRegistryObject<BLOCK, ITEM> {
        return EXTRA_BLOCKS.register(registerName, blockSupplier, itemCreator)
    }

}
