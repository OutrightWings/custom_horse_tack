package com.outrightwings.truly_custom_horse_tack.block.entity;

import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HeadStandBlockEntity extends BlockEntity {
    public HeadStandBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.HEAD_STAND_BE.get(), pos, state);
    }
}
