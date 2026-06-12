package com.jerry.mekextras.common.config;

import mekanism.common.Mekanism;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.util.HashMap;
import java.util.Map;

public class ExtraConfig {

    private ExtraConfig() {
    }
    private static final Map<IConfigSpec, IMekanismConfig> KNOWN_CONFIGS = new HashMap<>();
    public static final ExtraTierConfig extraTierConfig = new ExtraTierConfig();

    public static void registerConfigs(ModContainer modContainer) {
        ExtraConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, extraTierConfig);
    }

    public static void onConfigLoad(ModConfigEvent configEvent) {
        ExtraConfigHelper.onConfigLoad(configEvent, Mekanism.MODID, KNOWN_CONFIGS);
    }
}
