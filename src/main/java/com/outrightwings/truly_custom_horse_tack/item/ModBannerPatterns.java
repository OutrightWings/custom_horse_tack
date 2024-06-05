package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ModBannerPatterns {
    public static final Map<String, PatternData> PATTERNS = new HashMap<>();

    public static final PatternData ANCHOR = createPattern("horse_head");
    public static final PatternData BALANCE = createPattern("unicorn");
    public static final PatternData GRASS = createPattern("horse_rearing");
    public static final PatternData KELP = createPattern("horse_running");
    public static final PatternData MUSHROOM = createPattern("horse_running_flipped");

    private static PatternData createPattern(String name) {

        final PatternData pattern = new PatternData(name);
        PATTERNS.put(name, pattern);
        return pattern;
    }

    public static class PatternData {

        public final String name;
        public final String enumName;
        public final String texture;

        public PatternData(String name) {
            this.name = name;
            this.texture = Main.MODID + "_" + this.name;
            this.enumName = this.texture.toUpperCase(Locale.ROOT);
        }
    }

}
