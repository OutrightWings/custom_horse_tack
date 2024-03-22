package com.outrightwings.truly_custom_horse_tack.item.recipe;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);
    public static final RegistryObject<RecipeSerializer<?>> DUPLICATE_TACK = RECIPES.register("duplicate_tack",()-> new SimpleCraftingRecipeSerializer<>(TackDuplicateRecipe::new));

}
