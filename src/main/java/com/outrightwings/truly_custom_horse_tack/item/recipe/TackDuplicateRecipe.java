package com.outrightwings.truly_custom_horse_tack.item.recipe;

import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;

public class TackDuplicateRecipe extends CustomRecipe {
    public TackDuplicateRecipe(ResourceLocation resourceLocation, CraftingBookCategory bookCategory) {
        super(resourceLocation,bookCategory);
    }

    Tuple<ItemStack,Integer> findDecorated(CraftingContainer container, boolean decorated){
        ItemStack found = null;
        int index = -1;
        for(int i = 0; i < container.getContainerSize(); i++){
            ItemStack stack = container.getItem(i);
            if(stack.getItem() instanceof CustomTackItem){
                int listSize = TackPattern.getPatternListSize(stack.getTag());
                if(decorated && listSize > 0){
                    found = stack;
                    index = i;
                    break;
                }
                else if(!decorated && listSize < 1){
                    found = stack;
                    index = i;
                    break;
                }
            }
        }
        return new Tuple<>(found,index);
    }
    int itemCount(Container container){
        int count = 0;
        for(int i = 0; i < container.getContainerSize(); i++){
            ItemStack stack = container.getItem(i);
            if(!stack.isEmpty()) count++;
        }
        return count;
    }
    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack tackWithPatterns = findDecorated(container, true).getA();
        ItemStack tackWithoutPatterns = findDecorated(container, false).getA();

        return tackWithPatterns != null && tackWithoutPatterns != null && itemCount(container) == 2;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        return findDecorated(container,true).getA().copy();
    }
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container){
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        var tack = findDecorated(container,true);
        nonnulllist.set(tack.getB(),tack.getA().copy());
        return nonnulllist;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DUPLICATE_TACK.get();
    }
}
