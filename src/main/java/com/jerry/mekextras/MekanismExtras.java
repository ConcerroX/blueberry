package com.jerry.mekextras;

import com.jerry.mekextras.common.capabilities.ExtraCapabilities;
import com.jerry.mekextras.common.config.ExtraConfig;
import com.jerry.mekextras.common.registries.*;
import com.mojang.logging.LogUtils;
import mekanism.common.command.CommandMek;
import mekanism.common.lib.Version;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

public class MekanismExtras {

    public static final String MOD_ID = "blueberry";
    public static final String MOD_NAME = "Mekanism-Extras";
    public static MekanismExtras instance;

    public MekanismExtras(ModContainer modContainer, IEventBus modEventBus) {
        instance = this;
        ExtraConfig.registerConfigs(modContainer);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        modEventBus.addListener(ExtraCapabilities::registerCapabilities);
        modEventBus.addListener(ExtraConfig::onConfigLoad);
        ExtraItems.register(modEventBus);
        ExtraBlocks.register(modEventBus);
        ExtraTileEntityTypes.register(modEventBus);
        ExtraCreativeTabs.register(modEventBus);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(CommandMek.register());
    }

}
