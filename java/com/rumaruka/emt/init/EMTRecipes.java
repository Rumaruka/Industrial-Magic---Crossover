package com.rumaruka.emt.init;

import com.rumaruka.emt.util.RecipesUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;

public class EMTRecipes {

    public static void setup (){


        for (int i = 0; i < OreDictionary.getOres("itemRubber").size(); i++) {
             RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_rubberball), "XX", "XX", 'X', OreDictionary.getOres("itemRubber").get(i));
        }
        GameRegistry.addSmelting(new ItemStack(EMTItems.materials_rubberball),new ItemStack(EMTItems.materials_glue),2f);
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_ducttape), new Object[]{
                "ppp",
                "ggg",
                "ppp",
                'p',new ItemStack(Items.PAPER),
                'g', new ItemStack(EMTItems.materials_glue)
        });

        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_feathermesh), new Object[]{
                "fdf",
                "dfd",
                "fdf",
                'f',new ItemStack(Items.FEATHER), 'd', new ItemStack(EMTItems.materials_ducttape)
        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_cardboard), new Object[]{
                "ppp",
                "www",
                "ppp",
                'p',new ItemStack(Items.PAPER),
                'w', new ItemStack(Blocks.LOG)
        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_feathergluedcardboardwing), new Object[]{
                "mgc",
                "mgc",
                "mgc",
                'm',new ItemStack(EMTItems.materials_feathermesh),
                'g', new ItemStack(EMTItems.materials_glue),
                'c', new ItemStack(EMTItems.materials_cardboard)
        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.featherwing), "WW", 'W', new ItemStack(EMTItems.materials_feathergluedcardboardwing));
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_taintedfeather), new Object[]{
                "fff",
                " c ",
                "   ",
                'f', new ItemStack(Items.FEATHER), 'c', ConfigItems.FLUX_CRYSTAL
        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.thaumiumwing), "XX",  'X', new ItemStack(EMTItems.materials_thaumiumwing));
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.ironomnitool), new Object[]{
                "c  ",
                "p  ",
                "d  ",
                'c', new ItemStack(Item.getByNameOrId("ic2:chainsaw"),1,OreDictionary.WILDCARD_VALUE),
                'p', "plateIron",
                'd',  new ItemStack(Item.getByNameOrId("ic2:drill"),1,OreDictionary.WILDCARD_VALUE),

        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.diamondchainsaw), new Object[]{
                " d ",
                "dcd",
                "   ",
                'c', new ItemStack(Item.getByNameOrId("ic2:chainsaw"),1,OreDictionary.WILDCARD_VALUE),
                'd',  new ItemStack(Items.DIAMOND),


        });
        RecipesUtils.addShapedRecipe(new ItemStack(EMTItems.materials_thaumiumplate), new Object[]{
                "h  ",
                "t  ",
                "o  ",
                'h', new ItemStack(Item.getByNameOrId("ic2:forge_hammer")),
                't',  new ItemStack(ItemsTC.ingots,1,0),
                'o', new ItemStack(Blocks.OBSIDIAN),


        });
        RecipesUtils.addSmelting(new ItemStack(EMTItems.materials_crushedorecinnabar),new ItemStack(ItemsTC.quicksilver,2));
        RecipesUtils.addSmelting(new ItemStack(EMTItems.materials_crushedoreamber),new ItemStack(ItemsTC.amber,6));
        RecipesUtils.addSmelting(new ItemStack(EMTItems.materials_purifiedorecinnabar),new ItemStack(ItemsTC.quicksilver,4));
        RecipesUtils.addSmelting(new ItemStack(EMTItems.materials_purifiedoreamber),new ItemStack(ItemsTC.amber,12));
    }

}
