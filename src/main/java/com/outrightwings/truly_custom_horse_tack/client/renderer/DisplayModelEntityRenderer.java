package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
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

@OnlyIn(Dist.CLIENT)
public abstract class DisplayModelEntityRenderer implements BlockEntityRenderer<SingleInventoryBlockEntity> {
    protected DisplayModel displayModel;
    protected ResourceLocation standTexture;
    @Override
    public void render(SingleInventoryBlockEntity blockEntity, float ticks, PoseStack pose, MultiBufferSource bufferSource, int light, int overlay) {
        //rotate
        pose.pushPose();
        pose.mulPose(Vector3f.ZP.rotationDegrees(180));
        pose.translate(-0.5,-1.5,0.5);
        float deg = ((SingleInventoryBlock)blockEntity.getBlockState().getBlock()).getRotation(blockEntity.getBlockState());
        pose.mulPose(Vector3f.YP.rotationDegrees(deg));
        //Stand
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(standTexture));
        this.displayModel.stand.render(pose,vertexConsumer,light,overlay,1,1,1,1);
        //Tack
        ItemStack tack = blockEntity.getItem(0);
        ResourceLocation texture = TextureCache.getTexture(tack);
        if(texture!=null){
            VertexConsumer vertexConsumer1 = bufferSource.getBuffer(RenderType.entityTranslucent(texture));
            this.displayModel.body.render(pose,vertexConsumer1,light,overlay,1,1,1,1);
        }
        pose.popPose();
    }
}
