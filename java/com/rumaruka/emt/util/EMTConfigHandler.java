package com.rumaruka.emt.util;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class EMTConfigHandler {

    public static Configuration cfg;

    public static final String RANDOM = "Random Configs";
    public static final String RESEARCH = "Research";
    public static final String VALUES = "Numeric Values";
    public static final String OUTPUTS = "Essentia Generator Outputs";

    public static boolean enchanting;

    public static boolean toolsInBore;
    public static boolean impactOfRain;
    public static double compressedSolarOutput;
    public static double doubleCompressedSolarOutput;
    public static double tripleCompressedSolarOutput;
    public static double fireOutput;
    public static double waterOutput;
    public static double airOutput;
    public static double earthOutput;
    public static double orderOutput;
    public static double entropyOutput;
    public static double outputCap;
    public static int inventoryBaubleProdution;
    public static int armorBaubleProduction;
    public static boolean nightVisionOff;
    public static int essenstialGeneratorTicks;
    public static boolean thorHammerResearch;
    public static int maceratorBaseSpeed;
    public static int etherealProcessorBonus;
    public static double electricBootsUseEnergy;
    public static double nanoBootsUseEnergy;
    public static double quantumBootsUseEnergy;

    public static double electricBootsSpeedBust;
    public static double nanoBootsSpeedBust;
    public static double quantumBootsSpeedBust;

    public static double electricJumpBust;
    public static double nanoBootsJumpBust;
    public static double quantumBootsJumpBust;
    public static void init(File file) {
        cfg = new Configuration(file);
        syncConfig();
    }
    public static void syncConfig() {
        toolsInBore = cfg.get(RANDOM, "Tools for Arcane Bore", false, "This will augment the durablilty of the tools, and will also decrease the EU cost to 1. " + "The tools should have the same number of uses than before.").getBoolean(toolsInBore);
        enchanting = cfg.get(RANDOM, "Enable enchanting tools", false, "Warning: the enchantability is low.").getBoolean(enchanting);
        nightVisionOff = cfg.get(RANDOM, "Enable night vision", false, "Night vision.").getBoolean(nightVisionOff);
        impactOfRain = cfg.get(RANDOM, "Impact of rain", true, "The impact of rain on all wings").getBoolean(impactOfRain);
        maceratorBaseSpeed = cfg.get(VALUES, "Etheral Processor speed", 400, "Default is 400, the double of a regular furnace").getInt();
        etherealProcessorBonus = cfg.get(VALUES, "Ethereal Processor Bonus", 10, "This number is the chance of getting a Thaumium Ingot as a bonus when " + "processing an item in the machine. It is 1 out of the number you will enter. " + "The default is 1/10 chance.").getInt();


        airOutput = cfg.get(OUTPUTS, "Aer Output", 15000).getDouble(airOutput);
        waterOutput = cfg.get(OUTPUTS, "Aqua Output", 5000).getDouble(waterOutput);
        fireOutput = cfg.get(OUTPUTS, "Ignis Output", 20000).getDouble(fireOutput);
        orderOutput = cfg.get(OUTPUTS, "Ordo Output", 16000).getDouble(orderOutput);
        entropyOutput = cfg.get(OUTPUTS, "Perditio Output", 10000).getDouble(entropyOutput);
        earthOutput = cfg.get(OUTPUTS, "Terra Output", 2000).getDouble(earthOutput);

        electricBootsUseEnergy = cfg.get(VALUES,"Electric EU/tick",10,"How much electric boots use EU/tick! Default: 10 EU/Tick").getDouble(electricBootsUseEnergy);
        nanoBootsUseEnergy = cfg.get(VALUES,"Nano EU/tick",100,"How much nano boots use EU/tick! Default: 100 EU/Tick").getDouble(nanoBootsUseEnergy);
        quantumBootsUseEnergy = cfg.get(VALUES,"Quntum EU/tick",1000,"How much quantum boots use EU/tick! Default: 1000 EU/Tick").getDouble(quantumBootsUseEnergy);

        outputCap = cfg.get(OUTPUTS, "Output Cap", -1).getDouble(outputCap);
        inventoryBaubleProdution = cfg.get(VALUES, "Inventory Charging Ring production", 32, "Default is 32").getInt();

        armorBaubleProduction = cfg.get(VALUES, "Armor Charging Ring production", 32, "Default is 32").getInt();

        electricBootsSpeedBust = cfg.get(VALUES,"Electric Jump Boost",0.5,"Boost:").getDouble(electricBootsSpeedBust);
        nanoBootsUseEnergy = cfg.get(VALUES,"Nano Jump Boost",0.525,"Boost:").getDouble(nanoBootsSpeedBust);
        quantumBootsUseEnergy = cfg.get(VALUES,"Quantum Jump Boost",0.555,"Boost:").getDouble(quantumBootsSpeedBust);

        electricJumpBust = cfg.get(VALUES,"Electric Jump Boost",0.3,"Boost:").getDouble(electricJumpBust);
        nanoBootsJumpBust = cfg.get(VALUES,"Nano Jump Boost",0.35555,"Boost:").getDouble(nanoBootsJumpBust);
        quantumBootsJumpBust = cfg.get(VALUES,"Quantum Jump Boost",0.5,"Boost:").getDouble(quantumBootsJumpBust);


        compressedSolarOutput = cfg.get(VALUES, "Compressed Solar Panel Output", 10, "This is the number you have to modify if you want to make the Compressed Solar Panel yield " + "more or less EU per tick. Really, you shouldn't touch that, " + "since it's pretty balanced as it is.").getDouble(compressedSolarOutput);
        doubleCompressedSolarOutput = cfg.get(VALUES, "Double Compressed Solar Panel Output", 100, "This is the number you have to modify if you want to make the Double Compressed Solar Panel " + "yield more or less EU per tick. Really, you shouldn't touch that, " + "since it's pretty balanced as it is.").getDouble(doubleCompressedSolarOutput);
        tripleCompressedSolarOutput = cfg.get(VALUES, "Triple Compressed Solar Panel Output", 1000, "This is the number you have to modify if you want to make the Triple Compressed Solar Panel " + "yield more or less EU per tick. Really, you shouldn't touch that, " + "since it's pretty balanced as it is.").getDouble(tripleCompressedSolarOutput);
        essenstialGeneratorTicks = cfg.get(VALUES, "Ticks Generator", 120, "This is the number you have to modify if you want to make the Triple Compressed Solar Panel " + "yield more or less EU per tick. Really, you shouldn't touch that, " + "since it's pretty balanced as it is.").getInt(essenstialGeneratorTicks);
        thorHammerResearch = cfg.get(RESEARCH,"Mjolnir",false).getBoolean(thorHammerResearch);




        if (cfg.hasChanged()) {
            cfg.save();
        }
    }

}
