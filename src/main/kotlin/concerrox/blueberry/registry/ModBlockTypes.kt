package concerrox.blueberry.registry

import mekanism.api.text.ILangEntry
import mekanism.api.tier.ITier
import mekanism.common.MekanismLang
import mekanism.common.block.attribute.AttributeTier
import mekanism.common.content.blocktype.BlockTypeTile
import mekanism.common.content.blocktype.BlockTypeTile.BlockTileBuilder
import mekanism.common.registration.impl.TileEntityTypeRegistryObject
import mekanism.common.tier.PipeTier
import mekanism.common.tile.transmitter.TileEntityMechanicalPipe
import mekanism.common.tile.transmitter.TileEntityTransmitter
import java.util.function.Supplier


object ModBlockTypes {

    val WINDOWED_COPPER_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.WINDOWED_COPPER_PRESSURED_PIPE }

    val CAST_IRON_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.CAST_IRON_PRESSURED_PIPE }
    val WINDOWED_CAST_IRON_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.WINDOWED_CAST_IRON_PRESSURED_PIPE }

    val ALUMINUM_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.ALUMINUM_PRESSURED_PIPE }
    val WINDOWED_ALUMINUM_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.WINDOWED_ALUMINUM_PRESSURED_PIPE }

    val PLASTIC_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.PLASTIC_PRESSURED_PIPE }
    val WINDOWED_PLASTIC_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.BASIC) { ModTileEntityTypes.WINDOWED_PLASTIC_PRESSURED_PIPE }

    val WINDOWED_STEEL_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.ADVANCED) { ModTileEntityTypes.WINDOWED_STEEL_PRESSURED_PIPE }

    val BRASS_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.ADVANCED) { ModTileEntityTypes.BRASS_PRESSURED_PIPE }
    val WINDOWED_BRASS_PRESSURED_PIPE: BlockTypeTile<TileEntityMechanicalPipe> = createPipe(
        PipeTier.ADVANCED) { ModTileEntityTypes.WINDOWED_BRASS_PRESSURED_PIPE }

    private fun createPipe(
        tier: PipeTier, tile: Supplier<TileEntityTypeRegistryObject<TileEntityMechanicalPipe>>
    ): BlockTypeTile<TileEntityMechanicalPipe> {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_PIPE)
    }

    private fun <TILE : TileEntityTransmitter> createTransmitter(
        tier: ITier, tile: Supplier<TileEntityTypeRegistryObject<TILE>>, description: ILangEntry
    ): BlockTypeTile<TILE> {
        return BlockTileBuilder.createBlock(tile, description).with(AttributeTier(tier)).build() as BlockTypeTile<TILE>
    }

}