package com.outrightwings.truly_custom_horse_tack.client.item.tack;

import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.*;
import net.minecraft.util.StringRepresentable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum TackModels implements StringRepresentable {
    BELLS_NECK("bells_neck", new BellsNeckModel(BellsNeckModel.createBodyLayer().bakeRoot())),
    BELLS_REAR("bells_rear", new BellsRearModel(BellsRearModel.createBodyLayer().bakeRoot())),
    WINGS("wings", new WingsModel(WingsModel.createBodyLayer().bakeRoot(), WingsModel.WING_TYPE.ITEM)),
    WINGS_DYED("wings_dyed",new WingsModel(WingsModel.createBodyLayer().bakeRoot(), WingsModel.WING_TYPE.DYED)),
    WINGS_BUTTERFLY("wings_butterfly",new WingsModel(WingsModel.createBodyLayer().bakeRoot(), WingsModel.WING_TYPE.BUTTERFLY)),

    HORN("horn",new HornModel(HornModel.createBodyLayer().bakeRoot(), HornModel.HORN_TYPE.ITEM)),
    HORN_COLOR("horn_color",new HornModel(HornModel.createBodyLayer().bakeRoot(), HornModel.HORN_TYPE.DYED)),
    RIBBON_EAR_RIGHT("ribbon_ear",new RibbonModel(RibbonModel.createBodyLayerRight().bakeRoot())),
    RIBBON_EAR_LEFT("ribbon_ear_left",new RibbonModel(RibbonModel.createBodyLayerLeft().bakeRoot())),
    ;
    public final String name;
    private final SpecialTackModel model;
    TackModels(String name, SpecialTackModel model){
        this.name = name;
        this.model = model;
    }

    @Override
    public java.lang.String getSerializedName() {
        return name;
    }
    public static SpecialTackModel getModel(String name) {
        for(TackModels tack : values()){
            if(tack.name.equals(name))
                return tack.model;
        }
        return null;
    }
}
