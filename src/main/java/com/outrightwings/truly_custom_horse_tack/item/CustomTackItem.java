package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class CustomTackItem extends HorseArmorItem {
    public CustomTackItem(int protection, ResourceLocation name, Properties properties) {
        super(protection, name, properties);
    }
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag){
        CompoundTag tagData =  stack.getTag();
        for(int i = 0; i < TackPattern.getPatternListSize(tagData); i++) {
            Tuple<Integer, String> colorPattern = TackPattern.getColorAndPatternByIndex(tagData, i);
            var colorName = DyeColor.byId(colorPattern.getA()).getName();
            var pattern = TackPattern.getTackPattern(colorPattern.getB());
            var patternName = pattern != null ? pattern.getSerializedName() : "unknown";

            list.add(Component.translatable(  String.format("tooltip.%s.%s.%s", Main.MODID,colorName,patternName)).withStyle(ChatFormatting.GRAY));
        }
    }
}
