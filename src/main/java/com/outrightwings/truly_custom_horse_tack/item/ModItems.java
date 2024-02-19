package com.outrightwings.truly_custom_horse_tack.item;

import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.block.JumpBlock;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sekelsta.horse_colors.entity.ModEntities;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
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

    public static final RegistryObject<Item> SADDLE_RACK = ITEMS.register("saddle_rack",() -> new StandingAndWallBlockItem(ModBlocks.SADDLE_RACK.get(),ModBlocks.SADDLE_RACK_WALL.get(),new Item.Properties(), Direction.DOWN));
    public static final RegistryObject<Item> HEAD_STAND = ITEMS.register("head_stand",() -> new StandingAndWallBlockItem(ModBlocks.HEAD_STAND.get(),ModBlocks.HEAD_STAND_WALL.get(),new Item.Properties(), Direction.DOWN));
    private static RegistryObject<HorseArmorItem> registerHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new CustomTackItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1)));
    }
    private static RegistryObject<DyeableHorseArmorItem> registerDyeableHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new DyeableHorseArmorItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1)));
    }

    @SubscribeEvent
    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            event.accept(ForgeRegistries.ITEMS.getValue(ModBlocks.SADDLER.getId()));
            event.accept(SADDLE_RACK);
            event.accept(HEAD_STAND);
        } else if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            event.accept(CUSTOM_TACK_ITEM);
            event.accept(INCREASE_JUMP_POTION);
            event.accept(DECREASE_JUMP_POTION);
            event.accept(INCREASE_SPEED_POTION);
            event.accept(DECREASE_SPEED_POTION);
            event.accept(HORSE_STICK);

        } else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(WINTER_TACK_PATTERN);
            event.accept(CUTE_TACK_PATTERN);
            event.accept(RACE_TACK_PATTERN);
        } else if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS)) {
            ForgeRegistries.ITEMS.getEntries().stream().filter(o -> o.getValue().getDescriptionId().contains("jump") && !o.getValue().getDescriptionId().contains("potion")).forEach((object ->
                    event.accept(object.getValue().asItem())
            ));
        }

        ModEntities.addToCreativeTab(event);
    }
}
