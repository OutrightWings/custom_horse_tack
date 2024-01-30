package com.outrightwings.truly_custom_horse_tack.client.event;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import com.outrightwings.truly_custom_horse_tack.client.renderer.HeadStandBlockEntityRenderer;
import com.outrightwings.truly_custom_horse_tack.client.renderer.SaddleRackBlockEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlocks.HEAD_STAND_BE.get(),HeadStandBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.SADDLE_RACK_BE.get(), SaddleRackBlockEntityRenderer::new);
    }
}
