package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.HeadStandModel;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.HeadStandWallModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeadStandBlockEntityRenderer extends DisplayModelEntityRenderer {
    public HeadStandBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.ground = new HeadStandModel(HeadStandModel.createBodyLayer().bakeRoot());
        this.wall = new HeadStandWallModel(HeadStandWallModel.createBodyLayer().bakeRoot());
        this.standTexture = new ResourceLocation(Main.MODID,"textures/entity/stand.png");
    }
}
