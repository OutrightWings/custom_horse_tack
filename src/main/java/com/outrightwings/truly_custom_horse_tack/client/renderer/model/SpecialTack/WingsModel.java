package com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.util.HorseModelRotations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

public class WingsModel extends SpecialTackModel{
    private final ModelPart wings;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private static final ResourceLocation texture = new ResourceLocation(Main.MODID,"textures/entity/horse/special_tack/wings.png");

    float outXRot = 126;
    float outYRot = 292;
    float outZRot = 125;
    float inXRot = 90;
    float inYRot = 332.5F;
    float inZRot = 270;
    float ticksToOpen = 10;
    float delayToOpen = 2;

    public WingsModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        wings = root.getChild("wings");
        rightWing = wings.getChild("right_wing");
        leftWing = wings.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(-1F, 11.0F, 9.0F));

        PartDefinition right_wing = wings.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 22).addBox(-6.0F, -2.0F, 0.0F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -7.0F, -13.0F, 1.5708F, 0.48F, 1.5708F));

        PartDefinition left_wing = wings.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, 0.0F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -7.0F, -13.0F, 1.5708F, -0.48F, -1.5708F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, float ticks, float limbSwing, float limbSwingAmount,float[] color) {
        float rearingAmount = entityIn.getStandAnim(ticks);

        float fallTicks = entityIn.getFallFlyingTicks()-delayToOpen;
        fallTicks = Mth.clamp(fallTicks,0,ticksToOpen)/ticksToOpen;

        HorseModelRotations.rotateModelWithBody(wings,entityIn,ticks,limbSwing,limbSwingAmount);
        float xRot, yRot, zRot;

        xRot = Mth.lerp(fallTicks,inXRot,outXRot);
        yRot = Mth.lerp(fallTicks,inYRot,outYRot);
        zRot = Mth.lerp(fallTicks,inZRot,outZRot);

        if(entityIn.isFallFlying()){
            long time = Minecraft.getInstance().level.getGameTime();
            float wobbleTime = ((float)Math.floorMod(time, 100L) + ticks) / 100.0F;
            float wobble = (4 * Mth.cos(((float)Math.PI * 2F) * wobbleTime)) * (float)Math.PI;
            yRot += wobble;
            xRot += wobble;
        }

        this.leftWing.setRotation(Mth.DEG_TO_RAD * xRot, Mth.DEG_TO_RAD * yRot, Mth.DEG_TO_RAD * zRot);
        this.rightWing.setRotation(Mth.DEG_TO_RAD * xRot, Mth.DEG_TO_RAD * -yRot, Mth.DEG_TO_RAD * -zRot);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        wings.render(poseStack,vertexConsumer,light,overlay);
    }

    @Override
    public void renderOnRack(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }

    @Override
    public void renderOnStand(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }
}
