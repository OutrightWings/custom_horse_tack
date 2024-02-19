package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab instance;

    public ModCreativeTab(int index, String label) {
        super(CreativeModeTab.builder());
    }

    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.SADDLER.get());
    }

    static {
        instance = new ModCreativeTab(CreativeModeTabs.allTabs().size(), "tab_"+ Main.MODID);
    }
}