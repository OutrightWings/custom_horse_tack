package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.client.renderer.TextureLayer;
import sekelsta.horse_colors.client.renderer.TextureLayerGroup;
import sekelsta.horse_colors.util.Color;

import java.util.List;

public class CustomTackItem extends HorseArmorItem {
    ResourceLocation EMPTY_LAYER = new ResourceLocation(Main.MODID,"textures/entity/horse/armor/custom_tack.png");
    public CustomTackItem(int protection, ResourceLocation name, Properties properties) {
        super(protection, name, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState block = context.getLevel().getBlockState(context.getClickedPos());
        if(block.is(Blocks.WATER_CAULDRON)){
            boolean removed = TackPattern.removeLastPattern(context.getItemInHand().getTag());
            if(removed){
                LayeredCauldronBlock.lowerFillLevel(block,context.getLevel(),context.getClickedPos());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
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
        if(TackPattern.getPatternListSize(tagData) <= 0){
            list.add(Component.translatable(String.format("tooltip.%s.%s", Main.MODID,"no_pattern")).withStyle(ChatFormatting.GRAY));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public TextureLayerGroup getTextureLayers(ItemStack stack){
        TextureLayerGroup layerGroup = new TextureLayerGroup();

        CompoundTag tagData = stack.getTag();
        //This is to prevent crashing by  always having a blank base layer
        layerGroup.add(buildLayer(EMPTY_LAYER.toString(), Color.WHITE));
        for(int i = 0; i < TackPattern.getPatternListSize(tagData);i++) {
            Tuple<Integer, String> colorPattern = TackPattern.getColorAndPatternByIndex(tagData, i);

            float[] rawColor = TackPattern.getColorFromColorTag(colorPattern.getA());
            Color color = new Color(rawColor[0],rawColor[1],rawColor[2]);

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
