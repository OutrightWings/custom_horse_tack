package com.outrightwings.truly_custom_horse_tack.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
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
import sekelsta.horse_colors.client.renderer.HorseArmorLayer;
import sekelsta.horse_colors.client.renderer.HorseGeneticModel;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

@Mixin(HorseArmorLayer.class)
public class HorseArmorRendererMixin {
    @Final
    @Shadow(remap = false)
    private HorseGeneticModel<AbstractHorseGenetic> horseModel;

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILsekelsta/horse_colors/entity/AbstractHorseGenetic;FFFFFF)V", at = @At(value = "INVOKE",target = "Lsekelsta/horse_colors/client/renderer/HorseGeneticModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"),locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void render(PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int light, AbstractHorseGenetic entityIn, float f1, float f2, float f3, float f4, float f5, float f6, CallbackInfo ci, ItemStack itemstack, Item armor, ResourceLocation textureLocation, float r, float g, float b){
        if(armor instanceof DyeableHorseArmorItem){
            renderTextureOnHorse(renderTypeBuffer,matrixStack,light,r,g,b,textureLocation,false);
            renderTextureOnHorse(renderTypeBuffer,matrixStack,light,1,1,1,textureLocation,true);
        }else if(armor instanceof BlockItem){
            renderTextureOnHorse(renderTypeBuffer,matrixStack,light,r,g,b,textureLocation,false);
        }else{
            renderTextureOnHorse(renderTypeBuffer,matrixStack,light,1,1,1,textureLocation,false);
        }
        CompoundTag tagData =  itemstack.getTag();
        for(int i = 0; i < TackPattern.getPatternListSize(tagData);i++) {
            Tuple<Integer, String> colorPattern = TackPattern.getColorAndPatternByIndex(tagData, i);

            float[] color = TackPattern.getColorFromColorTag(colorPattern.getA());

            TackPattern tackPattern = TackPattern.getTackPattern(colorPattern.getB());
            if (tackPattern != null) {
                renderTextureOnHorse(renderTypeBuffer, matrixStack, light, color[0], color[1], color[2], tackPattern.getArmorTextureLocation(), false);
                ResourceLocation overlayLocation = tackPattern.getOverlayTextureLocation();
                if (overlayLocation != null) {
                    renderTextureOnHorse(renderTypeBuffer, matrixStack, light, 1, 1, 1, overlayLocation, false);
                }
            }
        }

        ci.cancel();
    }
    public void renderTextureOnHorse(MultiBufferSource renderTypeBuffer, PoseStack stack, int light, float r, float g, float b, ResourceLocation texture, boolean calculateOverlayPath){
        VertexConsumer vertexconsumer;
        if(calculateOverlayPath){
            String path = texture.getPath();
            vertexconsumer = renderTypeBuffer.getBuffer(RenderType.entityCutout(new ResourceLocation(texture.getNamespace(),path.substring(0, path.lastIndexOf('.'))+"_overlay.png")));
        } else{
            vertexconsumer = renderTypeBuffer.getBuffer(RenderType.entityCutout(texture));
        }

        horseModel.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }
}