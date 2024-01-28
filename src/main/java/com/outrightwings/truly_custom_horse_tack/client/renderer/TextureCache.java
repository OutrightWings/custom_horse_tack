package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.google.common.collect.Maps;
import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.client.renderer.CustomLayeredTexture;
import sekelsta.horse_colors.client.renderer.TextureLayer;

import java.util.Map;
@OnlyIn(Dist.CLIENT)
public class TextureCache {
    private static final Map<CompoundTag, ResourceLocation> LAYER_CACHE = Maps.newHashMap();

    public static ResourceLocation getTexture(ItemStack armor) {
        if(armor.getItem() instanceof CustomTackItem custom){
            ResourceLocation resourcelocation = LAYER_CACHE.get(armor.getTag());
            if (resourcelocation == null) {
                TextureLayer l = custom.getTextureLayers(armor);
                resourcelocation = new ResourceLocation(l.getUniqueName());
                Minecraft.getInstance().getTextureManager().register(resourcelocation, new CustomLayeredTexture(custom.getTextureLayers(armor)));
                LAYER_CACHE.put(armor.getTag(), resourcelocation);
            }
            return resourcelocation;
        }
        return null;
    }
}
