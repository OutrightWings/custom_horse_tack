package com.outrightwings.truly_custom_horse_tack.util;

public class ColorConverter {
    public static float[] decToRGB(int rawColor){
        float[] color = new float[3];
        color[0] = (float) (rawColor >> 16 & 255) / 255.0F;
        color[1] = (float) (rawColor >> 8 & 255) / 255.0F;
        color[2] = (float) (rawColor & 255) / 255.0F;
        return color;
    }
}
