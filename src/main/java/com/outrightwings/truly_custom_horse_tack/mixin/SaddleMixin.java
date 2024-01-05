package com.outrightwings.truly_custom_horse_tack.mixin;

import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;
import sekelsta.horse_colors.util.HorseArmorer;

@Mixin(HorseArmorer.class)
public class SaddleMixin {
    @Inject(method = "getSaddleTexture(Lsekelsta/horse_colors/entity/AbstractHorseGenetic;)Lnet/minecraft/resources/ResourceLocation;",at=@At("HEAD"),cancellable = true,remap = false)
    private static void getSaddle(AbstractHorseGenetic horse, CallbackInfoReturnable<ResourceLocation> cir){
        if(horse.getArmor().getItem() instanceof CustomTackItem){
            cir.setReturnValue(null);
        }
    }
}
