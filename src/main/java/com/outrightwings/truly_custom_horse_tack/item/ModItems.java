package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<StatPotion> INCREASE_JUMP_POTION = ITEMS.register("increase_jump_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),true,true));
    public static final RegistryObject<StatPotion> DECREASE_JUMP_POTION = ITEMS.register("decrease_jump_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),false,true));
    public static final RegistryObject<StatPotion> INCREASE_SPEED_POTION = ITEMS.register("increase_speed_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),true,false));
    public static final RegistryObject<StatPotion> DECREASE_SPEED_POTION = ITEMS.register("decrease_speed_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),false,false));
    public static final RegistryObject<HorseStick> HORSE_STICK = ITEMS.register("horse_stick",()->new HorseStick(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<HorseArmorItem> CUSTOM_TACK_ITEM = registerHorseArmor("custom_tack",3);
    public static final RegistryObject<Item> WINTER_TACK_PATTERN = ITEMS.register("winter_tack_pattern",() -> new TackPatternItem("winter",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> RACE_TACK_PATTERN = ITEMS.register("race_tack_pattern",() -> new TackPatternItem("race",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> IMPOSSIBLE_TACK_PATTERN = ITEMS.register("impossible_tack_pattern",() -> new TackPatternItem("impossible",(new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CUTE_TACK_PATTERN = ITEMS.register("cute_tack_pattern",() -> new TackPatternItem("cute",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> HEAD_TACK_PATTERN = ITEMS.register("head_tack_pattern",() -> new TackPatternItem("head",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> REINS_TACK_PATTERN = ITEMS.register("reins_tack_pattern",() -> new TackPatternItem("reins",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> BODY_TACK_PATTERN = ITEMS.register("body_tack_pattern",() -> new TackPatternItem("body",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> FEET_TACK_PATTERN = ITEMS.register("feet_tack_pattern",() -> new TackPatternItem("feet",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));


    public static final RegistryObject<Item> SADDLE_RACK = ITEMS.register("saddle_rack",() -> new StandingAndWallBlockItem(ModBlocks.SADDLE_RACK.get(),ModBlocks.SADDLE_RACK_WALL.get(),new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> HEAD_STAND = ITEMS.register("head_stand",() -> new StandingAndWallBlockItem(ModBlocks.HEAD_STAND.get(),ModBlocks.HEAD_STAND_WALL.get(),new Item.Properties().tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> MIXABLE_DYE = ITEMS.register("mixable_dye",() -> new MixedDye((new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    private static RegistryObject<HorseArmorItem> registerHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new CustomTackItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    }
    private static RegistryObject<DyeableHorseArmorItem> registerDyeableHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new DyeableHorseArmorItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    }
}
