package com.rumaruka.emt.init;

import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class IC2RecipesInput implements IRecipeInput {
    private final ItemStack input;

    public IC2RecipesInput(ItemStack input){
        this.input=input;
    }
    /**
     * Check if subject matches this recipe input, ignoring the amount.
     *
     * @param subject ItemStack to check
     * @return true if it matches the requirement
     */
    @Override
    public boolean matches(ItemStack subject) {
        return subject != ItemStack.EMPTY && input.isItemEqual(subject)&&input.getItemDamage()==subject.getItemDamage();
    }

    /**
     * Determine the minimum input stack size.
     *
     * @return input amount required
     */
    @Override
    public int getAmount() {

        return input.getMaxStackSize();
    }

    /**
     * List all possible inputs (best effort).
     * <p>
     * The stack size has to match getAmount().
     *
     * @return list of inputs, may be incomplete
     */
    @Override
    public List<ItemStack> getInputs() {
        List<ItemStack> list = Arrays.asList(input);
        return list;
    }
}
