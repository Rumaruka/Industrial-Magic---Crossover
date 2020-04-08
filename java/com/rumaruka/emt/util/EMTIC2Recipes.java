package com.rumaruka.emt.util;

import com.rumaruka.emt.init.EMTItems;

import ic2.api.recipe.Recipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

public class EMTIC2Recipes {

    public static void setup() {



        Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(BlocksTC.oreAmber)), null, false, new ItemStack[] { new ItemStack(EMTItems.materials_crushedoreamber, 2) });


        Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(BlocksTC.oreCinnabar)), null, false, new ItemStack[] { new ItemStack(EMTItems.materials_crushedorecinnabar, 2) });


        Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(ItemsTC.ingots, 1, 0)), null, false, new ItemStack[] { new ItemStack(EMTItems.materials_thaumiumplate) });

        NBTTagCompound waterAmount = new NBTTagCompound();
        waterAmount.setInteger("amount", 1000);

        Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(EMTItems.materials_crushedoreamber)), waterAmount, false, new ItemStack[] { new ItemStack(EMTItems.materials_purifiedoreamber), new ItemStack(Item.getByNameOrId("ic2:dust"), 2, 19), new ItemStack(Item.getByNameOrId("ic2:dust"), 1, 15) });
        Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(EMTItems.materials_crushedorecinnabar)), waterAmount, false, new ItemStack[] { new ItemStack(EMTItems.materials_purifiedorecinnabar), new ItemStack(Item.getByNameOrId("ic2:dust"), 2, 28), new ItemStack(Item.getByNameOrId("ic2:dust"), 1, 15) });
    }




}
