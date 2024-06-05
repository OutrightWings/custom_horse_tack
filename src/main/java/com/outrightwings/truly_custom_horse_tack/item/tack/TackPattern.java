package com.outrightwings.truly_custom_horse_tack.item.tack;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.List;

public enum TackPattern implements StringRepresentable {
    REINS("reins","none,winter,cute,reins",false),
    REINS_LIGHTS("reins_lights","winter,reins",false),
    REINS_STRIPED("reins_striped","winter,reins",false),
    REINS_LIGHTS_RAINBOW("reins_lights_rainbow","winter,reins",true),
    REINS_FLOWERS("reins_flowers","cute,reins",true),

    BRIDLE("bridle","none,head",true),
    BRIDLE_HACKAMORE("bridle_hackamore","none,head",true),
    BRIDLE_NOSEBAND("bridle_noseband","none,head",true),
    BRIDLE_SIDEPULL("bridle_sidepull","none,head",true),
    HALTER("halter","none,head",true),
    HALTER_WOOL("halter_wool","none,head",true),
    HALTER_WOOL_PADDING("halter_wool_padding","none,head",true),
    HALTER_WOOL_PADDING_WOOL("halter_wool_padding_wool","none,head",false),
    BRONC_HALTER("bronc_halter","none,head",true),
    HEAD_HAT("head_hat","none,head",false),
    HEAD_HAT_BORDER("head_hat_border","none,head",false),
    HEAD_HAT_LONG("head_hat_long","none,head",false),
    HEAD_HAT_LONG_BORDER("head_hat_long_border","none,head",false),
    FLY_MASK("fly_mask","none,head",true),
    FLY_MASK_SLIM("fly_mask_slim","head",true),
    POOFY_HOOD("poofy_hood","winter,head",true),
    POOFY_HOOD_FUZZ("poofy_hood_fuzz","winter,head",false),
    HEAD_RACE_MASK("head_race_mask","race,head",false),
    HEAD_RACE_MASK_BORDER("head_race_mask_border","race,head",false),
    HEAD_RACE_MASK_CHECKER("head_race_mask_checker","race,head",false),
    BOWS("bows","cute,head",false),
    STRIPES("stripes","cute,head",false),
    HORN("horn","impossible",false),
    HORN_COLOR("horn_color","cute,head",false),
    RIBBON_EAR_RIGHT("ribbon_ear","cute,head",false),
    RIBBON_EAR_LEFT("ribbon_ear_left","cute,head",false),

    SADDLE("saddle","none,body",true),
    SADDLE_SIDE("saddle_side","cute,body",true),
    SADDLE_RACE("saddle_race","race,body",true),
    SADDLE_RACE_STRAP("saddle_race_strap","race,body",false),
    GIRTH("girth","body",false),
    GIRTH_WOOL("girth_wool","body",false),
    GIRTH_JUMP("girth_jump","body",false),
    PAD_SHAPED("pad_shaped","none,body",false),
    PAD_SHAPED_BORDER("pad_shaped_border","none,body",false),
    PAD_SHAPED_QUILT("pad_shaped_quilt","none,body",false),
    PAD_SQUARE("pad_square","none,body",false),
    PAD_SQUARE_BORDER("pad_square_border","none,body",false),
    PAD_SQUARE_QUILT("pad_square_quilt","none,body",false),
    PAD_DRESSAGE("pad_dressage","body",false),
    PAD_DRESSAGE_BORDER("pad_dressage_border","body",false),
    PAD_SPORT("pad_sport","body",false),
    PAD_SPORT_BORDER("pad_sport_border","body",false),
    PAD_WOOL("pad_wool","body",false),
    PAD_BAREBACK("pad_bareback","body",false),
    PAD_SHOCK("pad_shock","body",false),
    PAD_SHOCK_WOOL("pad_shock_wool","impossible",true),
    PAD_SHOCK_WOOL_PADDING("pad_shock_wool_padding","body",false),
    LARGE_PAD("large_pad","race",false),
    LARGE_PAD_BORDER("large_pad_border","race",false),
    LARGE_PAD_QUILT("large_pad_quilt","race",false),
    CORD("cord","cute,body",false),
    BLANKET("blanket","none,body",false),
    BLANKET_STRIPE("blanket_stripe","none,body",false),
    BLANKET_WATERPROOF("blanket_waterproof","body",false),
    BLANKET_WATERPROOF_BORDER("blanket_waterproof_border","body",false),
    KIDNEY_COVERS("kidney_covers","none,body",false),
    KIDNEY_COVERS_BORDER("kidney_covers_border","none,body",false),
    POOFY_BLANKET("poofy_blanket","winter,body",true),
    POOFY_BLANKET_FUZZ("poofy_blanket_fuzz","winter,body",false),
    POOFY_BUTT("poofy_butt","winter,body",true),
    POOFY_BUTT_FUZZ("poofy_butt_fuzz","winter,body",false),
    FLOWER_CIRCLE("flower_circle","cute,body",true),
    CIRCLE("circle","cute,body",false),
    REAR_CIRCLE("circle_rear","cute,body",false),
    SIMPLE_SHOULDER("shoulder_simple","cute,body",false),
    BELLS_NECK("bells_neck","cute,body",false),
    BELLS_REAR("bells_rear","cute,body",false),
    WINGS("wings","impossible",false),
    WINGS_DYED("wings_dyed","wings",false),
    WINGS_BUTTERFLY("wings_butterfly","wings",false),

    BOOTS_BELL("boots_bell","none,feet",false),
    BOOTS_FETLOCK("boots_fetlock","none,feet",false),
    BOOTS_SHIPPING("boots_shipping","none,feet",false),
    FEET_WRAPS("feet_wraps","feet",false),
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
    public final String name;
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
}
