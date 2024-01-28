package com.outrightwings.truly_custom_horse_tack.block.entity;

import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SaddlerRackBlockEntity extends SingleInventoryBlockEntity {
    public SaddlerRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.SADDLE_RACK_BE.get(), pos, state);
    }
}
