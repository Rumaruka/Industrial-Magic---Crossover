package com.rumaruka.emt.init;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.emt;
import com.rumaruka.emt.item.ItemElectricScribingTools;
import com.rumaruka.emt.item.ItemIC2Baubles;
import com.rumaruka.emt.item.ItemMaterials;
import com.rumaruka.emt.item.ItemOneRing;
import com.rumaruka.emt.item.armor.*;
import com.rumaruka.emt.item.tool.ItemElectricThorHammer;
import com.rumaruka.emt.item.tool.ItemThorHammer;
import com.rumaruka.emt.item.tool.ItemThorHammerBroken;
import com.rumaruka.emt.item.tool.*;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thaumcraft.api.ThaumcraftMaterials;

public class EMTItems {

    public static Item ironomnitool;
    public static Item diamondomnitool;
    public static Item thaumiumomnitool;
    public static Item featherwing;
    public static Item thaumiumwing;
    public static Item nanowing;
    public static Item quantumwing;
    public static Item thaumiumdrill;
    public static Item thaumiumchainsaw;
    public static Item diamondchainsaw;
    public static Item streamchainsaw;
    public static Item electrichoegrowth;
    public static Item thorhammer;
    public static Item rockbreakerdrill;

    public static Item taintedthorhammer;
    public static Item electricthorhammer;

    public static Item electricbootstraveller;
    public static Item nanobootstraveller;
    public static Item quantumbootstraveller;


    public static Item materials_oreclusteruranium;
    public static Item materials_crushedoreamber;
    public static Item materials_purifiedoreamber;
    public static Item materials_crushedorecinnabar;
    public static Item materials_purifiedorecinnabar;
    public static Item materials_thaumiumplate;
    public static Item materials_lightningsummoner;
    public static Item materials_feathermesh;
    public static Item materials_glue;
    public static Item materials_ducttape;
    public static Item materials_rubberball;
    public static Item materials_cardboard;
    public static Item materials_feathergluedcardboardwing;
    public static Item materials_taintedfeather;
    public static Item materials_thaumiumwing;
    public static Item materials_uumatterdrop;

    public static Item electricgoggles;
    public static Item nanogoggles;
    public static Item quantumgoggles;
    public static Item solarhelmetrevealing;

    public static Item emtbaubles_armor;
    public static Item emtbaubles_inventory;
    public static Item electricscribingtools;

    public static Item quantumarmor;
    public static Item quantumarmor_thaumium;
    public static Item quantumarmor_nano;
    public static Item quantumarmor_jetpack;
    public static Item quantumarmor_quantum;

    public static Item onering;


    public static ItemArmor.ArmorMaterial featherWingMaterial = EnumHelper.addArmorMaterial("FEATEHRWiNG", "emt:featherwing", 10, new int[]{2, 2, 2, 2}, 6, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static void init() {
        onering = new ItemOneRing().setUnlocalizedName("onering").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumarmor = new ItemInfusedQuantumChestplate("quantumarmor",8000000,100,4).setUnlocalizedName("quantumarmor").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumarmor_thaumium = new ItemInfusedQuantumChestplate("quantumarmor_thaumium",8000000,100,4).setUnlocalizedName("quantumarmor_thaumium").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumarmor_nano = new ItemInfusedQuantumChestplate("quantumarmor_nano",8000000,100,4).setUnlocalizedName("quantumarmor_nano").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumarmor_quantum = new ItemInfusedQuantumChestplate("quantumarmor_quantum",8000000,100,4).setUnlocalizedName("quantumarmor_quantum").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumarmor_jetpack = new ItemInfusedQuantumChestplate("quantumarmor_jetpack",8000000,100,4).setUnlocalizedName("quantumarmor_jetpack").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        ironomnitool = new ItemIronOmniTool().setUnlocalizedName("ironomnitool").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        diamondomnitool = new ItemDiamondOmniTool().setUnlocalizedName("diamondomnitool").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        thaumiumomnitool = new ItemThaumiumOmniTool().setUnlocalizedName("thaumiumomnitool").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        thaumiumdrill = new ItemThaumiumDrill().setUnlocalizedName("thaumiumdrill").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        thaumiumchainsaw = new ItemThaumiumChainsaw().setUnlocalizedName("thaumiumchainsaw").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        diamondchainsaw = new ItemDiamondChainsaw().setUnlocalizedName("diamondchainsaw").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        streamchainsaw = new ItemStreamChainsaw().setUnlocalizedName("streamchainsaw").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        electrichoegrowth = new ItemElectricHoeGrowth().setUnlocalizedName("electrichoegrowth").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        thorhammer = new ItemThorHammer().setUnlocalizedName("thorhammer").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        taintedthorhammer = new ItemThorHammerBroken().setUnlocalizedName("taintedthorhammer").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        electricthorhammer = new ItemElectricThorHammer().setUnlocalizedName("electricthorhammer").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        rockbreakerdrill = new ItemRockbreakerDrill().setUnlocalizedName("rockbreakerdrill").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        thaumiumwing = new ItemThaumiumReinforcedWing(ThaumcraftMaterials.ARMORMAT_THAUMIUM, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("thaumiumwing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        nanowing = new ItemNanoWing(ItemArmor.ArmorMaterial.DIAMOND, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("nanowing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumwing = new ItemQuantumWing(ItemArmor.ArmorMaterial.DIAMOND, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("quantumwing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        featherwing = new ItemFeatherWing(featherWingMaterial, 7, EntityEquipmentSlot.CHEST).setUnlocalizedName("featherwing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        electricbootstraveller = new ItemElectricBootsTraveller(ItemArmor.ArmorMaterial.DIAMOND,3,EntityEquipmentSlot.FEET).setUnlocalizedName("electricbootstraveller").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        nanobootstraveller = new ItemNanoBootsTraveller(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET).setUnlocalizedName("nanobootsraveller").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumbootstraveller = new ItemQuantumBootsTraveller(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET).setUnlocalizedName("quantumbootsraveller").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);


        materials_oreclusteruranium = new ItemMaterials().setUnlocalizedName("materials_oreclusteruranium").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_crushedoreamber = new ItemMaterials().setUnlocalizedName("materials_crushedoreamber").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_purifiedoreamber = new ItemMaterials().setUnlocalizedName("materials_purifiedoreamber").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_crushedorecinnabar = new ItemMaterials().setUnlocalizedName("materials_crushedorecinnabar").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_purifiedorecinnabar = new ItemMaterials().setUnlocalizedName("materials_purifiedorecinnabar").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_thaumiumplate = new ItemMaterials().setUnlocalizedName("materials_thaumiumplate").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_lightningsummoner = new ItemMaterials().setUnlocalizedName("materials_lightningsummoner").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_feathermesh = new ItemMaterials().setUnlocalizedName("materials_feathermesh").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_glue = new ItemMaterials().setUnlocalizedName("materials_glue").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_ducttape = new ItemMaterials().setUnlocalizedName("materials_ducttape").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_rubberball = new ItemMaterials().setUnlocalizedName("materials_rubberball").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_cardboard = new ItemMaterials().setUnlocalizedName("materials_cardboard").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_feathergluedcardboardwing = new ItemMaterials().setUnlocalizedName("materials_feathergluedcardboardwing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_taintedfeather = new ItemMaterials().setUnlocalizedName("materials_taintedfeather").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_thaumiumwing = new ItemMaterials().setUnlocalizedName("materials_thaumiumwing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        materials_uumatterdrop = new ItemMaterials().setUnlocalizedName("materials_uumatterdrop").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        electricgoggles = new ItemElectricGoggles(ItemArmor.ArmorMaterial.IRON,3,EntityEquipmentSlot.HEAD).setUnlocalizedName("electricgoggles").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        nanogoggles = new ItemNanoGoggles(ItemArmor.ArmorMaterial.DIAMOND,3,EntityEquipmentSlot.HEAD).setUnlocalizedName("nanogoggles").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        quantumgoggles = new ItemQuantumGoggles(ItemArmor.ArmorMaterial.DIAMOND,3,EntityEquipmentSlot.HEAD).setUnlocalizedName("quantumgoggles").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        solarhelmetrevealing = new ItemSolarHelmetRevealing(ItemArmor.ArmorMaterial.DIAMOND,3,EntityEquipmentSlot.HEAD).setUnlocalizedName("solarhelmetrevealing").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

        emtbaubles_armor = new ItemIC2Baubles().setUnlocalizedName("emtbaubles_armor").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        emtbaubles_inventory = new ItemIC2Baubles().setUnlocalizedName("emtbaubles_inventory").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
        electricscribingtools = new ItemElectricScribingTools( ).setUnlocalizedName("electricscribingtools").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    }


    public static void InGameRegister() {
        EMTItems.registerItem(ironomnitool, ironomnitool.getUnlocalizedName().substring(5));
        EMTItems.registerItem(diamondomnitool, diamondomnitool.getUnlocalizedName().substring(5));
        EMTItems.registerItem(thaumiumomnitool, thaumiumomnitool.getUnlocalizedName().substring(5));
        EMTItems.registerItem(thaumiumdrill, thaumiumdrill.getUnlocalizedName().substring(5));
        EMTItems.registerItem(featherwing, featherwing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(thaumiumchainsaw, thaumiumchainsaw.getUnlocalizedName().substring(5));
        EMTItems.registerItem(diamondchainsaw, diamondchainsaw.getUnlocalizedName().substring(5));
        EMTItems.registerItem(streamchainsaw, streamchainsaw.getUnlocalizedName().substring(5));
        EMTItems.registerItem(thaumiumwing, thaumiumwing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(nanowing, nanowing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumwing, quantumwing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(electrichoegrowth, electrichoegrowth.getUnlocalizedName().substring(5));
        EMTItems.registerItem(thorhammer, thorhammer.getUnlocalizedName().substring(5));
        EMTItems.registerItem(rockbreakerdrill, rockbreakerdrill.getUnlocalizedName().substring(5));
        EMTItems.registerItem(taintedthorhammer, taintedthorhammer.getUnlocalizedName().substring(5));
        EMTItems.registerItem(electricthorhammer, electricthorhammer.getUnlocalizedName().substring(5));

        EMTItems.registerItem(materials_oreclusteruranium, materials_oreclusteruranium.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_crushedoreamber, materials_crushedoreamber.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_purifiedoreamber, materials_purifiedoreamber.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_crushedorecinnabar, materials_crushedorecinnabar.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_purifiedorecinnabar, materials_purifiedorecinnabar.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_thaumiumplate, materials_thaumiumplate.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_feathermesh, materials_feathermesh.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_glue, materials_glue.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_ducttape, materials_ducttape.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_rubberball, materials_rubberball.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_cardboard, materials_cardboard.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_feathergluedcardboardwing, materials_feathergluedcardboardwing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_taintedfeather, materials_taintedfeather.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_thaumiumwing, materials_thaumiumwing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_uumatterdrop, materials_uumatterdrop.getUnlocalizedName().substring(5));
        EMTItems.registerItem(materials_lightningsummoner, materials_lightningsummoner.getUnlocalizedName().substring(5));

        EMTItems.registerItem(electricbootstraveller, electricbootstraveller.getUnlocalizedName().substring(5));
        EMTItems.registerItem(nanobootstraveller, nanobootstraveller.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumbootstraveller, quantumbootstraveller.getUnlocalizedName().substring(5));

        EMTItems.registerItem(electricgoggles, electricgoggles.getUnlocalizedName().substring(5));
        EMTItems.registerItem(nanogoggles, nanogoggles.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumgoggles, quantumgoggles.getUnlocalizedName().substring(5));
        EMTItems.registerItem(solarhelmetrevealing, solarhelmetrevealing.getUnlocalizedName().substring(5));
        EMTItems.registerItem(emtbaubles_armor, emtbaubles_armor.getUnlocalizedName().substring(5));
        EMTItems.registerItem(emtbaubles_inventory, emtbaubles_inventory.getUnlocalizedName().substring(5));
        EMTItems.registerItem(electricscribingtools, electricscribingtools.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumarmor, quantumarmor.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumarmor_nano, quantumarmor_nano.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumarmor_quantum, quantumarmor_quantum.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumarmor_thaumium, quantumarmor_thaumium.getUnlocalizedName().substring(5));
        EMTItems.registerItem(quantumarmor_jetpack, quantumarmor_jetpack.getUnlocalizedName().substring(5));
        EMTItems.registerItem(onering, onering.getUnlocalizedName().substring(5));

    }

    @Deprecated
    public static void registerItem(Item item, String name) {
        if (item.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a item with no name: " + item);

        ForgeRegistries.ITEMS.register(item.getRegistryName() == null ? item.setRegistryName(name) : item);
    }

    public static void Renders() {

        renderItems(ironomnitool);
        renderItems(diamondomnitool);
        renderItems(thaumiumomnitool);
        renderItems(thaumiumdrill);
        renderItems(thaumiumchainsaw);
        renderItems(diamondchainsaw);
        renderItems(streamchainsaw);
        renderItems(featherwing);
        renderItems(quantumwing);
        renderItems(thaumiumwing);
        renderItems(nanowing);
        renderItems(electrichoegrowth);
        renderItems(thorhammer);
        renderItems(rockbreakerdrill);
        renderItems(taintedthorhammer);
        renderItems(electricthorhammer);
        renderItems(materials_oreclusteruranium);
        renderItems(materials_crushedoreamber);
        renderItems(materials_purifiedoreamber);
        renderItems(materials_crushedorecinnabar);
        renderItems(materials_purifiedorecinnabar);
        renderItems(materials_thaumiumplate);
        renderItems(materials_feathermesh);
        renderItems(materials_glue);
        renderItems(materials_ducttape);
        renderItems(materials_rubberball);
        renderItems(materials_cardboard);
        renderItems(materials_feathergluedcardboardwing);
        renderItems(materials_taintedfeather);
        renderItems(materials_thaumiumwing);
        renderItems(materials_uumatterdrop);
        renderItems(materials_lightningsummoner);
        renderItems(electricbootstraveller);
        renderItems(nanobootstraveller);
        renderItems(quantumbootstraveller);
        renderItems(electricgoggles);
        renderItems(nanogoggles);
        renderItems(quantumgoggles);
        renderItems(solarhelmetrevealing);
        renderItems(emtbaubles_armor);
        renderItems(emtbaubles_inventory);
        renderItems(electricscribingtools);
        renderItems(quantumarmor);
        renderItems(quantumarmor_nano);
        renderItems(quantumarmor_quantum);
        renderItems(quantumarmor_thaumium);
        renderItems(quantumarmor_jetpack);
        renderItems(onering);


    }


    public static void renderItems(Item i) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(
                emt.MOD_ID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
    }

}
