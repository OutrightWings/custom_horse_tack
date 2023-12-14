package com.outrightwings.extra_things;

import com.mojang.logging.LogUtils;
import com.outrightwings.extra_things.block.ModBlocks;
import com.outrightwings.extra_things.item.ModItems;
import com.outrightwings.extra_things.screen.ModMenus;
import com.outrightwings.extra_things.screen.SaddlerBlockScreen;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "extra_things";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModItems.class);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event){
    }
    private void clientSetup(final FMLClientSetupEvent event){
        event.enqueueWork(
                () -> MenuScreens.register(ModMenus.SADDLER_BLOCK_MENU.get(), SaddlerBlockScreen::new)
        );
    }
    /*
        Todo:
        * Remove saddle from horse by default
        * Make reins visable in 3rd person
        * Rearrange the Saddler GUI
        * Make horse dummy in Saddler GUI

     */
}
