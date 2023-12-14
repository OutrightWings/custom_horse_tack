package com.outrightwings.extra_things.screen;

import com.outrightwings.extra_things.Main;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MODID);
    public static final RegistryObject<MenuType<SaddlerBlockMenu>> SADDLER_BLOCK_MENU = MENUS.register("saddler_block_menu",()->new MenuType<SaddlerBlockMenu>(SaddlerBlockMenu::new));

}
