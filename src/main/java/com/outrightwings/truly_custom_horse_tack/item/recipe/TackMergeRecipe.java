package com.outrightwings.truly_custom_horse_tack.item.recipe;

import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackTagUtility;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
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

public class TackMergeRecipe extends CustomRecipe {
    public TackMergeRecipe(ResourceLocation resourceLocation, CraftingBookCategory bookCategory) {
        super(resourceLocation,bookCategory);
    }

    Tuple<ItemStack,Integer> findDecorated(CraftingContainer container, int prev, boolean decorated){
        ItemStack found = null;
        int index = -1;
        for(int i = 0; i < container.getContainerSize(); i++){
            ItemStack stack = container.getItem(i);
            if(stack.getItem() instanceof CustomTackItem){
                int listSize = TackTagUtility.getPatternListSize(stack.getTag());
                if(((listSize > 0 && decorated)||(listSize <= 0 && !decorated) )&& prev != i  ){
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
        var tackA = findDecorated(container, -100,true);
        var tackB = findDecorated(container, tackA.getB(),true);
        var tackC = findDecorated(container, -100,false);
        return tackC.getA() != null && tackB.getA() != null && tackA.getA() != null&& itemCount(container) == 3;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        var tackA = findDecorated(container, -100,true);
        var tackB = findDecorated(container, tackA.getB(),true);
        ItemStack tackWithPatterns = tackA.getA();
        ItemStack tackWithoutPatterns = tackB.getA();

        return TackTagUtility.mergeTack(tackWithPatterns.copy(),tackWithoutPatterns.copy());
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container){
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        var tackA = findDecorated(container, -100,true);
        var tackB = findDecorated(container, tackA.getB(),true);
        nonnulllist.set(tackA.getB(), tackA.getA().copy());
        nonnulllist.set(tackB.getB(), tackB.getA().copy());
        return nonnulllist;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MERGE_TACK.get();
    }
}
