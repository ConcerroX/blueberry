package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityTransmitter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityUniversalCable;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityMechanicalPipe;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityThermodynamicConductor;
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityPressurizedTube;
import mekanism.api.text.ILangEntry;
import mekanism.api.tier.ITier;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.content.blocktype.*;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.*;

import java.util.function.Supplier;

public class ExtraBlockTypes {

    //Transmitters
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = createCable(CableTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = createCable(CableTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = createCable(CableTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE);
    public static final BlockTypeTile<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = createCable(CableTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE);

    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = createPipe(PipeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = createPipe(PipeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = createPipe(PipeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE);
    public static final BlockTypeTile<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = createPipe(PipeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE);

    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = createTube(TubeTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = createTube(TubeTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = createTube(TubeTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE);
    public static final BlockTypeTile<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = createTube(TubeTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE);

    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER);
    public static final BlockTypeTile<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = createTransporter(TransporterTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER);

    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.BASIC, () -> ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ADVANCED, () -> ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ELITE, () -> ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final BlockTypeTile<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = createConductor(ConductorTier.ULTIMATE, () -> ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR);

    private static BlockTypeTile<ExtraTileEntityUniversalCable> createCable(CableTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CABLE);
    }

    private static BlockTypeTile<ExtraTileEntityMechanicalPipe> createPipe(PipeTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_PIPE);
    }

    private static BlockTypeTile<ExtraTileEntityPressurizedTube> createTube(TubeTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TUBE);
    }

    private static BlockTypeTile<ExtraTileEntityLogisticalTransporter> createTransporter(TransporterTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_TRANSPORTER);
    }

    private static BlockTypeTile<ExtraTileEntityThermodynamicConductor> createConductor(ConductorTier tier, Supplier<TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor>> tile) {
        return createTransmitter(tier, tile, MekanismLang.DESCRIPTION_CONDUCTOR);
    }

    private static <TILE extends ExtraTileEntityTransmitter> BlockTypeTile<TILE> createTransmitter(ITier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile, ILangEntry description) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, description)
                .with(new AttributeTier<>(tier))
                .build();
    }
}
