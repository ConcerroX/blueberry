package com.simibubi.create.content.fluids.tankf.storage;

import com.mojang.serialization.Codec;
import concerrox.blueberry.registry.ModBlockEntityTypes;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.functions.ConstantPredicates;
import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simibubi.create.api.contraption.storage.SyncedMountedStorage;
import concerrox.blueberry.content.chemical.tank.MountedChemicalStorageType;
import concerrox.blueberry.content.chemical.tank.WrapperMountedChemicalStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.fluids.tankf.ChemicalTankBlockEntity;
import com.simibubi.create.content.fluids.tankf.storage.ChemicalTankMountedStorage.Handler;

import net.createmod.catnip.animation.LerpedFloat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChemicalTankMountedStorage extends WrapperMountedChemicalStorage<Handler> implements SyncedMountedStorage {
	public static final MapCodec<ChemicalTankMountedStorage> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Codec.LONG.fieldOf("capacity").forGetter(ChemicalTankMountedStorage::getCapacity),
		ChemicalStack.OPTIONAL_CODEC.fieldOf("fluid").forGetter(ChemicalTankMountedStorage::getFluid)
	).apply(i, ChemicalTankMountedStorage::new));

	private boolean dirty;

	protected ChemicalTankMountedStorage(MountedChemicalStorageType<?> type, long capacity, ChemicalStack stack) {
		super(type, new Handler(capacity, stack));
		this.getWrapped().onChange = () -> this.dirty = true;
	}

	protected ChemicalTankMountedStorage(long capacity, ChemicalStack stack) {
		this(null/*ModBlockEntityTypes.FLUID_TANK2.get()*/, capacity, stack);
	}

	@Override
	public void unmount(Level level, BlockState state, BlockPos pos, @Nullable BlockEntity be) {
		if (be instanceof ChemicalTankBlockEntity tank && tank.isController()) {
			BasicChemicalTank inventory = tank.getTankInventory();
			// capacity shouldn't change, leave it
			inventory.setStack(this.getWrapped().getStack());
		}
	}

	public ChemicalStack getFluid() {
		return this.getWrapped().getStack();
	}

	public long getCapacity() {
		return this.getWrapped().getCapacity();
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	@Override
	public void markClean() {
		this.dirty = false;
	}

	@Override
	public void afterSync(Contraption contraption, BlockPos localPos) {
		BlockEntity be = contraption.presentBlockEntities.get(localPos);
		if (!(be instanceof ChemicalTankBlockEntity tank))
			return;

		BasicChemicalTank inv = tank.getTankInventory();
		inv.setStack(this.getFluid());
		float fillLevel = inv.getStored() / (float) inv.getCapacity();
		if (tank.getChemicalLevel() == null) {
			tank.setChemicalLevel(LerpedFloat.linear().startWithValue(fillLevel));
		}
		tank.getChemicalLevel().chase(fillLevel, 0.5, LerpedFloat.Chaser.EXP);
	}

	public static ChemicalTankMountedStorage fromTank(ChemicalTankBlockEntity tank) {
		// tank has update callbacks, make an isolated copy
		BasicChemicalTank inventory = tank.getTankInventory();
		return new ChemicalTankMountedStorage(inventory.getCapacity(), inventory.getStack().copy());
	}

	public static ChemicalTankMountedStorage fromLegacy(HolderLookup.Provider registries, CompoundTag nbt) {
		int capacity = nbt.getInt("Capacity");
		ChemicalStack fluid = ChemicalStack.parseOptional(registries, nbt);
		return new ChemicalTankMountedStorage(capacity, fluid);
	}

	public static final class Handler extends BasicChemicalTank {
		public Runnable onChange = () -> {};

		public Handler(long capacity, ChemicalStack stack) {
			super(capacity, ConstantPredicates.alwaysTrueBi(), ConstantPredicates.alwaysTrueBi(),
				ConstantPredicates.alwaysTrue(), null, () -> {}, null);
			this.setStack(stack);
		}

		@Override
		public void onContentsChanged() {
			super.onContentsChanged();
			onChange.run();
		}
	}
}
