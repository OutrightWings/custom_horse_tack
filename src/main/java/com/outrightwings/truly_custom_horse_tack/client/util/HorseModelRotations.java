package com.outrightwings.truly_custom_horse_tack.client.util;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.entity.AbstractHorseGenetic;
@OnlyIn(Dist.CLIENT)
public class HorseModelRotations {
    public static float updateHorseRotation(float prevRotation, float currentRotation, float partialTickTime) {
        float bodyRotation;
        for(bodyRotation = currentRotation - prevRotation; bodyRotation < -180.0F; ) {
            bodyRotation += 360.0F;
        }

        while(bodyRotation >= 180.0F) {
            bodyRotation -= 360.0F;
        }

        return prevRotation + partialTickTime * bodyRotation;
    }
    public static void rotateModelWithHead(ModelPart part, AbstractHorseGenetic entityIn,float ticks,float limbSwing,float limbSwingAmount){
        float bodyRotation = updateHorseRotation(entityIn.yBodyRotO, entityIn.yBodyRot, ticks);
        float headRotation = updateHorseRotation(entityIn.yHeadRotO, entityIn.yHeadRot, ticks);
        float interpolatedPitch = entityIn.xRotO + (entityIn.getXRot() - entityIn.xRotO) * ticks;
        float f4 = interpolatedPitch * 0.017453292F;
        if (limbSwingAmount > 0.2F)
        {
            f4 += Mth.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
        }

        float headRelativeRotation = headRotation - bodyRotation;
        headRelativeRotation = headRelativeRotation > 20.0F ? 20 : headRelativeRotation;
        headRelativeRotation = headRelativeRotation < -20.0F ? -20 : headRelativeRotation;

        float grassEatingAmount = entityIn.getEatAnim(ticks);
        float rearingAmount = entityIn.getStandAnim(ticks);
        float neckBend = rearingAmount + 1.0F - Math.max(rearingAmount, grassEatingAmount);

        part.setPos(0.0F, 4.0F, -10.0F);
        part.xRot = rearingAmount * (0.2617994F + f4) + grassEatingAmount * 2.1816616F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * 0.5235988F + f4;
        part.yRot = neckBend * headRelativeRotation * 0.017453292F;
        part.y = rearingAmount * -6.0F + grassEatingAmount * 11.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * 4.0F;
        part.z = rearingAmount * -1.0F + grassEatingAmount * -10.0F + (1.0F - Math.max(rearingAmount, grassEatingAmount)) * -10.0F;
    }
    public static void rotateModelWithBody(ModelPart part, AbstractHorseGenetic entityIn,float ticks,float limbSwing,float limbSwingAmount){
       part.setPos(-1,11,9);
       part.xRot = entityIn.getStandAnim(ticks) * -0.7853982F;
    }
    public static void rotateModelToHeadStand(ModelPart part){
        part.setPos(0.0F, 18.0F, -5.0F);

        part.xRot = 0.261799f;
        part.yRot = 0f;
        part.zRot = 0f;
    }
}
