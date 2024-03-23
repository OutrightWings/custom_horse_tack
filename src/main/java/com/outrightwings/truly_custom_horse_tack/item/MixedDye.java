package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Config;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class MixedDye extends Item implements DyeableLeatherItem{
    public MixedDye(Properties properties) {
        super(properties.durability(16));
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
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag){
        CompoundTag compoundtag = stack.getTagElement("display");
        if (compoundtag != null && compoundtag.contains("color")) {
            int color = this.getColor(stack);
            int r = (color >> 16 & 255);
            int g = (color >> 8 & 255);
            int b = (color & 255);

            list.add(Component.literal(String.format("#%s%s%s", Integer.toHexString(r),Integer.toHexString(g),Integer.toHexString(b))).withStyle(ChatFormatting.GRAY));
        }
        else {
            list.add(Component.translatable(String.format("tooltip.%s.%s", Main.MODID,"no_dye")).withStyle(ChatFormatting.GRAY));
        }
    }
//   @Override
//    public int getColor(ItemStack stack) {
//        CompoundTag compoundtag = stack.getTagElement("display");
//        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 16383998;
//    }
}
