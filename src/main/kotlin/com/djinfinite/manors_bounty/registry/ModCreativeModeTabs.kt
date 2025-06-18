package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ManorsBounty.ID)
    private val TAB_ITEMS by lazy {
        listOf<ItemLike>(

        )
    }

    init {
        CREATIVE_MODE_TABS.register("tab", Supplier {
            CreativeModeTab.builder().title(Component.translatable("itemGroup.manors_bounty"))
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