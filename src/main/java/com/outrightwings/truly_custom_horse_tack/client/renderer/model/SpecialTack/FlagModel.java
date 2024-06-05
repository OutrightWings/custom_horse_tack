package com.outrightwings.truly_custom_horse_tack.client.renderer.model.SpecialTack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

@OnlyIn(Dist.CLIENT)
public class FlagModel extends SpecialTackModel {
    final ModelPart flag;
    final ModelPart pole;
    final ModelPart bar;
    public FlagModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.flag = root.getChild("flag");
        this.pole = root.getChild("pole");
        this.bar = root.getChild("bar");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -0.5F, 20.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -62.0F, -1.5F, 0.0F, 0.0F, 1.5708F));
        partdefinition.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(44, 0).addBox(8.0F, -12.0F, 7.0F, 2.0F, 42.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 8.0F, -8.0F, 0.0F, 0.0F, -3.1416F));
        partdefinition.addOrReplaceChild("bar", CubeListBuilder.create().texOffs(0, 42).addBox(-19.0F, -32.0F, 7.0F, 20.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.0F, -23.0F, -8.0F, 0.0F, 0.0F, 1.5708F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    public void renderOnHorse(AbstractHorseGenetic entityIn, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay,float ticks,float limbSwing,float limbSwingAmount,float[] color) {
        ItemStack bannerItem = null;
        var slots = EquipmentSlot.values();
        for (var slot : slots) {
            if (slot == EquipmentSlot.CHEST) continue;
            ItemStack item = entityIn.getItemBySlot(slot);
            if(item.getItem()  instanceof BannerItem){
                bannerItem = item;
            }
        }

        if(bannerItem == null) return;

        poseStack.pushPose();
        poseStack.mulPose(new Quaternion(-0.03f,0.70f,-0.03f,0.70f));

        float between = entityIn.getStandAnim(ticks);
        poseStack.translate(Mth.lerp(between,0.3f,0.3f-0.43f),Mth.lerp(between,-0.2f,-0.2f-0.56f),-0.35f);

        poseStack.pushPose();
        poseStack.scale(0.6666667F, 0.6666667F, 0.6666667F);
        VertexConsumer vertexConsumer = ModelBakery.BANNER_BASE.buffer(bufferSource, RenderType::entitySolid);
        this.pole.render(poseStack,vertexConsumer,light,overlay,1,1,1,1);
        this.bar.render(poseStack,vertexConsumer,light,overlay,1,1,1,1);

        long time = Minecraft.getInstance().level.getGameTime();
        float f2 = ((float)Math.floorMod(time, 100L) + ticks) / 100.0F;
        this.flag.xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
        this.flag.x = 0;
        this.flag.y = -42.0F;
        var patterns = BannerBlockEntity.createPatterns(((BannerItem) bannerItem.getItem()).getColor() ,BannerBlockEntity.getItemPatterns(bannerItem));
        BannerRenderer.renderPatterns(poseStack, bufferSource, light, overlay, this.flag, ModelBakery.BANNER_BASE, true,patterns);
        poseStack.popPose();
        poseStack.popPose();
    }

    @Override
    public void renderOnRack(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }

    @Override
    public void renderOnStand(BlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean wall,float[] color) {

    }
}
