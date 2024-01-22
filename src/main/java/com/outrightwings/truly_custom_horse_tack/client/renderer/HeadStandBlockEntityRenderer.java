package com.outrightwings.truly_custom_horse_tack.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.outrightwings.truly_custom_horse_tack.block.entity.HeadStandBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class HeadStandBlockEntityRenderer implements BlockEntityRenderer<HeadStandBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    public HeadStandBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }
    @Override
    public void render(HeadStandBlockEntity blockEntity, float ticks, PoseStack pose, MultiBufferSource bufferSource, int light, int overlay) {

    }
}
