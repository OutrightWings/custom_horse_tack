package com.outrightwings.extra_things.item;

import com.outrightwings.extra_things.Main;
import com.outrightwings.extra_things.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab instance;

    public ModCreativeTab(int index, String label) {
        super(index, label);
    }

    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.SADDLER.get());
    }

    static {
        instance = new ModCreativeTab(CreativeModeTab.TABS.length, "tab_"+ Main.MODID);
    }
}