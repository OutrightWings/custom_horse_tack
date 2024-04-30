package com.outrightwings.truly_custom_horse_tack.mixin;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.client.renderer.TextureCache;
import com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack.*;
import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackTagUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sekelsta.horse_colors.client.renderer.CustomLayeredTexture;
import sekelsta.horse_colors.client.renderer.HorseArmorLayer;
import sekelsta.horse_colors.client.renderer.HorseGeneticModel;
import sekelsta.horse_colors.client.renderer.TextureLayer;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

import java.util.ArrayList;
import java.util.Map;

@Mixin(HorseArmorLayer.class)
public class HorseArmorRendererMixin {
    @Final
    @Shadow(remap = false)
    private HorseGeneticModel<AbstractHorseGenetic> horseModel;
    private static final FlagModel flagModel = new FlagModel(FlagModel.createBodyLayer().bakeRoot());
    private static final HornModel hornModel = (HornModel) TackPattern.getTackPattern("horn").getModel();
    private static final WingsModel wingsModel = new WingsModel(WingsModel.createBodyLayer().bakeRoot());

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILsekelsta/horse_colors/entity/AbstractHorseGenetic;FFFFFF)V", at = @At(value = "HEAD"),remap = false)
    public void renderExtraModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractHorseGenetic entityIn, float limb_swing, float limb_swing_amount, float ticks, float f4, float f5, float f6, CallbackInfo ci){
        entityIn.getArmorSlots().forEach(item  -> {
            if(item.getItem()  instanceof BannerItem){
                flagModel.renderOnHorse(entityIn,poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,ticks,limb_swing,limb_swing_amount,null);
            }
            else if(item.is(Items.END_ROD)){
                hornModel.renderOnHorse(entityIn,poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,ticks,limb_swing,limb_swing_amount,null);
            } else if(item.is(Items.ELYTRA)){
                wingsModel.renderOnHorse(entityIn,poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,ticks,limb_swing,limb_swing_amount,null);
            }
        });
    }
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILsekelsta/horse_colors/entity/AbstractHorseGenetic;FFFFFF)V", at = @At(value = "INVOKE",target = "Lsekelsta/horse_colors/client/renderer/HorseGeneticModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"),locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractHorseGenetic entityIn, float limb_swing, float limb_swing_amount, float ticks, float f4, float f5, float f6, CallbackInfo ci, ItemStack itemstack, Item armor, ResourceLocation textureLocation, float r, float g, float b){
        if(armor instanceof DyeableHorseArmorItem){
            renderTextureOnHorse(bufferSource,poseStack,packedLight,r,g,b,textureLocation,false);
            renderTextureOnHorse(bufferSource,poseStack,packedLight,1,1,1,textureLocation,true);
        }else if(armor instanceof BlockItem){
            renderTextureOnHorse(bufferSource,poseStack,packedLight,r,g,b,textureLocation,false);
        }else if(armor instanceof CustomTackItem){
            ResourceLocation customTackCached = TextureCache.getTexture(entityIn.getArmor());
            if(customTackCached != null){
                renderTextureOnHorse(bufferSource, poseStack, packedLight, 1, 1, 1, customTackCached, false);
            }
            ArrayList<Tuple<SpecialTackModel, float[]>> modelsToRender = TackTagUtility.getModels(entityIn.getArmor().getTag());
            modelsToRender.forEach(pair -> {
                pair.getA().renderOnHorse(entityIn,poseStack,bufferSource,packedLight,OverlayTexture.NO_OVERLAY,ticks,limb_swing,limb_swing_amount,pair.getB());
            });
        }else{
            renderTextureOnHorse(bufferSource,poseStack,packedLight,1,1,1,textureLocation,false);
        }
        ci.cancel();
    }
    public void renderTextureOnHorse(MultiBufferSource renderTypeBuffer, PoseStack stack, int light, float r, float g, float b, ResourceLocation texture, boolean calculateOverlayPath){
        VertexConsumer vertexconsumer;
        if(calculateOverlayPath){
            String path = texture.getPath();
            vertexconsumer = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(texture.getNamespace(),path.substring(0, path.lastIndexOf('.'))+"_overlay.png")));
        } else{
            vertexconsumer = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(texture));
        }

        horseModel.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }
}