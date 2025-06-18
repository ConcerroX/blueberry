package com.djinfinite.manors_bounty

import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModCreativeModeTabs
import com.djinfinite.manors_bounty.registry.ModItems
import com.mojang.logging.LogUtils
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.server.ServerStartingEvent

@Mod(ManorsBounty.ID)
class ManorsBounty(modEventBus: IEventBus, modContainer: ModContainer) {

    init {
        ModBlocks.BLOCKS.register(modEventBus)
        ModItems.ITEMS.register(modEventBus)
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus)

        NeoForge.EVENT_BUS.register(this)
        modContainer.registerConfig(ModConfig.Type.COMMON, ManorsBountyConfig.SPEC)
    }

    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent?) {
        LOGGER.info("HELLO from server starting")
    }

    companion object {
        const val ID = "manors_bounty"
        private val LOGGER = LogUtils.getLogger()
    }

}