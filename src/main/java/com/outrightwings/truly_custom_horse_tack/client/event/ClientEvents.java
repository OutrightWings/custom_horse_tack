package com.outrightwings.truly_custom_horse_tack.client.event;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import com.outrightwings.truly_custom_horse_tack.client.renderer.HeadStandBlockEntityRenderer;
import com.outrightwings.truly_custom_horse_tack.client.renderer.HeadStandWallBlockEntityRenderer;
import com.outrightwings.truly_custom_horse_tack.client.renderer.SaddleRackBlockEntityRenderer;
import com.outrightwings.truly_custom_horse_tack.client.renderer.SaddleRackWallBlockEntityRenderer;
import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlocks.HEAD_STAND_BE.get(),HeadStandBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.SADDLE_RACK_BE.get(), SaddleRackBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.HEAD_STAND_WALL_BE.get(), HeadStandWallBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.SADDLE_RACK_WALL_BE.get(), SaddleRackWallBlockEntityRenderer::new);
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event){
        ItemColors itemColors = event.getItemColors();

        itemColors.register((stack, tintIndex) -> {
                    return tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack);
                },
                ModItems.MIXABLE_DYE.get());

    }
}
