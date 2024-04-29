package com.outrightwings.truly_custom_horse_tack.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "travelRidden",at=@At(value= "INVOKE",shift=At.Shift.AFTER,target = "Lnet/minecraft/world/entity/LivingEntity;tickRidden(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/Vec3;)V"),cancellable = true,locals = LocalCapture.CAPTURE_FAILHARD)
    public void travelRidden(Player player, Vec3 inputVec, CallbackInfo ci, Vec3 inputPlayer){
        if(isFallFlying() && isControlledByLocalInstance()){
            this.setSpeed(this.getRiddenSpeed(player));
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.02f,1.02f,1.02f));
            this.travel(inputPlayer);
            ci.cancel();
        }
    }
    @Shadow
    public boolean isFallFlying() {
        return true;
    }

    @Shadow
    public void setSpeed(float riddenSpeed) {
    }

    @Shadow
    protected float getRiddenSpeed(Player player) {
        return 0;
    }

    @Shadow
    public void travel(Vec3 inputPlayer) {
    }

    @Shadow
    protected void defineSynchedData() {

    }

    @Shadow
    public void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Shadow
    public void addAdditionalSaveData(CompoundTag p_20139_) {

    }
}
