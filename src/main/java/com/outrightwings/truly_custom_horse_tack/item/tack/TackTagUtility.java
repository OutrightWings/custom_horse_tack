package com.outrightwings.truly_custom_horse_tack.item.tack;

import com.outrightwings.truly_custom_horse_tack.client.item.tack.TackModels;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.SpecialTackModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

public class TackTagUtility {
    public static Tuple<Integer,String> getColorAndPatternByIndex(CompoundTag patternList, int index){
        if(patternList != null){
            ListTag listtag = patternList.getList("Patterns", 10);
            CompoundTag tag = (CompoundTag) listtag.get(index);
            int colorTag = tag.getInt("Color");
            String patternTag = tag.getString("Pattern");
            return new Tuple<Integer,String>(colorTag,patternTag);
        }
        return null;
    }
    public static boolean removeLastPattern(CompoundTag patternList){
        if(patternList != null){
            ListTag listtag = patternList.getList("Patterns", 10);
            int length = listtag.size()-1;
            if(length >= 0){
                listtag.remove(length);
                return true;
            }
        }
        return false;
    }
    public static int getPatternListSize(CompoundTag patternList){
        ListTag listtag = null;
        if (patternList != null && patternList.contains("Patterns", 9)) {
            listtag = patternList.getList("Patterns", 10);
        }
        return listtag != null ? listtag.size() : -1;
    }
    public static float[] getColorFromColorTag(int colID){
        if(colID < DyeColor.values().length){
            DyeColor dye = DyeColor.byId(colID);
            return dye.getTextureDiffuseColors();
        }
        else{
            float[] colors = new float[3];
            colors[0] = (float)(colID >> 16 & 255) / 255.0F;
            colors[1] = (float)(colID >> 8 & 255) / 255.0F;
            colors[2] = (float)(colID & 255) / 255.0F;
            return colors;
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static ArrayList<Tuple<SpecialTackModel,float[]>> getModels(CompoundTag patternList){
        ArrayList<Tuple<SpecialTackModel,float[]>> list = new ArrayList<>();
        ListTag listtag = null;
        if (patternList != null && patternList.contains("Patterns", 9)) {
            listtag = patternList.getList("Patterns", 10);
        }
        if(listtag != null){
            listtag.forEach(tag -> {
                SpecialTackModel model;
                float[] color;
                TackPattern pattern = TackPattern.getTackPattern(((CompoundTag)tag).getString("Pattern"));
                model = pattern != null ? TackModels.getModel(pattern.name): null;
                color = getColorFromColorTag(((CompoundTag)tag).getInt("Color"));
                if(model != null){
                    list.add(new Tuple<>(model,color));
                }
            });
        }
        return list;
    }
    public static boolean has(CompoundTag patternList, String check){
        boolean has = false;
        ListTag listtag = null;
        if (patternList != null && patternList.contains("Patterns", 9)) {
            listtag = patternList.getList("Patterns", 10);
        }
        if(listtag != null){
            for (Tag tag : listtag) {
                if (((CompoundTag)tag).getString("Pattern").contains(check)) {
                    has = true;
                    break;
                }
            }
        }
        return has;
    }
    public static ItemStack mergeTack(ItemStack baseTack, ItemStack mergeTack){
        CompoundTag baseTackTag = baseTack.getTag();
        CompoundTag outputSlotItemTag = mergeTack.getTag();
        if(outputSlotItemTag != null){
            ListTag listtag;
            listtag = outputSlotItemTag.getList("Patterns", 10);
            ListTag existing = baseTackTag.getList("Patterns",10);
            existing.addAll(listtag);
            baseTackTag.put("Patterns",existing);
            baseTack.setTag(baseTackTag);
        }
        return baseTack;
    }
}
