package com.outrightwings.truly_custom_horse_tack.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;
@OnlyIn(Dist.CLIENT)
public abstract class DisplayModel extends Model {
    public final ModelPart stand;
    public final ModelPart body;

    public DisplayModel(ModelPart root, Function<ResourceLocation, RenderType> prop) {
        super(prop);
        this.stand = getStand(root);
        this.body = getBody(root);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        stand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    protected ModelPart getStand(ModelPart root) {
        return root.getChild("stand");
    }
    protected ModelPart getBody(ModelPart root) {
        return root.getChild("body");
    }
}
