package com.outrightwings.truly_custom_horse_tack.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

@Mixin(AbstractHorseGenetic.class)
public class AbstractHorseGeneticMixin extends Mob {
    protected AbstractHorseGeneticMixin(EntityType<? extends Mob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Inject(method = "itemInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Z", at = @At(value = "HEAD"),remap = false,cancellable = true)
    public void interact(Player player, ItemStack itemstack, InteractionHand hand, CallbackInfoReturnable<Boolean> cir){
        if(itemstack.getItem() instanceof BannerItem){
            this.setItemSlot(EquipmentSlot.LEGS,itemstack.split(1));
            cir.setReturnValue(true);
        } else if (itemstack.is(Items.END_ROD)) {
            this.setItemSlot(EquipmentSlot.HEAD,itemstack.split(1));
            cir.setReturnValue(true);
        }
    }
}
