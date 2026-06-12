package concerrox.blueberry.logistics.data

import concerrox.blueberry.logistics.BlueberryLogistics
import concerrox.blueberry.logistics.registry.ModBlocks
import mekanism.common.block.transmitter.BlockLargeTransmitter
import mekanism.common.item.block.transmitter.ItemBlockLogisticalTransporter
import mekanism.common.registration.impl.BlockRegistryObject
import mekanism.common.tile.transmitter.TileEntityLogisticalTransporter
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, existingFileHelper: ExistingFileHelper) : ItemModelProvider(output,
    BlueberryLogistics.MOD_ID,
    existingFileHelper
) {

    override fun registerModels() {
        ModBlocks.ITEM_DUCTS.forEach(::itemDuct)
    }

    private fun itemDuct(registryObject: BlockRegistryObject<BlockLargeTransmitter<TileEntityLogisticalTransporter>, ItemBlockLogisticalTransporter>) {
        val id = registryObject.id
        val typeName = id.path.removeSuffix("_item_duct")
        withExistingParent(id.toString(), modLoc("block/transmitter/item_duct/$typeName"))
    }

}