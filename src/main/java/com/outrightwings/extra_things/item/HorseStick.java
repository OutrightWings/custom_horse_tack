package com.outrightwings.extra_things.item;

import com.outrightwings.extra_things.util.PrintHorseStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HorseStick extends Item {
    public HorseStick(Properties prop) {
        super(prop);
    }
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        return PrintHorseStats.printHorseStatsToChat(player,target);
    }
}
