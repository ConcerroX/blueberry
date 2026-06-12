package concerrox.blueberry.logistics.registry

import mekanism.common.Mekanism
import mekanism.common.capabilities.Capabilities
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister.BlockEntityTypeBuilder
import mekanism.common.registration.impl.TileEntityTypeRegistryObject
import mekanism.common.tile.base.CapabilityTileEntity
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporterBase
import mekanism.common.tile.transmitter.TileEntityTransmitter
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.registries.DeferredHolder


object ModTileEntityTypes {

    internal val MEKANISM_TILE_ENTITY_TYPES = TileEntityTypeDeferredRegister(Mekanism.MODID)

    val ANDESITE_ITEM_DUCT = registerTransporter(ModBlocks.ANDESITE_ITEM_DUCT, ::TileEntityLogisticalTransporter)
    val WINDOWED_ANDESITE_ITEM_DUCT =
        registerTransporter(ModBlocks.WINDOWED_ANDESITE_ITEM_DUCT, ::TileEntityLogisticalTransporter)
    val BRASS_ITEM_DUCT = registerTransporter(ModBlocks.BRASS_ITEM_DUCT, ::TileEntityLogisticalTransporter)
    val WINDOWED_BRASS_ITEM_DUCT =
        registerTransporter(ModBlocks.WINDOWED_BRASS_ITEM_DUCT, ::TileEntityLogisticalTransporter)

    private fun <BE : TileEntityLogisticalTransporterBase> registerTransporter(
        block: DeferredHolder<Block, *>, factory: BlockEntityFactory<BE>
    ): TileEntityTypeRegistryObject<BE> {
        return transporterBuilder(block, factory).build()
    }

    private fun <BE : TileEntityLogisticalTransporterBase> transporterBuilder(
        block: DeferredHolder<Block, *>, factory: BlockEntityFactory<BE>
    ): BlockEntityTypeBuilder<BE> {
        return transmitterBuilder(block, factory).clientTicker(TileEntityLogisticalTransporterBase::tickClient)
            .with(Capabilities.ITEM.block(), CapabilityTileEntity.ITEM_HANDLER_PROVIDER)
    }

    private fun <BE : TileEntityTransmitter> transmitterBuilder(
        block: DeferredHolder<Block, *>, factory: BlockEntityFactory<BE>
    ): BlockEntityTypeBuilder<BE> {
        return MEKANISM_TILE_ENTITY_TYPES.builder(block) { pos, state -> factory.create(block, pos, state) }
            .serverTicker(TileEntityTransmitter::tickServer).withSimple(Capabilities.ALLOY_INTERACTION)
            .with(Capabilities.CONFIGURABLE, TileEntityTransmitter.CONFIGURABLE_PROVIDER)
    }

    private fun interface BlockEntityFactory<BE : BlockEntity> {
        fun create(block: Holder<Block>, pos: BlockPos, state: BlockState): BE
    }

}