package com.rumaruka.emt.util;

import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.init.IC2RecipesInput;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

public class EMTIC2Recipes {

    public static void setup(){

        IC2RecipesInput amber_ore = new IC2RecipesInput(new ItemStack(BlocksTC.oreAmber));
        Recipes.macerator.addRecipe(amber_ore,null,false,new ItemStack[]{new ItemStack(EMTItems.materials_crushedoreamber,2)});

        IC2RecipesInput cinnabar_ore = new IC2RecipesInput(new ItemStack(BlocksTC.oreCinnabar));
        Recipes.macerator.addRecipe(cinnabar_ore,null,false,new ItemStack[]{new ItemStack(EMTItems.materials_crushedorecinnabar,2)});

        IC2RecipesInput thaum_ingot = new IC2RecipesInput(new ItemStack(ItemsTC.ingots,1,0));
        Recipes.metalformerRolling.addRecipe(thaum_ingot,null,false,new ItemStack[]{new ItemStack(EMTItems.materials_thaumiumplate)});


    }





}
