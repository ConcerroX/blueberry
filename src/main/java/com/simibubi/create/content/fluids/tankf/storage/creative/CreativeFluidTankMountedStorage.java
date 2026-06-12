package com.simibubi.create.content.fluids.tankf.storage.creative;

import concerrox.blueberry.registry.ModBlockEntityTypes;
import mekanism.api.chemical.BasicChemicalTank;
import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.AllMountedStorageTypes;
import concerrox.blueberry.content.chemical.tank.MountedChemicalStorageType;
import concerrox.blueberry.content.chemical.tank.WrapperMountedChemicalStorage;
import com.simibubi.create.content.fluids.tankf.CreativeFluidTankBlockEntity;
import com.simibubi.create.content.fluids.tankf.CreativeFluidTankBlockEntity.CreativeBasicChemicalTank;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeFluidTankMountedStorage extends WrapperMountedChemicalStorage<CreativeBasicChemicalTank> {
	public static final MapCodec<CreativeFluidTankMountedStorage> CODEC = CreativeBasicChemicalTank.CODEC.xmap(
		CreativeFluidTankMountedStorage::new, storage -> storage.getWrapped()
	).fieldOf("value");

	protected CreativeFluidTankMountedStorage(MountedChemicalStorageType<?> type, CreativeBasicChemicalTank tank) {
		super(type, tank);
	}

	protected CreativeFluidTankMountedStorage(CreativeBasicChemicalTank tank) {
		this(null,/*ModBlockEntityTypes.CREATIVE_FLUID_TANK2.get()*/ tank);
	}

	@Override
	public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
		// no need to do anything, supplied stack can't change while mounted
	}

	public static CreativeFluidTankMountedStorage fromTank(CreativeFluidTankBlockEntity tank) {
		// make an isolated copy
		BasicChemicalTank inv = tank.getTankInventory();
		CreativeBasicChemicalTank copy = new CreativeBasicChemicalTank(inv.getCapacity(), () -> {});
		copy.setContainedFluid(inv.getStack());
		return new CreativeFluidTankMountedStorage(copy);
	}

//	public static CreativeFluidTankMountedStorage fromLegacy(HolderLookup.Provider registries, CompoundTag nbt) {
//		int capacity = nbt.getInt("Capacity");
//		ChemicalStack fluid = FluidStack.parseOptional(registries, nbt.getCompound("ProvidedStack"));
//		CreativeBasicChemicalTank tank = new CreativeBasicChemicalTank(capacity, $ -> {});
//		tank.setContainedFluid(fluid);
//		return new CreativeFluidTankMountedStorage(tank);
//	}
}
