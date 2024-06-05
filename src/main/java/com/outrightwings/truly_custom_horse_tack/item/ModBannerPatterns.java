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

public class ModBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNERS = DeferredRegister.create(Registry.BANNER_PATTERN_REGISTRY, Main.MODID);

    public static final RegistryObject<BannerPattern> HORSE_HEAD = BANNERS.register("horse_head",() -> new BannerPattern("horse_head"));
    public static final RegistryObject<BannerPattern> UNICORN = BANNERS.register("unicorn",() -> new BannerPattern("unicorn"));
    public static final RegistryObject<BannerPattern> HORSE_REARING = BANNERS.register("horse_rearing",() -> new BannerPattern("horse_rearing"));
    public static final RegistryObject<BannerPattern> HORSE_RUNNING = BANNERS.register("horse_running",() -> new BannerPattern("horse_running"));
    public static final RegistryObject<BannerPattern> HORSE_RUNNING_FLIPPED = BANNERS.register("horse_running_flipped",() -> new BannerPattern("horse_running_flipped"));

    public static final RegistryObject<Item> HORSE_BANNER_PATTERN = ModItems.ITEMS.register("horse_banner_pattern",() -> new BannerPatternItem(createTagKey("horse"),new Item.Properties().stacksTo(1).tab(ModCreativeTab.instance)));

    private static TagKey<BannerPattern> createTagKey(String name){
        return TagKey.create(Registry.BANNER_PATTERN_REGISTRY,new ResourceLocation(Main.MODID,"pattern_item/"+name));
    }

}
