package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.RackWallModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SaddleRackWallBlockEntityRenderer extends DisplayModelEntityRenderer{
    public SaddleRackWallBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.displayModel = new RackWallModel(RackWallModel.createBodyLayer().bakeRoot());
        this.standTexture = new ResourceLocation(Main.MODID,"textures/entity/rack.png");
    }
}
