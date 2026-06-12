package concerrox.blueberry.logistics.registry

import mekanism.api.text.ILangEntry
import mekanism.api.tier.ITier
import mekanism.common.MekanismLang
import mekanism.common.block.attribute.AttributeTier
import mekanism.common.content.blocktype.BlockTypeTile
import mekanism.common.content.blocktype.BlockTypeTile.BlockTileBuilder
import mekanism.common.registration.impl.TileEntityTypeRegistryObject
import mekanism.common.tier.TransporterTier
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter
import mekanism.common.tile.transmitter.TileEntityTransmitter
import java.util.function.Supplier


object ModBlockTypes {

    val ANDESITE_ITEM_DUCT: BlockTypeTile<TileEntityLogisticalTransporter> =
        createTransporter(TransporterTier.BASIC) { ModTileEntityTypes.ANDESITE_ITEM_DUCT }
    val WINDOWED_ANDESITE_ITEM_DUCT: BlockTypeTile<TileEntityLogisticalTransporter> =
        createTransporter(TransporterTier.BASIC) { ModTileEntityTypes.WINDOWED_ANDESITE_ITEM_DUCT }
    val BRASS_ITEM_DUCT: BlockTypeTile<TileEntityLogisticalTransporter> =
        createTransporter(TransporterTier.BASIC) { ModTileEntityTypes.BRASS_ITEM_DUCT }
    val WINDOWED_BRASS_ITEM_DUCT: BlockTypeTile<TileEntityLogisticalTransporter> =
        createTransporter(TransporterTier.BASIC) { ModTileEntityTypes.WINDOWED_BRASS_ITEM_DUCT }

    private fun createTransporter(
        tier: TransporterTier, tile: Supplier<TileEntityTypeRegistryObject<TileEntityLogisticalTransporter>>
    ): BlockTypeTile<TileEntityLogisticalTransporter> {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TRANSPORTER)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : TileEntityTransmitter> createTransmitter(
        tier: ITier, tile: Supplier<TileEntityTypeRegistryObject<T>>, description: ILangEntry
    ): BlockTypeTile<T> {
        return BlockTileBuilder.createBlock(tile, description).with(AttributeTier(tier)).build() as BlockTypeTile<T>
    }

}