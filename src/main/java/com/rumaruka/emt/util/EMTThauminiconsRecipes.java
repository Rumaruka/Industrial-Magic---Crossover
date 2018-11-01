package com.rumaruka.emt.util;

import com.rumaruka.emt.init.EMTBlocks;
import com.rumaruka.emt.init.EMTItems;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Fluid;
import ic2.core.apihelper.ItemAPI;
import ic2.core.item.ItemFluidCell;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.internal.CommonInternals;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.blocks.BlockTC;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

import java.util.List;

public class EMTThauminiconsRecipes {

    static ResourceLocation defaultGroup = new ResourceLocation("");

    public static final OnetimeCaller insertAspects = new OnetimeCaller(EMTThauminiconsRecipes::$insertAspects);

    public static void  setup() {
        ResearchCategories.registerCategory(catName,null,null,icon,back,back2);

        //===Arcane Recipe===\\
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.TaintFeather"), new ShapedArcaneRecipe(defaultGroup, "EMT.WINGS",25, new AspectList()
                .add(primals(10)), new ItemStack(EMTItems.materials_thaumiumwing), new Object[] {
                "fti",
                "fti",
                "fti",

                Character.valueOf('t'), new ItemStack(EMTItems.materials_thaumiumplate),
                Character.valueOf('f'),new ItemStack(EMTItems.materials_taintedfeather),
                Character.valueOf('i'),"plateIron" }));


        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.electroST"), new ShapedArcaneRecipe(defaultGroup,"EMT.FIRST",10, new AspectList().add(Aspect.AIR,5).add(Aspect.ORDER,5).add(Aspect.ENTROPY,5),new ItemStack(EMTItems.electricscribingtools),
                new Object[] {
                "ctc",
                "tet",
                "ctc",
                 Character.valueOf('c'), "circuitBasic",
                 Character.valueOf('t'), new ItemStack(ItemsTC.scribingTools),
                 Character.valueOf('e'), new ItemStack(Item.getByNameOrId("ic2:energy_crystal"),1,OreDictionary.WILDCARD_VALUE)}));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.diamondomnitool"), new ShapedArcaneRecipe(defaultGroup,"EMT.TOOLS",20, new AspectList().add(Aspect.AIR,10).add(Aspect.ORDER,10),new ItemStack(EMTItems.diamondomnitool),
                new Object[] {
                        "dad",
                        "did",
                        "dad",
                        Character.valueOf('a'), "circuitAdvanced",
                        Character.valueOf('d'), new ItemStack(Items.DIAMOND),
                        Character.valueOf('i'), new ItemStack(EMTItems.ironomnitool)}));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.electricgoggles"), new ShapedArcaneRecipe(defaultGroup,"EMT.TOOLS",45, new AspectList().add(Aspect.AIR,10).add(Aspect.ORDER,10).add(Aspect.WATER,10),new ItemStack(EMTItems.electricgoggles),
                new Object[] {
                        " h ",
                        "rgr",
                        "cbc",
                        Character.valueOf('r'), new ItemStack(Item.getByNameOrId("ic2:re_battery"),1,OreDictionary.WILDCARD_VALUE),
                        Character.valueOf('b'), "circuitBasic",
                        Character.valueOf('h'), new ItemStack(Items.DIAMOND_HELMET),
                        Character.valueOf('g'), new ItemStack(ItemsTC.goggles),
                        Character.valueOf('c'), new ItemStack(Items.COMPARATOR),
                }));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.solar"), new ShapedArcaneRecipe(defaultGroup,"EMT.MACH",100, new AspectList().add(Aspect.AIR,10).add(Aspect.ORDER,10).add(Aspect.WATER,10),new ItemStack(EMTBlocks.solar_compressed),
                new Object[] {
                        "sss",
                        "sss",
                        "sss",
                        Character.valueOf('s'), new ItemStack(Item.getByNameOrId("ic2:re_battery"),1,OreDictionary.WILDCARD_VALUE),

                }));

        //===Crucible Recipe===\\

        CrucibleRecipe crystalUU = new CrucibleRecipe("EMT.ALT_USE_UU",new ItemStack(EMTItems.materials_uumatterdrop),IC2Items.getItem("fluid_cell", "ic2uu_matter"),new AspectList().add(Aspect.CRYSTAL,8).add(Aspect.MAGIC,8).add(Aspect.ORDER,8));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.crystalUU"),crystalUU );



        //===Infusion Recipe===\\

        InfusionRecipe thauOmnitools = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumomnitool),3,new AspectList().add(Aspect.TOOL,25).add(Aspect.ENERGY,25).add(Aspect.AVERSION,25), new ItemStack(EMTItems.thaumiumchainsaw),new ItemStack(EMTItems.thaumiumdrill), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,15),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,6));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tomni"),thauOmnitools);
        InfusionRecipe thaumChainsaw = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumchainsaw),3,new AspectList().add(Aspect.TOOL,15).add(Aspect.ENERGY,15).add(Aspect.AVERSION,15),new ItemStack(EMTItems.diamondchainsaw), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:plate"),1,12),new ItemStack(Items.DIAMOND),new ItemStack(Items.DIAMOND));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tchainsaw"),thaumChainsaw);
        InfusionRecipe streamChainsaw = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.streamchainsaw),4,new AspectList().add(Aspect.TOOL,32).add(Aspect.ENERGY,45).add(Aspect.AVERSION,15).add(Aspect.WATER,32), new ItemStack(EMTItems.thaumiumchainsaw), new ItemStack(Items.WATER_BUCKET),new ItemStack(ItemsTC.elementalAxe),new ItemStack(BlocksTC.logGreatwood), new ItemStack(Item.getByNameOrId("ic2:lapotron_crystal"),1,OreDictionary.WILDCARD_VALUE),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4),new ItemStack(Item.getByNameOrId("ic2:upgrade"),1,0));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.schainsaw"),streamChainsaw);


        InfusionRecipe thaumDrill = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumdrill),2,new AspectList().add(Aspect.TOOL,15).add(Aspect.ENERGY,15).add(Aspect.AVERSION,15),new ItemStack(Item.getByNameOrId("ic2:diamond_drill"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:plate"),1,12),new ItemStack(Items.DIAMOND),new ItemStack(Items.DIAMOND));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tdrill"),thaumDrill);
        InfusionRecipe rockDrill = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.rockbreakerdrill),4,new AspectList().add(Aspect.TOOL,32).add(Aspect.ENERGY,45).add(Aspect.AVERSION,15).add(Aspect.WATER,32), new ItemStack(EMTItems.thaumiumdrill), new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(Items.FIRE_CHARGE),new ItemStack(ItemsTC.elementalPick),new ItemStack(ItemsTC.elementalShovel), new ItemStack(Item.getByNameOrId("ic2:lapotron_crystal")),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4),new ItemStack(Item.getByNameOrId("ic2:upgrade"),1,0),new ItemStack(Item.getByNameOrId("ic2:resource"),1,11));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.rdrill"),rockDrill);

        InfusionRecipe eHoG = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.electrichoegrowth),2,new AspectList().add(Aspect.TOOL,32).add(Aspect.ENERGY,45).add(Aspect.AVERSION,15).add(Aspect.WATER,32).add(Aspect.PLANT,25), new ItemStack(ItemsTC.elementalHoe),new ItemStack(Item.getByNameOrId("ic2:lapotron_crystal"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(Item.getByNameOrId("ic2:electric_hoe")), new ItemStack(Items.DYE,1,15),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,6), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,5),new ItemStack(Blocks.SAPLING,1,0));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.ehor"),eHoG);


        InfusionRecipe electricBOT = new InfusionRecipe("EMT.ARMORS", new ItemStack(EMTItems.electricbootstraveller),2, new AspectList().add(Aspect.ENERGY,16).add(Aspect.ORDER,16).add(Aspect.MOTION,32), new ItemStack(ItemsTC.travellerBoots), new ItemStack(Items.DIAMOND), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,6), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,5), new ItemStack(Item.getByNameOrId("ic2:rubber_boots")), new ItemStack(Item.getByNameOrId("ic2:advanced_re_battery"),1,OreDictionary.WILDCARD_VALUE));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.ebot"),electricBOT);


        InfusionRecipe nanoW = new InfusionRecipe("EMT.WINGS", new ItemStack(EMTItems.nanowing),2, new AspectList().add(Aspect.ENERGY,32).add(Aspect.ORDER,32).add(Aspect.MOTION,32).add(Aspect.MECHANISM,56), new ItemStack(EMTItems.thaumiumwing), new ItemStack(Items.DIAMOND), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,15), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,15), new ItemStack(Item.getByNameOrId("ic2:crafting"), 1,15), new ItemStack(Item.getByNameOrId("ic2:nano_chestplate"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.salisMundus));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.nanoW"),nanoW);
        InfusionRecipe nanoBOT = new InfusionRecipe("EMT.ARMORS", new ItemStack(EMTItems.nanobootstraveller),3, new AspectList().add(Aspect.ENERGY,25).add(Aspect.ORDER,15).add(Aspect.MOTION,20), new ItemStack(EMTItems.electricbootstraveller), new ItemStack(Items.DIAMOND), new ItemStack(Item.getByNameOrId("ic2:nano_boots"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(Item.getByNameOrId("ic2:energy_crystal"),1,OreDictionary.WILDCARD_VALUE));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.nanoBOT"),nanoBOT);
        InfusionRecipe nanoGOR = new InfusionRecipe("EMT.ARMORS", new ItemStack(EMTItems.nanogoggles),3, new AspectList().add(Aspect.ENERGY,20).add(Aspect.ORDER,15).add(Aspect.SENSES,16), new ItemStack(EMTItems.electricgoggles), new ItemStack(Items.DIAMOND), new ItemStack(Item.getByNameOrId("ic2:nano_helmet"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(Items.GOLD_INGOT),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,15),"circuitBasic");
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.nanoGOR"),nanoGOR);

        InfusionRecipe qW = new InfusionRecipe("EMT.WINGS", new ItemStack(EMTItems.quantumwing),4, new AspectList().add(Aspect.ENERGY,32).add(Aspect.ORDER,32).add(Aspect.MOTION,32).add(Aspect.MECHANISM,56).add(Aspect.AIR,32), new ItemStack(EMTItems.nanowing), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4), new ItemStack(Item.getByNameOrId("ic2:crafting"), 1,4), new ItemStack(Item.getByNameOrId("ic2:quantum_chestplate"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.salisMundus));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.qW"),qW);
        InfusionRecipe qBOT = new InfusionRecipe("EMT.ARMORS", new ItemStack(EMTItems.quantumbootstraveller),4, new AspectList().add(Aspect.ENERGY,32).add(Aspect.ORDER,32).add(Aspect.MOTION,32).add(Aspect.SENSES,25).add(Aspect.AIR,32), new ItemStack(EMTItems.nanobootstraveller), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4), new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(Item.getByNameOrId("ic2:quantum_boots"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(Item.getByNameOrId("ic2:lapotron_crystal"),1,OreDictionary.WILDCARD_VALUE));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.qBOT"),qBOT);
        InfusionRecipe qGOR = new InfusionRecipe("EMT.ARMORS", new ItemStack(EMTItems.quantumgoggles),4, new AspectList().add(Aspect.ENERGY,20).add(Aspect.ORDER,15).add(Aspect.SENSES,16), new ItemStack(EMTItems.nanogoggles), new ItemStack(Items.DIAMOND), new ItemStack(Item.getByNameOrId("ic2:quantum_helmet"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(Items.MILK_BUCKET),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4),"circuitAdvanced");
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.qGOR"),qGOR);

        InfusionRecipe crystUUtoDiam = new InfusionRecipe("EMT.PER" ,new ItemStack(Items.DIAMOND),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(Items.GOLD_INGOT),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoDi"),crystUUtoDiam);

    }

    private static void $insertAspects() {



    }

    public static AspectList primals(int amount) {
        return new AspectList().add(Aspect.AIR, amount).add(Aspect.WATER, amount).add(Aspect.EARTH, amount).add(Aspect.FIRE, amount).add(Aspect.ORDER, amount).add(Aspect.ENTROPY, amount);
    }

    private static void appendAspects(String oreDict, AspectList toAdd) {
        List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
        if (toAdd == null) toAdd = new AspectList();
        if (ores != null && ores.size() > 0) for (ItemStack ore : ores)
            try {
                ItemStack oc = ore.copy();
                oc.setCount(1);
                appendAspects(oc, toAdd);
            } catch (Exception oc) {
            }
    }

    private static void appendAspects(ItemStack stack, AspectList toAdd) {
        toAdd = toAdd.copy();

        // Finds item's aspects, and if there are any, adds them to appended
        // aspects
        {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
            if (al != null) toAdd = toAdd.add(al);
        }

        CommonInternals.objectTags.put(CommonInternals.generateUniqueItemstackId(stack), toAdd);
    }

    private static void removeAspects(ItemStack stack, Aspect... aspects) {
        AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
        if (al != null) {
            for (Aspect a : aspects)
                al.remove(a);
            CommonInternals.objectTags.put(CommonInternals.generateUniqueItemstackId(stack), al);
        }
    }

    public static final String catName ="EMT";
    public static final ResourceLocation icon = new ResourceLocation("emt","textures/misc/emt.png");
    public static final ResourceLocation back = new ResourceLocation("emt","textures/misc/background.png");
    public static final ResourceLocation back2 = new ResourceLocation("thaumcraft","textures/gui/gui_research_back_over.png");

}
