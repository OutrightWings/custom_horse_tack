package com.outrightwings.truly_custom_horse_tack.item.recipe;

import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class TackDuplicateRecipe extends CustomRecipe {
    public TackDuplicateRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
        System.out.println("REGISTERED BITCH");
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack tackWithPatterns = null;
        ItemStack tackWithoutPatterns = null;

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof CustomTackItem) {
                System.out.println("I SEE TACK BITCH");
                CompoundTag tag = stack.getOrCreateTag();
                if(TackPattern.getPatternListSize(tag) > 0){
                    System.out.println("DECORATED BITCH");
                    tackWithPatterns = stack;
                } else{
                    System.out.println("NON DECORATED");
                    tackWithoutPatterns = stack;
                }
            } else{
                System.out.println("FAILED BITCH");
                return false;
            }
        }
        System.out.println("Matches? Bitch: " + (tackWithPatterns != null && tackWithoutPatterns != null));
        return tackWithPatterns != null && tackWithoutPatterns != null;
    }

    @Override
    public ItemStack assemble(CraftingContainer container) {
        System.out.println("FAILED BITCH");
        return ItemStack.EMPTY;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        System.out.println("FAILED BITCH");
        return x * y >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        System.out.println("FAILED BITCH");
        return ModRecipes.DUPLICATE_TACK.get();
    }
}
