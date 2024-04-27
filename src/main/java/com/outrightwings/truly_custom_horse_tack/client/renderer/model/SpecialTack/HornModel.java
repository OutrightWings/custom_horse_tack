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
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.client.renderer.HorseGeneticModel;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@OnlyIn(Dist.CLIENT)
public class HornModel extends SpecialTackModel {
    final ModelPart horn;
    private static final ResourceLocation texture = new ResourceLocation(Main.MODID,"textures/entity/horse/special_tack/horn.png");

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
    public void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay,float ticks,float limbSwing,float limbSwingAmount) {

        float bodyRotation = HorseModelRotationFix.updateHorseRotation(entityIn.yBodyRotO, entityIn.yBodyRot, ticks);
        float headRotation = HorseModelRotationFix.updateHorseRotation(entityIn.yHeadRotO, entityIn.yHeadRot, ticks);
        float interpolatedPitch = entityIn.xRotO + (entityIn.getXRot() - entityIn.xRotO) * ticks;
        float f4 = interpolatedPitch * 0.017453292F;
        if (limbSwingAmount > 0.2F)
        {
            f4 += Mth.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
        }

        float headRelativeRotation = headRotation - bodyRotation;
        headRelativeRotation = headRelativeRotation > 20.0F ? 20 : headRelativeRotation;
        headRelativeRotation = headRelativeRotation < -20.0F ? -20 : headRelativeRotation;

        float grassEatingAmount = entityIn.getEatAnim(ticks);
        float rearingAmount = entityIn.getStandAnim(ticks);
        float neckBend = rearingAmount + 1.0F - Math.max(rearingAmount, grassEatingAmount);

        this.horn.setPos(0.0F, 4.0F, -10.0F);
        this.horn.xRot = rearingAmount * (0.2617994F + f4) + grassEatingAmount * 2.1816616F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * 0.5235988F + f4;
        this.horn.yRot = neckBend * headRelativeRotation * 0.017453292F;
        this.horn.y = rearingAmount * -6.0F + grassEatingAmount * 11.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * 4.0F;
        this.horn.z = rearingAmount * -1.0F + grassEatingAmount * -10.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * -10.0F;
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        horn.render(poseStack,vertexConsumer,light,overlay);

    }
}
