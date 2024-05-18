package com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

import java.util.function.Function;
@OnlyIn(Dist.CLIENT)
public abstract class SpecialTackModel extends Model {

    public SpecialTackModel(Function<ResourceLocation, RenderType> p_103110_) {
        super(p_103110_);
    }

    public abstract void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, float ticks, float limbSwing, float limbSwingAmount,float[] color);
    public abstract void renderOnRack(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color);
    public abstract void renderOnStand(BlockEntity blockEntity,PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color);
    @Override
    public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_, float p_103115_, float p_103116_, float p_103117_, float p_103118_) {

    }
}