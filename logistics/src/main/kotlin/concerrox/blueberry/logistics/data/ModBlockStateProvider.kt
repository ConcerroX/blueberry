package concerrox.blueberry.logistics.data

import concerrox.blueberry.logistics.BlueberryLogistics
import concerrox.blueberry.logistics.registry.ModBlocks
import mekanism.common.block.transmitter.BlockLargeTransmitter
import mekanism.common.item.block.transmitter.ItemBlockLogisticalTransporter
import mekanism.common.registration.impl.BlockRegistryObject
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(output: PackOutput, existingFileHelper: ExistingFileHelper) : BlockStateProvider(
    output, BlueberryLogistics.MOD_ID, existingFileHelper
) {

    override fun registerStatesAndModels() {
        ModBlocks.ITEM_DUCTS.forEach(::itemDuct)
    }

    private fun itemDuct(registryObject: BlockRegistryObject<BlockLargeTransmitter<TileEntityLogisticalTransporter>, ItemBlockLogisticalTransporter>) {
        val typeName = registryObject.id.path.removeSuffix("_item_duct")
        val model = models().withExistingParent(
            "block/transmitter/item_duct/$typeName", modLoc("block/transmitter/large_pipe")
        ).texture("side", modLoc("block/transmitter/item_duct/$typeName"))
            .texture("center_down", "block/transmitter/item_duct/${typeName}_node")
        simpleBlock(registryObject.get(), model)
    }

}