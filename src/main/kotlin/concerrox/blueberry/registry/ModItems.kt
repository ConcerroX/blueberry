package concerrox.blueberry.registry

import concerrox.blueberry.Blueberry.Companion.MOD_ID
import concerrox.blueberry.util.new
import net.minecraft.world.item.Item
import net.minecraft.world.item.SignItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {

    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(MOD_ID)

    val LOGSIGN = ITEMS.new("l") { SignItem(Item.Properties(), ModBlocks.LOGSIGN.get(), ModBlocks.LOGSIGN.get()) }

//    val WINDOWED_COPPER_PRESSURED_PIPE: DeferredItem<BlockItem> = ITEMS.registerSimpleBlockItem(
//        "windowed_copper_pressured_pipe", ModBlocks.WINDOWED_COPPER_PRESSURED_PIPE)

}