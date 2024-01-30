package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.HeadStandWallModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HeadStandWallBlockEntityRenderer extends DisplayModelEntityRenderer{
    public HeadStandWallBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.displayModel = new HeadStandWallModel(HeadStandWallModel.createBodyLayer().bakeRoot());
        this.standTexture = new ResourceLocation(Main.MODID,"textures/entity/stand.png");
    }
}
