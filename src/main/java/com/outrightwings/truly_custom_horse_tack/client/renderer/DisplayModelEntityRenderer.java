package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.block.SingleInventoryBlock;
import com.outrightwings.truly_custom_horse_tack.block.entity.SingleInventoryBlockEntity;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.DisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public abstract class DisplayModelEntityRenderer implements BlockEntityRenderer<SingleInventoryBlockEntity> {
    protected DisplayModel displayModel;
    protected ResourceLocation standTexture;
    @Override
    public void render(SingleInventoryBlockEntity blockEntity, float ticks, PoseStack pose, MultiBufferSource bufferSource, int light, int overlay) {
        //rotate
        pose.pushPose();
        pose.mulPose(new Quaternionf(0,0,1,0));
        pose.translate(-0.5,-1.5,0.5);
        float deg = ((SingleInventoryBlock)blockEntity.getBlockState().getBlock()).getRotation(blockEntity.getBlockState());
        pose.mulPose(new Quaternionf(0, 1, 0, 0).rotateAxis((float) Math.toRadians(deg+180),new Vector3f(0,1,0)));
        //Stand
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(standTexture));
        this.displayModel.stand.render(pose,vertexConsumer,light,overlay,1,1,1,1);
        //Tack
        ItemStack tack = blockEntity.getItem(0);
        ResourceLocation texture = TextureCache.getTexture(tack);
        if(texture!=null){
            VertexConsumer vertexConsumer1 = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
            this.displayModel.body.render(pose,vertexConsumer1,light,overlay,1,1,1,1);
        }
        pose.popPose();
    }
}
