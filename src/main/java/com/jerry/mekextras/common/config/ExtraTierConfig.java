package com.jerry.mekextras.common.config;

import mekanism.api.heat.HeatAPI;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import mekanism.common.config.value.CachedIntValue;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Locale;

public class ExtraTierConfig extends BaseMekanismConfig {
    private static final String EXTRA_TRANSMITTER_CATEGORY = "transmitters";
    private static final String EXTRA_ENERGY_CATEGORY = "energy";
    private static final String EXTRA_FLUID_CATEGORY = "fluid";
    private static final String EXTRA_CHEMICAL_CATEGORY = "chemical";
    private static final String EXTRA_ITEM_CATEGORY = "item";
    private static final String EXTRA_HEAT_CATEGORY = "heat";
    private final ModConfigSpec configSpec;

    public final CachedLongValue absoluteUniversalCableCapacity;
    public final CachedLongValue supremeUniversalCableCapacity;
    public final CachedLongValue cosmicUniversalCableCapacity;
    public final CachedLongValue infiniteUniversalCableCapacity;

    public final CachedLongValue absoluteMechanicalPipeCapacity;
    public final CachedLongValue absoluteMechanicalPipePullAmount;
    public final CachedLongValue supremeMechanicalPipeCapacity;
    public final CachedLongValue supremeMechanicalPipePullAmount;
    public final CachedLongValue cosmicMechanicalPipeCapacity;
    public final CachedLongValue cosmicMechanicalPipePullAmount;
    public final CachedLongValue infiniteMechanicalPipeCapacity;
    public final CachedLongValue infiniteMechanicalPipePullAmount;

    public final CachedLongValue absolutePressurizedTubeCapacity;
    public final CachedLongValue absolutePressurizedTubePullAmount;
    public final CachedLongValue supremePressurizedTubeCapacity;
    public final CachedLongValue supremePressurizedTubePullAmount;
    public final CachedLongValue cosmicPressurizedTubeCapacity;
    public final CachedLongValue cosmicPressurizedTubePullAmount;
    public final CachedLongValue infinitePressurizedTubeCapacity;
    public final CachedLongValue infinitePressurizedTubePullAmount;

    public final CachedLongValue absoluteLogisticalTransporterSpeed;
    public final CachedLongValue absoluteLogisticalTransporterPullAmount;
    public final CachedLongValue supremeLogisticalTransporterSpeed;
    public final CachedLongValue supremeLogisticalTransporterPullAmount;
    public final CachedLongValue cosmicLogisticalTransporterSpeed;
    public final CachedLongValue cosmicLogisticalTransporterPullAmount;
    public final CachedLongValue infiniteLogisticalTransporterSpeed;
    public final CachedLongValue infiniteLogisticalTransporterPullAmount;

    public final CachedLongValue absoluteThermodynamicConductorConduction;
    public final CachedLongValue absoluteThermodynamicConductornCapacity;
    public final CachedLongValue absoluteThermodynamicConductornInsulation;
    public final CachedLongValue supremeThermodynamicConductorConduction;
    public final CachedLongValue supremeThermodynamicConductornCapacity;
    public final CachedLongValue supremeThermodynamicConductornInsulation;
    public final CachedLongValue cosmicThermodynamicConductorConduction;
    public final CachedLongValue cosmicThermodynamicConductornCapacity;
    public final CachedLongValue cosmicThermodynamicConductornInsulation;
    public final CachedLongValue infiniteThermodynamicConductorConduction;
    public final CachedLongValue infiniteThermodynamicConductornCapacity;
    public final CachedLongValue infiniteThermodynamicConductornInsulation;

    public ExtraTierConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Tier Config. This config is synced from server to client.").push("tier");
        //Transmitters
        builder.comment("Transmitters").push(EXTRA_TRANSMITTER_CATEGORY);

        builder.comment("Universal Cables").push(EXTRA_ENERGY_CATEGORY);
        this.absoluteUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_UNIVERSAL_CABLE_CAPACITY, "absoluteUniversalCable", 65536000L, 1, Long.MAX_VALUE);
        this.supremeUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_UNIVERSAL_CABLE_CAPACITY, "supremeUniversalCable", 524288000L, 1, Long.MAX_VALUE);
        this.cosmicUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_UNIVERSAL_CABLE_CAPACITY, "cosmicUniversalCable", 4194304000L, 1, Long.MAX_VALUE);
        this.infiniteUniversalCableCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_UNIVERSAL_CABLE_CAPACITY, "infiniteUniversalCable", 33554432000L, 1, Long.MAX_VALUE);
        builder.pop();

        builder.comment("Mechanical Pipes").push(EXTRA_FLUID_CATEGORY);
        this.absoluteMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_MECHANICAL_PIPE_CAPACITY, "absoluteMechanicalPipesCapacity", 1_024_000L, 1, Long.MAX_VALUE);
        this.absoluteMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_MECHANICAL_PIPE_PULL_AMOUNT, "absoluteMechanicalPipesPullAmount", 256_000, 1, Integer.MAX_VALUE);
        this.supremeMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_MECHANICAL_PIPE_CAPACITY, "supremeMechanicalPipesCapacity", 8_192_000L, 1, Long.MAX_VALUE);
        this.supremeMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_MECHANICAL_PIPE_PULL_AMOUNT, "supremeMechanicalPipesPullAmount", 2_048_000, 1, Integer.MAX_VALUE);
        this.cosmicMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_MECHANICAL_PIPE_CAPACITY, "cosmicMechanicalPipesCapacity", 65_536_000L, 1, Long.MAX_VALUE);
        this.cosmicMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_MECHANICAL_PIPE_PULL_AMOUNT, "cosmicMechanicalPipesPullAmount", 16_384_000, 1, Integer.MAX_VALUE);
        this.infiniteMechanicalPipeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_MECHANICAL_PIPE_CAPACITY, "infiniteMechanicalPipesCapacity", 524_288_000L, 1, Long.MAX_VALUE);
        this.infiniteMechanicalPipePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_MECHANICAL_PIPE_PULL_AMOUNT, "infiniteMechanicalPipesPullAmount", 131_072_000, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Pressurized Tubes").push(EXTRA_CHEMICAL_CATEGORY);
        this.absolutePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_PRESSURIZED_TUBE_CAPACITY, "absolutePressurizedTubesCapacity", 8_192_000L, 1, Long.MAX_VALUE);
        this.absolutePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_PRESSURIZED_TUBE_PULL_AMOUNT, "absolutePressurizedTubesPullAmount", 2_048_000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_PRESSURIZED_TUBE_CAPACITY, "supremePressurizedTubesCapacity", 65_536_000L, 1, Long.MAX_VALUE);
        this.supremePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_PRESSURIZED_TUBE_PULL_AMOUNT, "supremePressurizedTubesPullAmount", 16_384_000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_PRESSURIZED_TUBE_CAPACITY, "cosmicPressurizedTubesCapacity", 524_288_000L, 1, Long.MAX_VALUE);
        this.cosmicPressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_PRESSURIZED_TUBE_PULL_AMOUNT, "cosmicPressurizedTubesPullAmount", 131_072_000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubeCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_PRESSURIZED_TUBE_CAPACITY, "infinitePressurizedTubesCapacity", 4_194_304_000L, 1, Long.MAX_VALUE);
        this.infinitePressurizedTubePullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_PRESSURIZED_TUBE_PULL_AMOUNT, "infinitePressurizedTubesPullAmount", 1_048_576_000L, 1, Long.MAX_VALUE);
        builder.pop();

        builder.comment("Logistical Transporters").push(EXTRA_ITEM_CATEGORY);
        this.absoluteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_LOGISTICAL_TRANSPORTER_SPEED, "absoluteLogisticalTransporterSpeed", 55, 1, Integer.MAX_VALUE);
        this.absoluteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "absoluteLogisticalTransporterPullAmount", 128, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_LOGISTICAL_TRANSPORTER_SPEED, "supremeLogisticalTransporterSpeed", 60, 1, Integer.MAX_VALUE);
        this.supremeLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "supremeLogisticalTransporterPullAmount", 256, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_LOGISTICAL_TRANSPORTER_SPEED, "cosmicLogisticalTransporterSpeed", 70, 1, Integer.MAX_VALUE);
        this.cosmicLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "cosmicLogisticalTransporterPullAmount", 512, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterSpeed = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_LOGISTICAL_TRANSPORTER_SPEED, "infiniteLogisticalTransporterSpeed", 100, 1, Integer.MAX_VALUE);
        this.infiniteLogisticalTransporterPullAmount = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_LOGISTICAL_TRANSPORTER_PULL_AMOUNT, "infiniteLogisticalTransporterPullAmount", 1024, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("Thermodynamic Conductors").push(EXTRA_HEAT_CATEGORY);
        this.absoluteThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "absoluteThermodynamicConductorConduction", 10L, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductornCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTORN_CAPACITY, "absoluteThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.absoluteThermodynamicConductornInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.ABSOLUTE_THERMODYNAMIC_CONDUCTORN_INSULATION, "absoluteThermodynamicConductornInsulation", 400000L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "supremeThermodynamicConductorConduction", 15L, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductornCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTORN_CAPACITY, "supremeThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.supremeThermodynamicConductornInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.SUPREME_THERMODYNAMIC_CONDUCTORN_INSULATION, "supremeThermodynamicConductornInsulation", 800000L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "cosmicThermodynamicConductorConduction", 20L, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductornCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTORN_CAPACITY, "cosmicThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.cosmicThermodynamicConductornInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.COSMIC_THERMODYNAMIC_CONDUCTORN_INSULATION, "cosmicThermodynamicConductornInsulation", 1000000L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductorConduction = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTOR_CONDUCTION, "infiniteThermodynamicConductorConduction", 25L, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductornCapacity = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTORN_CAPACITY, "infiniteThermodynamicConductornCapacity", (long) HeatAPI.DEFAULT_HEAT_CAPACITY, 1, Long.MAX_VALUE);
        this.infiniteThermodynamicConductornInsulation = CachedLongValue.define(this, builder, ExtraConfigTranslations.INFINITE_THERMODYNAMIC_CONDUCTORN_INSULATION, "infiniteThermodynamicConductornInsulation", 4000000L, 1, Long.MAX_VALUE);
        builder.pop();
        builder.pop();

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "TierConfig";
    }

    @Override
    public String getTranslation() {
        return null;
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
