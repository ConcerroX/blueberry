package com.jerry.mekextras.common.tile.transmitter;

import com.jerry.mekextras.api.IExtraAlloyInteraction;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ExtraTileEntityTransmitter extends TileEntityTransmitter implements IExtraAlloyInteraction {

    public ExtraTileEntityTransmitter(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected Transmitter<?, ?, ?> createTransmitter(Holder<Block> blockProvider) {
        return null;
    }

    @Override
    public TransmitterType getTransmitterType() {
        return null;
    }

    public static void tickServer(Level level, BlockPos blockPos, BlockState blockState, ExtraTileEntityTransmitter entityTransmitter) {
        entityTransmitter.onUpdateServer();
    }

}
