package com.jerry.mekextras.api.tier;

import mekanism.api.SupportsColorMap;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum AdvancedTier implements StringRepresentable, SupportsColorMap {
    ABSOLUTE("Absolute", new int[]{95, 255, 184}, MapColor.COLOR_LIGHT_GREEN),
    SUPREME("Supreme", new int[]{255, 128, 106}, MapColor.TERRACOTTA_PINK),
    COSMIC("Cosmic", new int[]{75, 248, 255}, MapColor.DIAMOND),
    INFINITE("Infinite", new int[]{247, 135, 255}, MapColor.COLOR_MAGENTA);

    private final String name;
    private final MapColor mapColor;
    private TextColor textColor;
    private int[] rgbCode;
    private int argb;

    AdvancedTier(String name, int[] rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        setColorFromAtlas(rgbCode);
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    public int getPackedColor() {
        return argb;
    }

    @Override
    public int[] getRgbCode() {
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        this.rgbCode = color;
        this.argb = FastColor.ARGB32.color(rgbCode[0], rgbCode[1], rgbCode[2]);
        this.textColor = TextColor.fromRgb(argb);
    }

    public TextColor getColor() {
        return this.textColor;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
