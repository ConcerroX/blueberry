package concerrox.blueberry.logistics.registry

import concerrox.blueberry.logistics.BlueberryLogistics
import mekanism.common.block.transmitter.BlockLargeTransmitter
import mekanism.common.content.blocktype.BlockTypeTile
import mekanism.common.item.block.transmitter.ItemBlockLogisticalTransporter
import mekanism.common.registration.impl.BlockDeferredRegister
import mekanism.common.registration.impl.BlockRegistryObject
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import java.util.function.BiFunction
import java.util.function.Supplier

object ModBlocks {

    internal val MEKANISM_BLOCKS = BlockDeferredRegister(BlueberryLogistics.MOD_ID)

    val ANDESITE_ITEM_DUCT = registerLogisticalTransporter("andesite_item_duct", ModBlockTypes.ANDESITE_ITEM_DUCT)
    val WINDOWED_ANDESITE_ITEM_DUCT =
        registerLogisticalTransporter("windowed_andesite_item_duct", ModBlockTypes.WINDOWED_ANDESITE_ITEM_DUCT)
    val BRASS_ITEM_DUCT = registerLogisticalTransporter("brass_item_duct", ModBlockTypes.BRASS_ITEM_DUCT)
    val WINDOWED_BRASS_ITEM_DUCT =
        registerLogisticalTransporter("windowed_brass_item_duct", ModBlockTypes.WINDOWED_BRASS_ITEM_DUCT)

    internal val ITEM_DUCTS =
        arrayOf(ANDESITE_ITEM_DUCT, WINDOWED_ANDESITE_ITEM_DUCT, BRASS_ITEM_DUCT, WINDOWED_BRASS_ITEM_DUCT)

    private fun registerLogisticalTransporter(
        name: String, type: BlockTypeTile<TileEntityLogisticalTransporter>
    ): BlockRegistryObject<BlockLargeTransmitter<TileEntityLogisticalTransporter>, ItemBlockLogisticalTransporter> {
        return registerTieredBlock(name, { BlockLargeTransmitter(type) }, ::ItemBlockLogisticalTransporter)
    }

    private fun <B : Block, I : BlockItem> registerTieredBlock(
        registerName: String, blockSupplier: Supplier<out B>, itemCreator: BiFunction<B, Item.Properties, I>
    ): BlockRegistryObject<B, I> {
        return MEKANISM_BLOCKS.register(registerName, blockSupplier, itemCreator)
    }

}