package com.outrightwings.truly_custom_horse_tack.util;

public class HorseModelRotationFix {
    public static float updateHorseRotation(float prevRotation, float currentRotation, float partialTickTime) {
        float bodyRotation;
        for(bodyRotation = currentRotation - prevRotation; bodyRotation < -180.0F; bodyRotation += 360.0F) {
        }

        while(bodyRotation >= 180.0F) {
            bodyRotation -= 360.0F;
        }

        return prevRotation + partialTickTime * bodyRotation;
    }
}
