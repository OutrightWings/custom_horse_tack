package com.outrightwings.truly_custom_horse_tack.item.tack;

import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.SpecialTackModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;

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
    public static ArrayList<SpecialTackModel> getModels(CompoundTag patternList){
        ArrayList<SpecialTackModel> list = new ArrayList<>();
        ListTag listtag = null;
        if (patternList != null && patternList.contains("Patterns", 9)) {
            listtag = patternList.getList("Patterns", 10);
        }
        if(listtag != null){
            listtag.forEach(tag -> {
                SpecialTackModel m = TackPattern.getTackPattern(((CompoundTag)tag).getString("Pattern")).getModel();
                if(m != null)
                    list.add(m);
            });
        }
        return list;
    }
}
