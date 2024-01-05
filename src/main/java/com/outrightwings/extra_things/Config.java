package com.outrightwings.extra_things;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.BooleanValue CONSUME_DYE = BUILDER
            .comment("Should dye  be consumed in Saddler Table?")
            .define("consume_dye",true);
    private static final ForgeConfigSpec.DoubleValue MAX_JUMP = BUILDER
            .comment("Max Jump allowed by potions")
            .defineInRange("max_jump",1f,0,100);
    private static final ForgeConfigSpec.DoubleValue MAX_SPEED = BUILDER
            .comment("Max Speed allowed by potions")
            .defineInRange("max_speed",0.3375f,0,100);
    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static float max_jump;
    public static float max_speed;

    public static boolean consume_dye;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        max_jump = (float) (MAX_JUMP.get() * 1);
        max_speed = (float) (MAX_SPEED.get() * 1);
        consume_dye = CONSUME_DYE.get();
    }
}
