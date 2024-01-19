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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.client.renderer.TextureLayer;
import sekelsta.horse_colors.client.renderer.TextureLayerGroup;
import sekelsta.horse_colors.util.Color;

import java.util.ArrayList;
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
    @OnlyIn(Dist.CLIENT)
    public TextureLayerGroup getTextureLayers(ItemStack stack){
        TextureLayerGroup layerGroup = new TextureLayerGroup();

        CompoundTag tagData = stack.getTag();
        for(int i = 0; i < TackPattern.getPatternListSize(tagData);i++) {
            Tuple<Integer, String> colorPattern = TackPattern.getColorAndPatternByIndex(tagData, i);

            int rawColor = TackPattern.getColorFromColorTag(colorPattern.getA());
            float r = (float) (rawColor >> 16 & 255) / 255.0F;
            float g = (float) (rawColor >> 8 & 255) / 255.0F;
            float b = (float) (rawColor & 255) / 255.0F;
            Color color = new Color(r,g,b);

            TackPattern tackPattern = TackPattern.getTackPattern(colorPattern.getB());
            if (tackPattern != null) {
                layerGroup.add(buildLayer(tackPattern.getArmorTextureLocation().toString(),color));
                ResourceLocation overlayLocation = tackPattern.getOverlayTextureLocation();
                if (overlayLocation != null) {
                    layerGroup.add(buildLayer(overlayLocation.toString(),Color.WHITE));
                }
            }
        }
        return layerGroup;
    }
    @OnlyIn(Dist.CLIENT)
    private TextureLayer buildLayer(String name, Color color){
        TextureLayer layer = new TextureLayer();
        layer.name = name;
        layer.color = color;
        return layer;
    }

}
