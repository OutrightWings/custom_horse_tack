package com.outrightwings.truly_custom_horse_tack.mixin;

import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackTagUtility;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;

import java.util.Arrays;

@Mixin(AbstractHorseGenetic.class)
public abstract class AbstractHorseGeneticMixin extends AbstractHorse {


    @Shadow public abstract ItemStack getArmor();

    protected AbstractHorseGeneticMixin(EntityType<? extends AbstractHorse> p_30531_, Level p_30532_) {
        super(p_30531_, p_30532_);
    }

    @Inject(method = "itemInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Z", at = @At(value = "HEAD"),remap = false,cancellable = true)
    public void interact(Player player, ItemStack itemstack, InteractionHand hand, CallbackInfoReturnable<Boolean> cir){
        if(itemstack.getItem() instanceof BannerItem || itemstack.is(Items.END_ROD) || itemstack.is(ModItems.RIBBON.get())){
            var slots = EquipmentSlot.values();
            for (var slot : slots) {
                if(slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) continue;
                ItemStack itemInSlot = this.getItemBySlot(slot);
                System.out.println(itemInSlot.getItem()+" "+itemstack.getItem()+ " "+itemInSlot.is(itemstack.getItem()));
                if(itemInSlot.is(itemstack.getItem())){
                    break;
                }
                else if(itemInSlot.is(Items.AIR)){
                    this.setItemSlot(slot,itemstack.split(1));
                    break;
                }
                else if(itemInSlot.getItem() instanceof BannerItem && itemstack.getItem() instanceof BannerItem) break;
            }
            cir.setReturnValue(true);
        }
        else if (itemstack.is(Items.ELYTRA)) {
            if(getItemBySlot(EquipmentSlot.LEGS).is(Items.AIR)){
                this.setItemSlot(EquipmentSlot.LEGS,itemstack.split(1));
            }
            cir.setReturnValue(true);
        } else if (itemstack.getItem() instanceof AxeItem) {
            var slots = EquipmentSlot.values();
            for (var slot : slots) {
                if(slot == EquipmentSlot.CHEST) continue;
                ItemStack itemInSlot = this.getItemBySlot(slot);
                if(itemInSlot.is(Items.AIR)) continue;
                this.spawnAtLocation(itemInSlot.split(1));
                cir.setReturnValue(true);
            }
        }
    }

    public void updateFallFlying() {
        boolean flag = false;
        if (!this.onGround() && !this.isPassenger() && !this.hasEffect(MobEffects.LEVITATION) && this.hasControllingPassenger()) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.LEGS);
            flag = itemstack.canElytraFly(this);
            if(!flag){
                flag = TackTagUtility.hasWings(getArmor().getTag());
            }
            if (!this.level().isClientSide) {
                int nextFlightTick = this.fallFlyTicks + 1;
                if (nextFlightTick % 10 == 0) {
                    this.gameEvent(net.minecraft.world.level.gameevent.GameEvent.ELYTRA_GLIDE);
                }
            }
        }

        if (!this.level().isClientSide) {
            this.setSharedFlag(7, flag);
        }
    }
    public boolean causeFallDamage(float distance, float amount, DamageSource source) {
        if(this.getItemBySlot(EquipmentSlot.LEGS).is(Items.ELYTRA))
            return false;
        if(TackTagUtility.hasWings(getArmor().getTag()))
            return false;
        return super.causeFallDamage(distance,amount,source);
    }

}
