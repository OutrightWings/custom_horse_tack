package com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.util.HorseModelRotationFix;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

public class BellsNeckModel extends SpecialTackModel {
    private final ModelPart bells;
    private final ModelPart[] sideBells;
    private static final ResourceLocation texture = new ResourceLocation(Main.MODID,"textures/entity/horse/special_tack/bell.png");

    public BellsNeckModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.bells = root.getChild("bells");
        sideBells = new ModelPart[4];
        sideBells[0] = bells.getChild("FW");
        sideBells[1] = bells.getChild("BW");
        sideBells[2] = bells.getChild("FE");
        sideBells[3] = bells.getChild("BE");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bells = partdefinition.addOrReplaceChild("bells", CubeListBuilder.create(), PartPose.offset(-1f, 11.0F, 9.0F));

        PartDefinition CE = bells.addOrReplaceChild("CE", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 2.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.5F, -19.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition CC = bells.addOrReplaceChild("CC", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 2.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, -19.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition CW = bells.addOrReplaceChild("CW", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 2.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -1.5F, -19.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition FW = bells.addOrReplaceChild("FW", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 1.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -3.0F, -16.0F));

        PartDefinition BW = bells.addOrReplaceChild("BW", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 1.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -6.0F, -13.0F));

        PartDefinition FE = bells.addOrReplaceChild("FE", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 1.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -3.0F, -16.0F));

        PartDefinition BE = bells.addOrReplaceChild("BE", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.0F, 1.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -6.0F, -13.0F));
        return LayerDefinition.create(meshdefinition, 16, 16);
    }
    public void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, float ticks, float limbSwing, float limbSwingAmount,float[] color) {
        this.bells.setPos(-1,11,9);
        float rearingAmount = entityIn.getStandAnim(ticks) * -0.7853982F;
        this.bells.xRot = rearingAmount;
        for(ModelPart sideBell : sideBells) {
            sideBell.xRot = -rearingAmount;
        }

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        bells.render(poseStack,vertexConsumer,light,overlay);
    }

    @Override
    public void renderOnRack(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {
        if(wall){
            this.bells.setPos(-1,23,5);
        }else{
            this.bells.setPos(-1,17,6);
        }
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        bells.render(poseStack,vertexConsumer,light,overlay);
    }

    @Override
    public void renderOnStand(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }
}
