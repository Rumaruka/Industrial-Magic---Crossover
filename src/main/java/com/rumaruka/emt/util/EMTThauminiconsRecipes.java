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

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.solar"), new ShapedArcaneRecipe(defaultGroup,"EMT.SOLAR",100, new AspectList().add(Aspect.AIR,10).add(Aspect.ORDER,10).add(Aspect.WATER,10),new ItemStack(EMTBlocks.solar_compressed),
                new Object[] {
                        "sss",
                        "sss",
                        "sss",
                        Character.valueOf('s'), new ItemStack(Item.getByNameOrId("ic2:te"),1,8),

                }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("EMT.solar_double"), new ShapedArcaneRecipe(defaultGroup,"EMT.SOLAR",100, new AspectList().add(Aspect.AIR,10).add(Aspect.ORDER,10).add(Aspect.WATER,10),new ItemStack(EMTBlocks.solar_doublecompressed),
                new Object[] {
                        "ccc",
                        "ccc",
                        "ccc",
                        Character.valueOf('c'), new ItemStack(EMTBlocks.solar_compressed),

                }));
        //===Crucible Recipe===\\

        CrucibleRecipe crystalUU = new CrucibleRecipe("EMT.UUCr",new ItemStack(EMTItems.materials_uumatterdrop),IC2Items.getItem("fluid_cell", "ic2uu_matter"),new AspectList().add(Aspect.CRYSTAL,8).add(Aspect.MAGIC,8).add(Aspect.ORDER,8));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.crystalUU"),crystalUU );

        CrucibleRecipe solarAir = new CrucibleRecipe("EMT.SOLAR_AIR",new ItemStack(EMTBlocks.solar_aircompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.AIR,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarAir"),solarAir);
        CrucibleRecipe solarDAir = new CrucibleRecipe("EMT.SOLAR_AIR",new ItemStack(EMTBlocks.solar_airdoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.AIR,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDAir"),solarDAir);
        CrucibleRecipe solarTAir = new CrucibleRecipe("EMT.SOLAR_AIR",new ItemStack(EMTBlocks.solar_airtriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.AIR,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTAir"),solarTAir);

        CrucibleRecipe gAir = new CrucibleRecipe("EMT.GENERATOR_AIR",new ItemStack(EMTBlocks.aer_generator),new ItemStack(EMTBlocks.energy_generator), new AspectList().add(Aspect.AIR,25).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.gAir"),gAir);
        CrucibleRecipe gAur = new CrucibleRecipe("EMT.GENERATOR_AUR",new ItemStack(EMTBlocks.auram_generator),new ItemStack(EMTBlocks.energy_generator), new AspectList().add(Aspect.AURA,25).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.gAur"),gAur);
        CrucibleRecipe gIgnis = new CrucibleRecipe("EMT.GENERATOR_FIRE",new ItemStack(EMTBlocks.fire_generator),new ItemStack(EMTBlocks.energy_generator), new AspectList().add(Aspect.FIRE,25).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.gIgnis"),gIgnis);

        CrucibleRecipe solarE = new CrucibleRecipe("EMT.SOLAR_EARTH",new ItemStack(EMTBlocks.solar_earthcompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.EARTH,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarE"),solarE);
        CrucibleRecipe solarDE = new CrucibleRecipe("EMT.SOLAR_EARTH",new ItemStack(EMTBlocks.solar_earthdoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.EARTH,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDE"),solarDE);
        CrucibleRecipe solarTE = new CrucibleRecipe("EMT.SOLAR_EARTH",new ItemStack(EMTBlocks.solar_earthtriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.EARTH,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTE"),solarTE);

        CrucibleRecipe solarEn = new CrucibleRecipe("EMT.SOLAR_EN",new ItemStack(EMTBlocks.solar_darkcompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.ENTROPY,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarEn"),solarEn);
        CrucibleRecipe solarDEn = new CrucibleRecipe("EMT.SOLAR_EN",new ItemStack(EMTBlocks.solar_darkdoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.ENTROPY,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDEn"),solarDEn);
        CrucibleRecipe solarTEn = new CrucibleRecipe("EMT.SOLAR_EN",new ItemStack(EMTBlocks.solar_darktriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.ENTROPY,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTEn"),solarTEn);

        CrucibleRecipe solarF = new CrucibleRecipe("EMT.SOLAR_FIRE",new ItemStack(EMTBlocks.solar_firecompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.FIRE,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarF"),solarF);
        CrucibleRecipe solarDF = new CrucibleRecipe("EMT.SOLAR_FIRE",new ItemStack(EMTBlocks.solar_firedoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.FIRE,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDF"),solarDF);
        CrucibleRecipe solarTF = new CrucibleRecipe("EMT.SOLAR_FIRE",new ItemStack(EMTBlocks.solar_firetriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.FIRE,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTF"),solarTF);

        CrucibleRecipe solarO = new CrucibleRecipe("EMT.SOLAR_ORDER",new ItemStack(EMTBlocks.solar_ordercompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.ORDER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarO"),solarO);
        CrucibleRecipe solarDO = new CrucibleRecipe("EMT.SOLAR_ORDER",new ItemStack(EMTBlocks.solar_orderdoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.ORDER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDO"),solarDO);
        CrucibleRecipe solarTO = new CrucibleRecipe("EMT.SOLAR_ORDER",new ItemStack(EMTBlocks.solar_ordertriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.ORDER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTO"),solarTO);

        CrucibleRecipe solarW = new CrucibleRecipe("EMT.SOLAR_WATER",new ItemStack(EMTBlocks.solar_watercompressed),new ItemStack(EMTBlocks.solar_compressed), new AspectList().add(Aspect.WATER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarW"),solarW);
        CrucibleRecipe solarDW = new CrucibleRecipe("EMT.SOLAR_WATER",new ItemStack(EMTBlocks.solar_waterdoublecompressed),new ItemStack(EMTBlocks.solar_doublecompressed), new AspectList().add(Aspect.WATER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarDW"),solarDW);
        CrucibleRecipe solarTW = new CrucibleRecipe("EMT.SOLAR_WATER",new ItemStack(EMTBlocks.solar_watertriplecompressed),new ItemStack(EMTBlocks.solar_triplecompressed), new AspectList().add(Aspect.WATER,25).add(Aspect.MAGIC,16).add(Aspect.EXCHANGE,16));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("EMT.solarTW"),solarTW);





        //===Infusion Recipe===\\

        InfusionRecipe thauOmnitools = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumomnitool),3,new AspectList().add(Aspect.TOOL,25).add(Aspect.ENERGY,25).add(Aspect.AVERSION,25), new ItemStack(EMTItems.thaumiumchainsaw),new ItemStack(EMTItems.thaumiumdrill), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:crafting"),1,15),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,6));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tomni"),thauOmnitools);
        InfusionRecipe thaumChainsaw = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumchainsaw),3,new AspectList().add(Aspect.TOOL,15).add(Aspect.ENERGY,15).add(Aspect.AVERSION,15),new ItemStack(EMTItems.diamondchainsaw), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:plate"),1,12),new ItemStack(Items.DIAMOND),new ItemStack(Items.DIAMOND));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tchainsaw"),thaumChainsaw);
        InfusionRecipe streamChainsaw = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.streamchainsaw),4,new AspectList().add(Aspect.TOOL,32).add(Aspect.ENERGY,45).add(Aspect.AVERSION,15).add(Aspect.WATER,32), new ItemStack(EMTItems.thaumiumchainsaw), new ItemStack(Items.WATER_BUCKET),new ItemStack(ItemsTC.elementalAxe),new ItemStack(BlocksTC.logGreatwood), new ItemStack(Item.getByNameOrId("ic2:lapotron_crystal"),1,OreDictionary.WILDCARD_VALUE),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4),new ItemStack(Item.getByNameOrId("ic2:upgrade"),1,0));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.schainsaw"),streamChainsaw);


        InfusionRecipe thaumDrill = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.thaumiumdrill),2,new AspectList().add(Aspect.TOOL,15).add(Aspect.ENERGY,15).add(Aspect.AVERSION,15),new ItemStack(Item.getByNameOrId("ic2:diamond_drill"),1,OreDictionary.WILDCARD_VALUE), new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate),new ItemStack(EMTItems.materials_thaumiumplate), new ItemStack(Item.getByNameOrId("ic2:plate"),1,12),new ItemStack(Items.DIAMOND),new ItemStack(Items.DIAMOND));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.tdrill"),thaumDrill);
        InfusionRecipe rockDrill = new InfusionRecipe("EMT.TOOLS",new ItemStack(EMTItems.rockbreakerdrill),4,new AspectList().add(Aspect.TOOL,32).add(Aspect.ENERGY,45).add(Aspect.AVERSION,15).add(Aspect.WATER,32), new ItemStack(EMTItems.thaumiumdrill), new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(Items.FIRE_CHARGE),new ItemStack(ItemsTC.elementalPick),new ItemStack(ItemsTC.elementalShovel), new ItemStack(Item.getByNameOrId("ic2:energy_crystal"),1,OreDictionary.WILDCARD_VALUE),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,4),new ItemStack(Item.getByNameOrId("ic2:upgrade"),1,0),new ItemStack(Item.getByNameOrId("ic2:resource"),1,11));
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


        InfusionRecipe triple_solar = new InfusionRecipe("EMT.SOLAR", new ItemStack(EMTBlocks.solar_triplecompressed),4, new AspectList().add(primals(10)), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed), new ItemStack(EMTBlocks.solar_doublecompressed));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.solar_triple"),triple_solar);


        InfusionRecipe pot_generator = new InfusionRecipe("EMT.GENERATOR", new ItemStack(EMTBlocks.energy_generator),5,new AspectList().add(Aspect.ENERGY,64).add(Aspect.MAGIC,65).add(Aspect.METAL,64).add(Aspect.EXCHANGE,64).add(Aspect.MECHANISM,45),new ItemStack(Item.getByNameOrId("ic2:te"),1,7),new ItemStack(Items.DIAMOND),new ItemStack(BlocksTC.jarNormal),new ItemStack(Blocks.HOPPER),new ItemStack(Item.getByNameOrId("ic2:te"),1,78),new ItemStack(Item.getByNameOrId("ic2:te"),1,56),new ItemStack(Item.getByNameOrId("ic2:crafting"),1,23),new ItemStack(Item.getByNameOrId("ic2:resource"),1,13));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.pot_generator"),pot_generator);

        //UU_CrystalToMateraila

        InfusionRecipe crystUUtoCoal = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Items.COAL,16),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(Items.COAL,1,1),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoCoal"),crystUUtoCoal);

        InfusionRecipe crystUUtoDiam = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Items.DIAMOND),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(Items.GOLD_INGOT),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoDi"),crystUUtoDiam);
        InfusionRecipe crystUUtoGold = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Items.GOLD_INGOT,2),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(Items.IRON_INGOT),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoGold"),crystUUtoGold);

        InfusionRecipe crystUUtoCopperCr = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:crushed"),32,0),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(BlocksTC.stoneArcane),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoCopperCr"),crystUUtoCopperCr);
        InfusionRecipe crystUUtoLeadCr = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:crushed"),32,3),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(BlocksTC.amberBlock),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoLeadCr"),crystUUtoLeadCr);
        InfusionRecipe crystUUtoTinCr = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:crushed"),32,5),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(BlocksTC.stoneArcaneBrick),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoTinCr"),crystUUtoTinCr);
        InfusionRecipe crystUUtoIrid = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:misc_resource"),2,1),4,new AspectList().add(Aspect.FIRE,64).add(Aspect.WATER,64).add(Aspect.AIR,64).add(Aspect.EARTH,64).add(Aspect.ORDER,64).add(Aspect.ENTROPY,64),new ItemStack(Item.getByNameOrId("ic2:nuclear"),1,2),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoIrid"),crystUUtoIrid);
        InfusionRecipe crystUUtoUran = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:nuclear"),2,2),4,new AspectList().add(Aspect.FIRE,64).add(Aspect.WATER,64).add(Aspect.AIR,64).add(Aspect.EARTH,64).add(Aspect.ORDER,64).add(Aspect.ENTROPY,64),new ItemStack(Items.DIAMOND),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoUran"),crystUUtoUran);

        InfusionRecipe crystUUtoGlow = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Blocks.GLOWSTONE,4),4,new AspectList().add(Aspect.FIRE,8).add(Aspect.WATER,8).add(Aspect.AIR,8).add(Aspect.EARTH,8).add(Aspect.ORDER,8).add(Aspect.ENTROPY,8),new ItemStack(Items.GLOWSTONE_DUST),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoGlow"),crystUUtoGlow);
        InfusionRecipe crystUUtoIron = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Blocks.IRON_ORE,16),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(Blocks.STONEBRICK),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoIronOre"),crystUUtoIron);
        InfusionRecipe crystUUtoUranOre = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:resource"),8,4),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),new ItemStack(BlocksTC.amberBrick),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoUranOre"),crystUUtoUranOre);
        InfusionRecipe crystUUtoLapis = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Items.DYE,8,4),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),ConfigItems.AIR_CRYSTAL,new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoLapis"),crystUUtoLapis);
        InfusionRecipe crystUUtoRed = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Items.REDSTONE,24),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),ConfigItems.FIRE_CRYSTAL,new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoRed"),crystUUtoRed);
        InfusionRecipe crystUUtoResin = new InfusionRecipe("EMT.UU_M" ,new ItemStack(Item.getByNameOrId("ic2:misc_resource"),21,4),4,new AspectList().add(Aspect.FIRE,10).add(Aspect.WATER,10).add(Aspect.AIR,10).add(Aspect.EARTH,10).add(Aspect.ORDER,10).add(Aspect.ENTROPY,10),ConfigItems.WATER_CRYSTAL,new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),new ItemStack(EMTItems.materials_uumatterdrop),ConfigItems.EARTH_CRYSTAL,ConfigItems.EARTH_CRYSTAL);
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("EMT.uutoResin"),crystUUtoResin);
    }

    private static void $insertAspects() {



    }

    public static AspectList primals(int amount) {
        return new AspectList().add(Aspect.AIR, amount).add(Aspect.WATER, amount).add(Aspect.EARTH, amount).add(Aspect.FIRE, amount).add(Aspect.ORDER, amount).add(Aspect.ENTROPY, amount);
    }

    private static void appendAspects(String oreDict, AspectList toAdd) {
        List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
        if (toAdd == null) toAdd = new AspectList();
        if (ores != null && ores.size() > 0) for (ItemStack ore : ores) {
            try {
                ItemStack oc = ore.copy();
                oc.setCount(1);
                appendAspects(oc, toAdd);
            } catch (Exception ignored) {
            }
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
