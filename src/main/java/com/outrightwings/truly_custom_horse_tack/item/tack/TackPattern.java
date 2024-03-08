package com.outrightwings.truly_custom_horse_tack.item.tack;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.List;

public enum TackPattern implements StringRepresentable {
    REINS("reins","none,reins",true),
    REINS_LIGHTS("reins_lights","winter,reins",false),
    REINS_STRIPED("reins_striped","winter,reins",false),
    REINS_LIGHTS_RAINBOW("reins_lights_rainbow","impossible,reins",true),
    REINS_FLOWERS("reins_flowers","cute,reins",true),

    BRIDLE("bridle","none,head",true),
    HALTER("halter","none,head",true),
    HEAD_HAT("head_hat","none,head",false),
    FLY_MASK("fly_mask","none,head",true),
    BRONC_HALTER("bronc_halter","none,head",true),
    POOFY_HOOD("poofy_hood","winter,head",true),
    POOFY_HOOD_FUZZ("poofy_hood_fuzz","winter,head",false),
    HEAD_RACE_MASK("head_race_mask","race,head",false),
    HEAD_RACE_MASK_BORDER("head_race_mask_border","race,head",false),
    HEAD_RACE_MASK_CHECKER("head_race_mask_checker","race,head",false),
    BOWS("bows","cute,head",false),
    STRIPES("stripes","cute,head",false),

    SADDLE("saddle","none,body",true),
    PAD_SHAPED("pad_shaped","none,body",false),
    PAD_SHAPED_BORDER("pad_shaped_border","none,body",false),
    PAD_SHAPED_QUILT("pad_shaped_quilt","none,body",false),
    BLANKET("blanket","none,body",false),
    BLANKET_STRIPE("blanket_stripe","none,body",false),
    PAD_SQUARE("pad_square","none,body",false),
    PAD_SQUARE_BORDER("pad_square_border","none,body",false),
    PAD_SQUARE_QUILT("pad_square_quilt","none,body",false),
    CORD("cord","cute,body",false),
    KIDNEY_COVERS("kidney_covers","none,body",false),
    KIDNEY_COVERS_BORDER("kidney_covers_border","none,body",false),
    POOFY_BLANKET("poofy_blanket","winter,body",true),
    POOFY_BLANKET_FUZZ("poofy_blanket_fuzz","winter,body",false),
    POOFY_BUTT("poofy_butt","winter,body",true),
    POOFY_BUTT_FUZZ("poofy_butt_fuzz","winter,body",false),
    SADDLE_RACE("saddle_race","race,body",true),
    SADDLE_RACE_STRAP("saddle_race_strap","race,body",false),
    LARGE_PAD("large_pad","race,body",false),
    LARGE_PAD_BORDER("large_pad_border","race,body",false),
    LARGE_PAD_QUILT("large_pad_quilt","race,body",false),
    FLOWER_CIRCLE("flower_circle","cute,body",true),
    CIRCLE("circle","cute,body",false),
    SIMPLE_SHOULDER("shoulder_simple","cute,body",false),

    BOOTS_BELL("boots_bell","none,feet",false),
    BOOTS_FETLOCK("boots_fetlock","none,feet",false),
    BOOTS_SHIPPING("boots_shipping","none,feet",false),

    RACE_NUM_1("race_1","race",false),
    RACE_NUM_2("race_2","race",false),
    RACE_NUM_3("race_3","race",false),
    RACE_NUM_4("race_4","race",false),
    RACE_NUM_5("race_5","race",false),
    RACE_NUM_6("race_6","race",false),
    RACE_NUM_7("race_7","race",false),
    RACE_NUM_8("race_8","race",false),
    RACE_NUM_9("race_9","race",false),
    RACE_NUM_0("race_0","race",false),

    //"Impossible"
    WOODEN("wood0","impossible",true),
    WOODEN_DARK("wood1","impossible",true),
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
        return Arrays.stream(values()).filter(tackPattern -> tackPattern.requiredPattern.contains(requiredPattern)).toList();
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
