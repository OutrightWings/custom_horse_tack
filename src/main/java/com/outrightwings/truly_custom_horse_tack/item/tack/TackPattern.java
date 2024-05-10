package com.outrightwings.truly_custom_horse_tack.item.tack;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.BellsNeckModel;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.HornModel;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.SpecialTackModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.List;

public enum TackPattern implements StringRepresentable {
    REINS("reins","none,winter,cute,reins",false,null),
    REINS_LIGHTS("reins_lights","winter,reins",false,null),
    REINS_STRIPED("reins_striped","winter,reins",false,null),
    REINS_LIGHTS_RAINBOW("reins_lights_rainbow","winter,reins",true,null),
    REINS_FLOWERS("reins_flowers","cute,reins",true,null),

    BRIDLE("bridle","none,head",true,null),
    BRIDLE_HACKAMORE("bridle_hackamore","none,head",true,null),
    BRIDLE_NOSEBAND("bridle_noseband","none,head",true,null),
    BRIDLE_SIDEPULL("bridle_sidepull","none,head",true,null),
    HALTER("halter","none,head",true,null),
    HALTER_WOOL("halter_wool","none,head",true,null),
    HALTER_WOOL_PADDING("halter_wool_padding","none,head",true,null),
    HALTER_WOOL_PADDING_WOOL("halter_wool_padding_wool","none,head",false,null),
    HEAD_HAT("head_hat","none,head",false,null),
    FLY_MASK("fly_mask","none,head",true,null),
    BRONC_HALTER("bronc_halter","none,head",true,null),
    POOFY_HOOD("poofy_hood","winter,head",true,null),
    POOFY_HOOD_FUZZ("poofy_hood_fuzz","winter,head",false,null),
    HEAD_RACE_MASK("head_race_mask","race,head",false,null),
    HEAD_RACE_MASK_BORDER("head_race_mask_border","race,head",false,null),
    HEAD_RACE_MASK_CHECKER("head_race_mask_checker","race,head",false,null),
    BOWS("bows","cute,head",false,null),
    STRIPES("stripes","cute,head",false,null),
    HORN("horn","cute,head",false,new HornModel(HornModel.createBodyLayer().bakeRoot())),
    HORN_COLOR("horn_color","cute,head",false,new HornModel(HornModel.createBodyLayer().bakeRoot())),

    SADDLE("saddle","none,body",true,null),
    SADDLE_SIDE("saddle_side","cute,body",true,null),
    SADDLE_RACE("saddle_race","race,body",true,null),
    SADDLE_RACE_STRAP("saddle_race_strap","race,body",false,null),
    PAD_SHAPED("pad_shaped","none,body",false,null),
    PAD_SHAPED_BORDER("pad_shaped_border","none,body",false,null),
    PAD_SHAPED_QUILT("pad_shaped_quilt","none,body",false,null),
    BLANKET("blanket","none,body",false,null),
    BLANKET_STRIPE("blanket_stripe","none,body",false,null),
    PAD_SQUARE("pad_square","none,body",false,null),
    PAD_SQUARE_BORDER("pad_square_border","none,body",false,null),
    PAD_SQUARE_QUILT("pad_square_quilt","none,body",false,null),
    CORD("cord","cute,body",false,null),
    KIDNEY_COVERS("kidney_covers","none,body",false,null),
    KIDNEY_COVERS_BORDER("kidney_covers_border","none,body",false,null),
    POOFY_BLANKET("poofy_blanket","winter,body",true,null),
    POOFY_BLANKET_FUZZ("poofy_blanket_fuzz","winter,body",false,null),
    POOFY_BUTT("poofy_butt","winter,body",true,null),
    POOFY_BUTT_FUZZ("poofy_butt_fuzz","winter,body",false,null),
    LARGE_PAD("large_pad","race,body",false,null),
    LARGE_PAD_BORDER("large_pad_border","race,body",false,null),
    LARGE_PAD_QUILT("large_pad_quilt","race,body",false,null),
    FLOWER_CIRCLE("flower_circle","cute,body",true,null),
    CIRCLE("circle","cute,body",false,null),
    SIMPLE_SHOULDER("shoulder_simple","cute,body",false,null),
    BELLS_NECK("bells_neck","cute,body",false, new BellsNeckModel(BellsNeckModel.createBodyLayer().bakeRoot())),

    BOOTS_BELL("boots_bell","none,feet",false,null),
    BOOTS_FETLOCK("boots_fetlock","none,feet",false,null),
    BOOTS_SHIPPING("boots_shipping","none,feet",false,null),

    RACE_NUM_1("race_1","race",false,null),
    RACE_NUM_2("race_2","race",false,null),
    RACE_NUM_3("race_3","race",false,null),
    RACE_NUM_4("race_4","race",false,null),
    RACE_NUM_5("race_5","race",false,null),
    RACE_NUM_6("race_6","race",false,null),
    RACE_NUM_7("race_7","race",false,null),
    RACE_NUM_8("race_8","race",false,null),
    RACE_NUM_9("race_9","race",false,null),
    RACE_NUM_0("race_0","race",false,null),

    //"Impossible"
    WOODEN("wood0","impossible",true,null),
    WOODEN_DARK("wood1","impossible",true,null),
    ;
    public final String name;
    private final boolean overlay;
    private final ResourceLocation armorTextureLocation;
    private final ResourceLocation patternTextureLocation;
    private final String requiredPattern;
    private final SpecialTackModel model;

    TackPattern(String name,String requiredPattern,boolean overlay, SpecialTackModel model){
        this.name = name;
        this.armorTextureLocation = new ResourceLocation(Main.MODID,"textures/entity/horse/armor/patterns/"+name+".png");
        this.patternTextureLocation = new ResourceLocation(Main.MODID,"textures/tackpatterns/"+name+".png");
        this.requiredPattern = requiredPattern;
        this.overlay = overlay;
        this.model = model;
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
    public SpecialTackModel getModel() { return model; }
}
