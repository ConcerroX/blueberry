package com.simibubi.create.content.fluids.tankf;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import concerrox.blueberry.registry.ModBlockEntityTypes;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.functions.ConstantPredicates;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CreativeFluidTankBlockEntity extends ChemicalTankBlockEntity {

    public CreativeFluidTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.CHEMICAL.block(), ModBlockEntityTypes.CREATIVE_FLUID_TANK.get(),
            (be, context) -> {
                if (be.chemicalCapability == null)
                    be.refreshCapability();
                return be.chemicalCapability;
            });
    }

    @Override
    protected BasicChemicalTank createInventory() {
        return new CreativeBasicChemicalTank(getCapacityMultiplier(), this::onChemicalStackChanged);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return false;
    }

    public static class CreativeBasicChemicalTank extends BasicChemicalTank {
        public static final Codec<CreativeBasicChemicalTank> CODEC = RecordCodecBuilder.create(
            i -> i.group(ChemicalStack.OPTIONAL_CODEC.fieldOf("fluid").forGetter(BasicChemicalTank::getStack),
                    Codec.LONG.fieldOf("capacity").forGetter(BasicChemicalTank::getCapacity))
                .apply(i, (fluid, capacity) -> {
                    CreativeBasicChemicalTank tank = new CreativeBasicChemicalTank(capacity,
                        ConstantPredicates.alwaysTrueBi(), ConstantPredicates.alwaysTrueBi(),
                        ConstantPredicates.alwaysTrue(), null, () -> {
                    }, null);
                    tank.setContainedFluid(fluid);
                    return tank;
                }));

        //        public CreativeBasicChemicalTank(int capacity, Consumer<FluidStack> updateCallback) {
        //            super(capacity, updateCallback);
        //        }

        public CreativeBasicChemicalTank(long capacity, IContentsListener listener) {
            super(capacity, ConstantPredicates.alwaysTrueBi(), ConstantPredicates.alwaysTrueBi(),
                ConstantPredicates.alwaysTrue(), null, listener, null);
        }

        public CreativeBasicChemicalTank(long capacity, BiPredicate<ChemicalStack, @NotNull AutomationType> canExtract,
            java.util.function.BiPredicate<ChemicalStack, @NotNull AutomationType> canInsert,
            Predicate<ChemicalStack> validator, @Nullable ChemicalAttributeValidator attributeValidator,
            @Nullable IContentsListener listener, @Nullable Void ignored
        ) {
            super(capacity, canExtract, canInsert, validator, attributeValidator, listener, ignored);
        }

        @Override
        public long getStored() {
            return 0L; //getStack().isEmpty() ? 0 : getTankCapacity(0);
        }

        public void setContainedFluid(ChemicalStack chemicalStack) {
            setChemicalInTank(0, chemicalStack);
            //            fluid = fluidStack.copy();
            //            if (!fluidStack.isEmpty())
            //                fluid.setAmount(getTankCapacity(0));
            onContentsChanged();
        }

        @Override
        public ChemicalStack insertChemical(ChemicalStack stack, Action action) {
            return stack;
        }

        @Override
        public ChemicalStack extractChemical(ChemicalStack resource, Action action) {
            return super.extractChemical(resource, Action.SIMULATE);
        }

        @Override
        public ChemicalStack extractChemical(long maxDrain, Action action) {
            return super.extractChemical(maxDrain, Action.SIMULATE);
        }

    }

}
