package com.outrightwings.truly_custom_horse_tack.client.renderer.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeadStandWallModel extends DisplayModel{
    public HeadStandWallModel(ModelPart root) {
        super(root, RenderType::entityCutoutNoCull);
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = stand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 80).addBox(-6.0F, 7.0F, 2.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r2 = stand.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(25, 73).addBox(-1.5F, -7.0F, -7.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition body = stand.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 2.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -10.0F, -1.5F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -7.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition upperMouth = head.addOrReplaceChild("upperMouth", CubeListBuilder.create().texOffs(24, 18).addBox(-2.0F, -9.99F, -7.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lowerMouth = head.addOrReplaceChild("lowerMouth", CubeListBuilder.create().texOffs(24, 27).addBox(-2.0F, -7.0F, -6.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseLeftEar = head.addOrReplaceChild("horseLeftEar", CubeListBuilder.create().texOffs(0, 0).addBox(0.45F, -12.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseRightEar = head.addOrReplaceChild("horseRightEar", CubeListBuilder.create().texOffs(0, 0).addBox(-2.45F, -12.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseLeftFaceMetal = head.addOrReplaceChild("horseLeftFaceMetal", CubeListBuilder.create().texOffs(74, 13).addBox(1.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseRightFaceMetal = head.addOrReplaceChild("horseRightFaceMetal", CubeListBuilder.create().texOffs(74, 13).addBox(-2.5F, -8.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseFaceRopes = head.addOrReplaceChild("horseFaceRopes", CubeListBuilder.create().texOffs(80, 12).addBox(-2.5F, -10.1F, -7.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 12).addBox(-2.05F, -9.8F, -2.0F, 4.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition horseLeftRein = neck.addOrReplaceChild("horseLeftRein", CubeListBuilder.create().texOffs(44, 10).addBox(2.6F, -6.0F, -6.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition horseRightRein = neck.addOrReplaceChild("horseRightRein", CubeListBuilder.create().texOffs(44, 5).addBox(-2.6F, -6.0F, -6.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition mane = neck.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(58, 0).addBox(-1.0F, -11.5F, 6.0F, 2.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    protected ModelPart getBody(ModelPart root) {
        return root.getChild("stand");
    }
}
