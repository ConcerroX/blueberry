package com.jerry.mekextras.common.registries;

import com.jerry.mekextras.MekanismExtras;
import com.jerry.mekextras.common.item.block.transmitter.*;
import com.jerry.mekextras.common.tile.transmitter.*;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ExtraBlocks {

    public static final BlockDeferredRegister EXTRA_BLOCKS = new BlockDeferredRegister(MekanismExtras.MOD_ID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(
        String registerName, Supplier<? extends BLOCK> blockSupplier,
        BiFunction<BLOCK, Item.Properties, ITEM> itemCreator
    ) {
        return EXTRA_BLOCKS.register(registerName, blockSupplier, itemCreator);
    }

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> ABSOLUTE_UNIVERSAL_CABLE = registerUniversalCable("absolute",
        ExtraBlockTypes.ABSOLUTE_UNIVERSAL_CABLE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> SUPREME_UNIVERSAL_CABLE = registerUniversalCable("supreme",
        ExtraBlockTypes.SUPREME_UNIVERSAL_CABLE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> COSMIC_UNIVERSAL_CABLE = registerUniversalCable("cosmic",
        ExtraBlockTypes.COSMIC_UNIVERSAL_CABLE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> INFINITE_UNIVERSAL_CABLE = registerUniversalCable("infinite",
        ExtraBlockTypes.INFINITE_UNIVERSAL_CABLE
    );

    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> ABSOLUTE_MECHANICAL_PIPE = registerMechanicalPipe("absolute",
        ExtraBlockTypes.ABSOLUTE_MECHANICAL_PIPE
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> SUPREME_MECHANICAL_PIPE = registerMechanicalPipe("supreme",
        ExtraBlockTypes.SUPREME_MECHANICAL_PIPE
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> COSMIC_MECHANICAL_PIPE = registerMechanicalPipe("cosmic",
        ExtraBlockTypes.COSMIC_MECHANICAL_PIPE
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> INFINITE_MECHANICAL_PIPE = registerMechanicalPipe("infinite",
        ExtraBlockTypes.INFINITE_MECHANICAL_PIPE
    );

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> ABSOLUTE_PRESSURIZED_TUBE = registerPressurizedTube("absolute",
        ExtraBlockTypes.ABSOLUTE_PRESSURIZED_TUBE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> SUPREME_PRESSURIZED_TUBE = registerPressurizedTube("supreme",
        ExtraBlockTypes.SUPREME_PRESSURIZED_TUBE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> COSMIC_PRESSURIZED_TUBE = registerPressurizedTube("cosmic",
        ExtraBlockTypes.COSMIC_PRESSURIZED_TUBE
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> INFINITE_PRESSURIZED_TUBE = registerPressurizedTube("infinite",
        ExtraBlockTypes.INFINITE_PRESSURIZED_TUBE
    );

    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> ABSOLUTE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("absolute",
        ExtraBlockTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> SUPREME_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("supreme",
        ExtraBlockTypes.SUPREME_LOGISTICAL_TRANSPORTER
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> COSMIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("cosmic",
        ExtraBlockTypes.COSMIC_LOGISTICAL_TRANSPORTER
    );
    public static final BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> INFINITE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("infinite",
        ExtraBlockTypes.INFINITE_LOGISTICAL_TRANSPORTER
    );

    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> ABSOLUTE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("absolute",
        ExtraBlockTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> SUPREME_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("supreme",
        ExtraBlockTypes.SUPREME_THERMODYNAMIC_CONDUCTOR
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> COSMIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("cosmic",
        ExtraBlockTypes.COSMIC_THERMODYNAMIC_CONDUCTOR
    );
    public static final BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> INFINITE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("infinite",
        ExtraBlockTypes.INFINITE_THERMODYNAMIC_CONDUCTOR
    );

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityUniversalCable>, ExtraItemBlockUniversalCable> registerUniversalCable(
        String nameTier, BlockTypeTile<ExtraTileEntityUniversalCable> type) {
        return registerTieredBlock(
            nameTier + "_universal_cable",
            () -> new BlockSmallTransmitter<>(type),
            ExtraItemBlockUniversalCable::new
        );
    }

    private static BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityMechanicalPipe>, ExtraItemBlockMechanicalPipe> registerMechanicalPipe(
        String nameTier, BlockTypeTile<ExtraTileEntityMechanicalPipe> type) {
        return registerTieredBlock(
            nameTier + "_mechanical_pipe",
            () -> new BlockLargeTransmitter<>(type),
            ExtraItemBlockMechanicalPipe::new
        );
    }

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityPressurizedTube>, ExtraItemBlockPressurizedTube> registerPressurizedTube(
        String nameTier, BlockTypeTile<ExtraTileEntityPressurizedTube> type) {
        return registerTieredBlock(
            nameTier + "_pressurized_tube",
            () -> new BlockSmallTransmitter<>(type),
            ExtraItemBlockPressurizedTube::new
        );
    }

    private static BlockRegistryObject<BlockLargeTransmitter<ExtraTileEntityLogisticalTransporter>, ExtraItemBlockLogisticalTransporter> registerLogisticalTransporter(
        String nameTier, BlockTypeTile<ExtraTileEntityLogisticalTransporter> type) {
        return registerTieredBlock(
            nameTier + "_logistical_transporter",
            () -> new BlockLargeTransmitter<>(type),
            ExtraItemBlockLogisticalTransporter::new
        );
    }

    private static BlockRegistryObject<BlockSmallTransmitter<ExtraTileEntityThermodynamicConductor>, ExtraItemBlockThermodynamicConductor> registerThermodynamicConductor(
        String nameTier, BlockTypeTile<ExtraTileEntityThermodynamicConductor> type) {
        return registerTieredBlock(
            nameTier + "_thermodynamic_conductor",
            () -> new BlockSmallTransmitter<>(type),
            ExtraItemBlockThermodynamicConductor::new
        );
    }

    public static void register(IEventBus eventBus) {
        EXTRA_BLOCKS.register(eventBus);
    }
}
