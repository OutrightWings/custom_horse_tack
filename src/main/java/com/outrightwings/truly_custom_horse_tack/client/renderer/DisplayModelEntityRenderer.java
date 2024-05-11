package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.HeadStandWallBlock;
import com.outrightwings.truly_custom_horse_tack.block.SingleInventoryBlock;
import com.outrightwings.truly_custom_horse_tack.block.entity.*;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.DisplayModel;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.SpecialTackModel;
import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackTagUtility;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public abstract class DisplayModelEntityRenderer implements BlockEntityRenderer<SingleInventoryBlockEntity> {
    protected DisplayModel displayModel;
    protected ResourceLocation standTexture;
    protected ResourceLocation saddleTexture = new ResourceLocation(Main.MODID,"textures/entity/horse/armor/saddle.png");
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
        ResourceLocation texture = null;
        if(tack.is(ModItems.CUSTOM_TACK_ITEM.get())){
            texture = TextureCache.getTexture(tack);
        } else if(tack.is(Items.SADDLE)){
            texture = saddleTexture;
        }

        if(texture!=null){
            VertexConsumer vertexConsumer2 = bufferSource.getBuffer(RenderType.entityTranslucent(texture));
            this.displayModel.body.render(pose,vertexConsumer2,light,overlay,1,1,1,1);
        }
        ArrayList<Tuple<SpecialTackModel, float[]>> modelsToRender = TackTagUtility.getModels(tack.getTag());
        modelsToRender.forEach(pair -> {
            if(blockEntity instanceof HeadStandWallBlockEntity)
                pair.getA().renderOnStand(blockEntity,pose,bufferSource,light, overlay,true,pair.getB());
            else if(blockEntity instanceof HeadStandBlockEntity)
                pair.getA().renderOnStand(blockEntity,pose,bufferSource,light, overlay,false,pair.getB());
            else if(blockEntity instanceof SaddleRackWallBlockEntity)
                pair.getA().renderOnRack(blockEntity,pose,bufferSource,light, overlay,true,pair.getB());
            else if(blockEntity instanceof SaddleRackBlockEntity)
                pair.getA().renderOnRack(blockEntity,pose,bufferSource,light, overlay,false,pair.getB());
        });
        pose.popPose();
    }
}
