package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.tile.transmitter.*;
import com.jerry.mekextras.common.capabilities.ExtraCapabilities;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.Mekanism;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.integration.computer.ComputerCapabilityHelper;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.CapabilityTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;

public class ExtraTileEntityTypes {

    public static final TileEntityTypeDeferredRegister EXTRA_TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismExtras.MOD_ID);

    //Transmitters
    //Universal Cables
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerCable(ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> SUPREME_UNIVERSAL_CABLE = registerCable(ExtraBlocks.SUPREME_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> COSMIC_UNIVERSAL_CABLE = registerCable(ExtraBlocks.COSMIC_UNIVERSAL_CABLE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> INFINITE_UNIVERSAL_CABLE = registerCable(ExtraBlocks.INFINITE_UNIVERSAL_CABLE);
    //Mechanical Pipes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerPipe(ExtraBlocks.ABSOLUTE_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerPipe(ExtraBlocks.SUPREME_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerPipe(ExtraBlocks.COSMIC_MECHANICAL_PIPE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerPipe(ExtraBlocks.INFINITE_MECHANICAL_PIPE);
    //Pressurized Tubes
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerTube(ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerTube(ExtraBlocks.SUPREME_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerTube(ExtraBlocks.COSMIC_PRESSURIZED_TUBE);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerTube(ExtraBlocks.INFINITE_PRESSURIZED_TUBE);
    //Logistic Transporters
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerTransporter(ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER, ExtraTileEntityLogisticalTransporter::new);
    //Thermodynamic Conductors
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR);
    public static final TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerConductor(ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR);

    private static TileEntityTypeRegistryObject<ExtraTileEntityUniversalCable> registerCable(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityUniversalCable> builder = transmitterBuilder(block, ExtraTileEntityUniversalCable::new);
        EnergyCompatUtils.addBlockCapabilities(builder);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityMechanicalPipe> registerPipe(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityMechanicalPipe> builder = transmitterBuilder(block, ExtraTileEntityMechanicalPipe::new)
                .with(Capabilities.FLUID.block(), CapabilityTileEntity.FLUID_HANDLER_PROVIDER);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityPressurizedTube> registerTube(BlockRegistryObject<?, ?> block) {
        TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<ExtraTileEntityPressurizedTube> builder = transmitterBuilder(block, ExtraTileEntityPressurizedTube::new)
                .with(Capabilities.CHEMICAL.block(), CapabilityTileEntity.CHEMICAL_HANDLER_PROVIDER);
        if (Mekanism.hooks.computerCompatEnabled()) {
            ComputerCapabilityHelper.addComputerCapabilities(builder, ConstantPredicates.ALWAYS_TRUE);
        }
        return builder.build();
    }
    private static <BE extends ExtraTileEntityLogisticalTransporterBase> TileEntityTypeRegistryObject<BE> registerTransporter(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return transporterBuilder(block, factory).build();
    }
    private static <BE extends ExtraTileEntityLogisticalTransporterBase> TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<BE> transporterBuilder(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return transmitterBuilder(block, factory)
                .clientTicker(ExtraTileEntityLogisticalTransporterBase::tickClient)
                .with(Capabilities.ITEM.block(), CapabilityTileEntity.ITEM_HANDLER_PROVIDER);
    }
    private static TileEntityTypeRegistryObject<ExtraTileEntityThermodynamicConductor> registerConductor(BlockRegistryObject<?, ?> block) {
        return transmitterBuilder(block, ExtraTileEntityThermodynamicConductor::new)
                .with(Capabilities.HEAT, CapabilityTileEntity.HEAT_HANDLER_PROVIDER)
                .build();
    }
    private static <BE extends ExtraTileEntityTransmitter> TileEntityTypeDeferredRegister.BlockEntityTypeBuilder<BE> transmitterBuilder(BlockRegistryObject<?, ?> block, BlockEntityFactory<BE> factory) {
        return EXTRA_TILE_ENTITY_TYPES.builder(block, (pos, state) -> factory.create(block, pos, state))
                .serverTicker(ExtraTileEntityTransmitter::tickServer)
                .withSimple(ExtraCapabilities.EXTRA_ALLOY_INTERACTION)
                .with(Capabilities.CONFIGURABLE, ExtraTileEntityTransmitter.CONFIGURABLE_PROVIDER);
    }

    @FunctionalInterface
    private interface BlockEntityFactory<BE extends BlockEntity> {

        BE create(Holder<Block> block, BlockPos pos, BlockState state);
    }
    public static void register(IEventBus eventBus) {
        EXTRA_TILE_ENTITY_TYPES.register(eventBus);
    }
}
