package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.SaddleRack;
import com.outrightwings.truly_custom_horse_tack.block.entity.HeadStandBlockEntity;
import com.outrightwings.truly_custom_horse_tack.block.entity.SaddlerRackBlockEntity;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.RackModel;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.RackWallModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SaddleRackBlockEntityRenderer implements BlockEntityRenderer<SaddlerRackBlockEntity> {
    private final RackModel rackModel;
    private final RackWallModel rackWallModel;
    public SaddleRackBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.rackModel = new RackModel(RackModel.createBodyLayer().bakeRoot());
        this.rackWallModel = new RackWallModel(RackWallModel.createBodyLayer().bakeRoot());
    }
    @Override
    public void render(SaddlerRackBlockEntity blockEntity, float ticks, PoseStack pose, MultiBufferSource bufferSource, int light, int overlay) {
        pose.pushPose();
        pose.mulPose(Vector3f.ZP.rotationDegrees(180));
        pose.translate(-0.5,-1.5,0.5);
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(new ResourceLocation(Main.MODID,"textures/entity/rack.png")));
        this.rackModel.renderToBuffer(pose,vertexConsumer,light,overlay,1,1,1,1);
        pose.popPose();
    }
}
