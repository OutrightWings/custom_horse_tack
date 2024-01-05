package com.outrightwings.extra_things.item;

import com.outrightwings.extra_things.Config;
import com.outrightwings.extra_things.util.PrintHorseStats;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;

public class StatPotion extends Item {
    private final boolean JUMP;
    private final boolean INCREASE;

    public StatPotion(Properties prop, boolean increase, boolean jump) {
        super(prop);
        this.JUMP = jump;
        this.INCREASE = increase;
    }
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof AbstractHorse horse) {
            if(checkPossible(this.INCREASE, this.JUMP ? Config.max_jump : Config.max_speed, (float) (this.JUMP ? horse.getAttributeValue(Attributes.JUMP_STRENGTH): horse.getAttributeValue(Attributes.MOVEMENT_SPEED)))){
                modifyEntity(horse);
                target.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (target.level.getRandom().nextFloat() * 0.4F + 0.8F));
                if (player == null || !player.getAbilities().instabuild) {
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                }
                PrintHorseStats.printHorseStatsToChat(player,target);
                return InteractionResult.sidedSuccess(player.level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
    private boolean checkPossible(boolean increase, float max_value, float currentValue){
        return (increase && max_value > currentValue) || (!increase && 0 < currentValue);
    }
    private void modifyEntity(LivingEntity target){
        float increaseBy = JUMP ? 0.1f : 0.01f;
        AttributeInstance attribute =  target.getAttribute(JUMP ? Attributes.JUMP_STRENGTH : Attributes.MOVEMENT_SPEED);

        var attributeModifier = new AttributeModifier("attributemodifier",INCREASE ? increaseBy : -increaseBy, AttributeModifier.Operation.ADDITION);
        attribute.addPermanentModifier(attributeModifier);
    }
}
