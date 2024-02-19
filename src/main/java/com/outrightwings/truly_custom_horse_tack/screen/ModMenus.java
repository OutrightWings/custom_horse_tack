package com.outrightwings.truly_custom_horse_tack.screen;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MODID);
    public static final RegistryObject<MenuType<SaddlerBlockMenu>> SADDLER_BLOCK_MENU = MENUS.register("saddler_block_menu",()->new MenuType<SaddlerBlockMenu>(SaddlerBlockMenu::new, FeatureFlags.DEFAULT_FLAGS));

}
