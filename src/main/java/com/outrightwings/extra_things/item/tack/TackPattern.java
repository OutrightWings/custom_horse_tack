package com.outrightwings.extra_things.item.tack;

import com.outrightwings.extra_things.Main;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.List;

public enum TackPattern implements StringRepresentable {
    //No Pattern Needed
    BOOTS_BELL("boots_bell","none",false),
    BOOTS_FETLOCK("boots_fetlock","none",false),
    BOOTS_SHIPPING("boots_shipping","none",false),
    HEAD_HAT("head_hat","none",false),
    HEAD_RACE_MASK("head_race_mask","none",false),
    HEAD_RACE_MASK_BORDER("head_race_mask_border","none",false),
    HEAD_RACE_MASK_CHECKER("head_race_mask_checker","none",false),
    PAD_SHAPED("pad_shaped","none",false),
    PAD_SHAPED_BORDER("pad_shaped_border","none",false),
    PAD_SHAPED_QUILT("pad_shaped_quilt","none",false),
    PAD_SQUARE("pad_square","none",false),
    PAD_SQUARE_BORDER("pad_square_border","none",false),
    PAD_SQUARE_QUILT("pad_square_quilt","none",false),
    REINS("reins","none",true),
    SADDLE("saddle","none",true),
    //"Winter" Pattern Needed
    //"Racing" Pattern Needed
    //"Impossible"
    WOODEN("wood","impossible",true),
    ;
    private final String name;
    private final boolean overlay;
    private final ResourceLocation armorTextureLocation;
    private final ResourceLocation patternTextureLocation;
    private final String requiredPattern;

    TackPattern(String name,String requiredPattern,boolean overlay){
        this.name = name;
        this.armorTextureLocation = new ResourceLocation(Main.MODID,"textures/entity/horse/armor/patterns/"+name+".png");
        this.patternTextureLocation = new ResourceLocation(Main.MODID,"textures/tackpatterns/"+name+".png");
        this.requiredPattern = requiredPattern;
        this.overlay = overlay;
    }
    public static List<TackPattern> getTackPatterns(String requiredPattern){
        return Arrays.stream(values()).filter(tackPattern -> tackPattern.requiredPattern == requiredPattern).toList();
    }
    public static TackPattern getTackPattern(String patternName){
        for(TackPattern tack : values()){
            if(tack.name.equals(patternName))
                return tack;
        }
        return null;
    }
    @Override
    public String getSerializedName() {
        return name;
    }
    public ResourceLocation getArmorTextureLocation(){
        return armorTextureLocation;
    }
    public ResourceLocation getPatternIconLocation(){
        return patternTextureLocation;
    }
    public ResourceLocation getOverlayTextureLocation(){
        if(overlay)
            return new ResourceLocation(Main.MODID,"textures/entity/horse/armor/patterns/"+name+"_overlay.png");;
        return null;
    }
    public String getTranslationKey(){
        return String.format("tack.%s.pattern.%s",Main.MODID,name);
    }
    //Garbage lol
    public static Tuple<Integer,String> getColorAndPatternByIndex(CompoundTag patternList,int index){
        if(patternList != null){
            ListTag listtag = patternList.getList("Patterns", 10);
                CompoundTag tag = (CompoundTag) listtag.get(index);
                int colorTag = tag.getInt("Color");
                String patternTag = tag.getString("Pattern");
                return new Tuple<Integer,String>(colorTag,patternTag);
        }
        return null;
    }
    public static int getPatternListSize(CompoundTag patternList){
        ListTag listtag = null;
        if (patternList != null && patternList.contains("Patterns", 9)) {
            listtag = patternList.getList("Patterns", 10);
        }
        return listtag != null ? listtag.size() : -1;
    }
    public static int getColorFromColorTag(int colID){
        DyeColor dye = DyeColor.byId(colID);
        return dye.getMaterialColor().col;
    }
}
