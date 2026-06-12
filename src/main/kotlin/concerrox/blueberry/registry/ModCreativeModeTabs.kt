package concerrox.blueberry.registry

import concerrox.blueberry.Blueberry.Companion.MOD_ID
import mekanism.common.registries.MekanismBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID)
    private val TAB_ITEMS by lazy {
        listOf(
            MekanismBlocks.BASIC_MECHANICAL_PIPE.asItem(),
            ModBlocks.WINDOWED_COPPER_PRESSURED_PIPE.asItem(),

            ModBlocks.CAST_IRON_PRESSURED_PIPE.asItem(),
            ModBlocks.WINDOWED_CAST_IRON_PRESSURED_PIPE.asItem(),

            ModBlocks.ALUMINUM_PRESSURED_PIPE.asItem(),
            ModBlocks.WINDOWED_ALUMINUM_PRESSURED_PIPE.asItem(),

            ModBlocks.PLASTIC_PRESSURED_PIPE.asItem(),
            ModBlocks.WINDOWED_PLASTIC_PRESSURED_PIPE.asItem(),

            MekanismBlocks.ADVANCED_MECHANICAL_PIPE.asItem(),
            ModBlocks.WINDOWED_STEEL_PRESSURED_PIPE.asItem(),

            ModBlocks.BRASS_PRESSURED_PIPE.asItem(),
            ModBlocks.WINDOWED_BRASS_PRESSURED_PIPE.asItem()
        )
    }

    init {
        CREATIVE_MODE_TABS.register("tab", Supplier {
            CreativeModeTab.builder().title(Component.translatable("itemGroup.blueberry"))
                .withTabsBefore(CreativeModeTabs.COMBAT)
//                .icon { EXAMPLE_ITEM.get().defaultInstance }
                .displayItems { _, output ->
                    TAB_ITEMS.forEach {
                        output.accept(it)
                    }
                }.build()
        })
    }

}