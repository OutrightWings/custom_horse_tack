package com.outrightwings.extra_things.util;
/*
 Code from: https://github.com/d4rkm0nkey/HorseStatsVanilla under MIT License
 */
public class Converter {
    public static double jumpStrengthToJumpHeight(double strength) {
        double height = 0;
        double velocity = strength;
        while(velocity > 0) {
            height += velocity;
            velocity = (velocity - .08) * .98 * .98;
        }
        return height;
    }

    public static double genericSpeedToBlocPerSec(double speed) {
        return 42.157796 * speed;
    }

    public static double round(double n, int decimals) {
        return Math.floor(n * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }
}