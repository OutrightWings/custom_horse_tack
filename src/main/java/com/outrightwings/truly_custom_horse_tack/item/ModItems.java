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
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<StatPotion> INCREASE_JUMP_POTION = ITEMS.register("increase_jump_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),true,true));
    public static final RegistryObject<StatPotion> DECREASE_JUMP_POTION = ITEMS.register("decrease_jump_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),false,true));
    public static final RegistryObject<StatPotion> INCREASE_SPEED_POTION = ITEMS.register("increase_speed_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),true,false));
    public static final RegistryObject<StatPotion> DECREASE_SPEED_POTION = ITEMS.register("decrease_speed_potion",() -> new StatPotion(new Item.Properties().tab(ModCreativeTab.instance),false,false));
    public static final RegistryObject<HorseStick> HORSE_STICK = ITEMS.register("horse_stick",()->new HorseStick(new Item.Properties()));

    public static final RegistryObject<HorseArmorItem> CUSTOM_TACK_ITEM = registerHorseArmor("custom_tack",3);
    public static final RegistryObject<Item> WINTER_TACK_PATTERN = ITEMS.register("winter_tack_pattern",() -> new TackPatternItem("winter",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> RACE_TACK_PATTERN = ITEMS.register("race_tack_pattern",() -> new TackPatternItem("race",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    public static final RegistryObject<Item> IMPOSSIBLE_TACK_PATTERN = ITEMS.register("impossible_tack_pattern",() -> new TackPatternItem("impossible",(new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CUTE_TACK_PATTERN = ITEMS.register("cute_tack_pattern",() -> new TackPatternItem("cute",(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));

    private static RegistryObject<HorseArmorItem> registerHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new CustomTackItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    }
    private static RegistryObject<DyeableHorseArmorItem> registerDyeableHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new DyeableHorseArmorItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(ModCreativeTab.instance)));
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
//        ItemColors itemColors = event.getItemColors();
//        event.register((stack, tintIndex) -> {
//                    return tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack);
//                },
//                ModItems.HALTER_DYEABLE.get(),ModItems.BLANKET_DYEABLE.get(),ModItems.JUMPING_DYEABLE.get(),ModItems.ENGLISH_DYEABLE.get(),ModItems.SADDLE_PAD_DYEABLE.get());

    }
}
