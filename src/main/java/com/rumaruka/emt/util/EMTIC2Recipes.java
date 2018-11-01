package com.rumaruka.emt.util;

import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.init.IC2RecipesInput;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

public class EMTIC2Recipes {

    public static void setup() {

        IC2RecipesInput amber_ore = new IC2RecipesInput(new ItemStack(BlocksTC.oreAmber));
        Recipes.macerator.addRecipe(amber_ore, null, false, new ItemStack[]{new ItemStack(EMTItems.materials_crushedoreamber, 2)});

        IC2RecipesInput cinnabar_ore = new IC2RecipesInput(new ItemStack(BlocksTC.oreCinnabar));
        Recipes.macerator.addRecipe(cinnabar_ore, null, false, new ItemStack[]{new ItemStack(EMTItems.materials_crushedorecinnabar, 2)});

        IC2RecipesInput thaum_ingot = new IC2RecipesInput(new ItemStack(ItemsTC.ingots, 1, 0));
        Recipes.metalformerRolling.addRecipe(thaum_ingot, null, false, new ItemStack[]{new ItemStack(EMTItems.materials_thaumiumplate)});

        NBTTagCompound waterAmount = new NBTTagCompound();
        waterAmount.setInteger("amount", 1000);

        IC2RecipesInput pur_ore_amber = new IC2RecipesInput(new ItemStack(EMTItems.materials_crushedoreamber));
        Recipes.oreWashing.addRecipe(pur_ore_amber, waterAmount, false, new ItemStack[]{new ItemStack(EMTItems.materials_purifiedoreamber),new ItemStack(Item.getByNameOrId("ic2:dust"),2,19),new ItemStack(Item.getByNameOrId("ic2:dust"),1,15)});

        IC2RecipesInput pur_ore_cinnabra = new IC2RecipesInput(new ItemStack(EMTItems.materials_crushedorecinnabar));
        Recipes.oreWashing.addRecipe(pur_ore_cinnabra, waterAmount, false, new ItemStack[]{new ItemStack(EMTItems.materials_purifiedorecinnabar),new ItemStack(Item.getByNameOrId("ic2:dust"),2,28),new ItemStack(Item.getByNameOrId("ic2:dust"),1,15)});
    }




}
