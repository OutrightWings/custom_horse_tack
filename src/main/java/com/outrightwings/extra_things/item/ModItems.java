package com.outrightwings.extra_things.item;

import com.outrightwings.extra_things.Main;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<HorseArmorItem> CUSTOM_TACK_ITEM = registerHorseArmor("custom_tack",3);

    private static RegistryObject<HorseArmorItem> registerHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new CustomTackItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    }
    private static RegistryObject<DyeableHorseArmorItem> registerDyeableHorseArmor(String name, int protection){
        return ITEMS.register(name, () -> new DyeableHorseArmorItem(protection,new ResourceLocation(Main.MODID,"textures/entity/horse/armor/"+name+".png"),(new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC)));
    }
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
//        ItemColors itemColors = event.getItemColors();
//        event.register((stack, tintIndex) -> {
//                    return tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack);
//                },
//                ModItems.HALTER_DYEABLE.get(),ModItems.BLANKET_DYEABLE.get(),ModItems.JUMPING_DYEABLE.get(),ModItems.ENGLISH_DYEABLE.get(),ModItems.SADDLE_PAD_DYEABLE.get());

    }
}
