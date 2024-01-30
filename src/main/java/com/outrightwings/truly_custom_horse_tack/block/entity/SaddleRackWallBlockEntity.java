package com.outrightwings.truly_custom_horse_tack.block.entity;

import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class SaddleRackWallBlockEntity extends SingleInventoryBlockEntity {
    public SaddleRackWallBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.SADDLE_RACK_WALL_BE.get(), pos, state);
    }

    @Override
    public boolean validItem(ItemStack item) {
        return item.getItem() instanceof CustomTackItem;
    }
}
