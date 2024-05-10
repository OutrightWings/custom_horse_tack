package com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.util.HorseModelRotations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

@OnlyIn(Dist.CLIENT)
public class HornModel extends SpecialTackModel {
    final ModelPart horn;
    private static final ResourceLocation baseTexture = new ResourceLocation(Main.MODID,"textures/entity/horse/special_tack/horn.png");
    private static final ResourceLocation colorTexture = new ResourceLocation(Main.MODID,"textures/entity/horse/special_tack/horn.png");

    public HornModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        horn = root.getChild("horn");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        // -0.5F, -8.5F, -2.5F
        PartDefinition horn = partdefinition.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -15.0F, 2.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -10.0F, 0.5235988F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }
    public void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay,float ticks,float limbSwing,float limbSwingAmount,float[] color) {
        HorseModelRotations.rotateModelWithHead(horn,entityIn,ticks,limbSwing,limbSwingAmount);

        VertexConsumer vertexConsumer;
        if(color != null){
            vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(colorTexture));
            horn.render(poseStack,vertexConsumer,light,overlay,color[0],color[1],color[2],1f);
        } else {
            vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(baseTexture));
            horn.render(poseStack,vertexConsumer,light,overlay);
        }

    }

    @Override
    public void renderOnRack(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }

    @Override
    public void renderOnStand(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {
        HorseModelRotations.rotateModelToHeadStand(this.horn);

        VertexConsumer vertexConsumer;
        if(color != null){
            vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(colorTexture));
            horn.render(poseStack,vertexConsumer,light,overlay,color[0],color[1],color[2],1f);
        } else {
            vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(baseTexture));
            horn.render(poseStack,vertexConsumer,light,overlay);
        }
    }
}
