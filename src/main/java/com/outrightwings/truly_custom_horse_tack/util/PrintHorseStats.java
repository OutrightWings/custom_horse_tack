package com.outrightwings.truly_custom_horse_tack.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;

public class PrintHorseStats {
    public static InteractionResult printHorseStatsToChat(Player player, LivingEntity target) {
        if (target instanceof AbstractHorse horse) {
            if(player.level.isClientSide){
                double speed = Converter.genericSpeedToBlocPerSec(horse.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                double jumpHeight = Converter.jumpStrengthToJumpHeight(horse.getAttribute(Attributes.JUMP_STRENGTH).getValue());
                String statText = String.format("§l§n§9Horse Stats:§r\n§9Jump: §6%,.2f blocks\n§9Speed: §6%,.2f blocks/sec\n",jumpHeight,speed);
                player.sendMessage(new TextComponent(statText),player.getUUID());
                return InteractionResult.sidedSuccess(true);
            }
        }
        return InteractionResult.PASS;
    }
}
