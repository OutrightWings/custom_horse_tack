package com.outrightwings.truly_custom_horse_tack.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RackWallModel extends Model {
    private final ModelPart stand;
    private final ModelPart body;
    public RackWallModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.stand = root.getChild("stand");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create().texOffs(1, 1).addBox(-4.5F, -9.0F, -8.0F, 9.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(24, 20).addBox(-5.5F, -12.0F, 6.0F, 11.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = stand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 21).addBox(-1.0F, -3.0F, -6.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.5F, -0.7854F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-5.0F, -2.1F, -19.0F, 10.0F, 10.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 5.0F));

        PartDefinition horseSaddleBottom = body.addOrReplaceChild("horseSaddleBottom", CubeListBuilder.create().texOffs(80, 0).addBox(-5.0F, 6.0F, -3.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -7.0F));

        PartDefinition horseSaddleFront = horseSaddleBottom.addOrReplaceChild("horseSaddleFront", CubeListBuilder.create().texOffs(106, 9).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseSaddleBack = horseSaddleBottom.addOrReplaceChild("horseSaddleBack", CubeListBuilder.create().texOffs(80, 9).addBox(-4.0F, 5.0F, 3.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseLeftSaddleRope = horseSaddleBottom.addOrReplaceChild("horseLeftSaddleRope", CubeListBuilder.create().texOffs(70, 0).addBox(-0.5F, 6.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.0F, 0.0F));

        PartDefinition horseLeftSaddleMetal = horseLeftSaddleRope.addOrReplaceChild("horseLeftSaddleMetal", CubeListBuilder.create().texOffs(74, 0).addBox(-0.5F, 12.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition horseRightSaddleRope = horseSaddleBottom.addOrReplaceChild("horseRightSaddleRope", CubeListBuilder.create().texOffs(80, 0).addBox(-0.5F, 6.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.0F, 0.0F));

        PartDefinition horseRightSaddleMetal = horseRightSaddleRope.addOrReplaceChild("horseRightSaddleMetal", CubeListBuilder.create().texOffs(74, 4).addBox(-0.5F, 12.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        stand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
