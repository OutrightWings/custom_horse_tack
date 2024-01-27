package com.outrightwings.truly_custom_horse_tack;

import com.mojang.logging.LogUtils;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import com.outrightwings.truly_custom_horse_tack.client.event.ClientEvents;
import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import com.outrightwings.truly_custom_horse_tack.networking.ModPacketHandler;
import com.outrightwings.truly_custom_horse_tack.screen.ModMenus;
import com.outrightwings.truly_custom_horse_tack.screen.SaddlerBlockScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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
    public static final String MODID = "truly_custom_horse_tack";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModItems.class);
        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ModPacketHandler.register();
        });
    }
    private void clientSetup(final FMLClientSetupEvent event){
        event.enqueueWork(
                () -> MenuScreens.register(ModMenus.SADDLER_BLOCK_MENU.get(), SaddlerBlockScreen::new)
        );
    }
    /*
        Todo:

     */
}
