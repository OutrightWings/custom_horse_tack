package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class MixedDye extends Item implements DyeableLeatherItem {
    public MixedDye(Properties properties) {
        super(properties);
    }

    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag){
        CompoundTag compoundtag = stack.getTagElement("display");
        if (compoundtag != null && compoundtag.contains("color")) {
            int color = this.getColor(stack);
            int r = (color >> 16 & 255);
            int g = (color >> 8 & 255);
            int b = (color & 255);

            list.add(Component.literal(String.format("R:%d G:%d B:%d", r,g,b)).withStyle(ChatFormatting.GRAY));
        }
        else {
            list.add(Component.translatable(String.format("tooltip.%s.%s", Main.MODID,"no_dye")).withStyle(ChatFormatting.GRAY));
        }
    }
   @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundtag = stack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 16383998;
    }
}
