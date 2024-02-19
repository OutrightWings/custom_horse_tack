package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    //todo
    //Creative Tabs
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<StatPotion> INCREASE_JUMP_POTION = ITEMS.register("increase_jump_potion",() -> new StatPotion(new Item.Properties(),true,true));
    public static final RegistryObject<StatPotion> DECREASE_JUMP_POTION = ITEMS.register("decrease_jump_potion",() -> new StatPotion(new Item.Properties(),false,true));
    public static final RegistryObject<StatPotion> INCREASE_SPEED_POTION = ITEMS.register("increase_speed_potion",() -> new StatPotion(new Item.Properties(),true,false));
    public static final RegistryObject<StatPotion> DECREASE_SPEED_POTION = ITEMS.register("decrease_speed_potion",() -> new StatPotion(new Item.Properties(),false,false));
    public static final RegistryObject<HorseStick> HORSE_STICK = ITEMS.register("horse_stick",()->new HorseStick(new Item.Properties()));

    public static final RegistryObject<HorseArmorItem> CUSTOM_TACK_ITEM = registerHorseArmor("custom_tack",3);
    public static final RegistryObject<Item> WINTER_TACK_PATTERN = ITEMS.register("winter_tack_pattern",() -> new TackPatternItem("winter",(new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> RACE_TACK_PATTERN = ITEMS.register("race_tack_pattern",() -> new TackPatternItem("race",(new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> IMPOSSIBLE_TACK_PATTERN = ITEMS.register("impossible_tack_pattern",() -> new TackPatternItem("impossible",(new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CUTE_TACK_PATTERN = ITEMS.register("cute_tack_pattern",() -> new TackPatternItem("cute",(new Item.Properties()).stacksTo(1)));

    private static RegistryObject<HorseArmorItem> registerHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new CustomTackItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1)));
    }
    private static RegistryObject<DyeableHorseArmorItem> registerDyeableHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new DyeableHorseArmorItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1)));
    }
}
