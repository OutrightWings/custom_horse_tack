package com.outrightwings.truly_custom_horse_tack.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class Ribbon extends Item implements DyeableLeatherItem {
    public Ribbon(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState block = context.getLevel().getBlockState(context.getClickedPos());
        if(block.is(Blocks.WATER_CAULDRON)){
            CompoundTag compoundtag = context.getItemInHand().getTagElement("display");
            if (compoundtag != null && compoundtag.contains("color")){
                this.clearColor(context.getItemInHand());
                LayeredCauldronBlock.lowerFillLevel(block,context.getLevel(),context.getClickedPos());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
