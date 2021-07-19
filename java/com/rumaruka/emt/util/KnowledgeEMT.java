package com.rumaruka.emt.util;


import com.rumaruka.emt.api.ResearchAddendumBuilder;
import com.rumaruka.emt.api.ResearchEntryBuilder;
import com.rumaruka.emt.api.ResearchStageBuilder;
import com.rumaruka.emt.emt;
import com.rumaruka.emt.init.EMTBlocks;
import com.rumaruka.emt.init.EMTItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;

import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;



public class KnowledgeEMT  {
    public static final OnetimeCaller init = new OnetimeCaller(KnowledgeEMT::$init);
    public static final OnetimeCaller clInit = new OnetimeCaller(KnowledgeEMT::$);

    @SubscribeEvent
    public void commandEvent(CommandEvent ce)
    {
        if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload"))
        {
            new Thread(() ->
            {
                while(emt.RES_CAT.research.containsKey("EMT_FIRST"))
                    try
                    {
                        Thread.sleep(10L);
                    } catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                $init();
            }).start();
        }
    }

    private static void $init()
    {
        new REB().setBaseInfo("EMT.FIRST", "emt", 0, 0, new ResourceLocation(emt.MOD_ID,"textures/misc/emt.png")).setMeta(ResearchEntry.EnumResearchMeta.SPIKY).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":emt.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("BASICS"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":emt.2").build()).setParents("FIRSTSTEPS").buildAndRegister();

        new REB().setBaseInfo("EMT.TOOLS", "tools", 0, -2, new ItemStack(EMTItems.ironomnitool)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":tools.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":tools.2").setRecipes("EMT.electroST","EMT.diamondomnitool","emt:diamondchainsaw","EMT.ehor","EMT.rdrill","EMT.tdrill","EMT.schainsaw","EMT.tchainsaw","EMT.tomni","emt:ironomnitool").build()).setParents("EMT.FIRST").buildAndRegister();


        new REB().setBaseInfo("EMT.WINGS_B", "wings_b", -2, 0, new ItemStack(EMTItems.materials_feathergluedcardboardwing)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":wings_b.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":wings_b.2").setRecipes("emt:materials_feathergluedcardboardwing","EMT.TaintFeather").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.WINGS", "wings", -3, 0, new ItemStack(EMTItems.featherwing)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":wings.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":wings.2").setRecipes("emt:featherwing","emt:thaumiumwing","EMT.nanoW","EMT.qW").build()).setParents("EMT.WINGS_B").buildAndRegister();

        new REB().setBaseInfo("EMT.WINGS_B", "wings_b", -2, 0, new ItemStack(EMTItems.materials_feathergluedcardboardwing)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":wings_b.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":wings_b.2").setRecipes("EMT.TaintFeather").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.WINGS", "wings", -3, 0, new ItemStack(EMTItems.featherwing)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":wings.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":wings.2").setRecipes("EMT.nanoW","EMT.qW").build()).setParents("EMT.WINGS_B").buildAndRegister();


        new REB().setBaseInfo("EMT.SOLAR", "solar",2 , 0, new ItemStack(EMTBlocks.solar_compressed)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar.2").setRecipes("EMT.solar","EMT.solar_double","EMT.solar_triple").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_AIR", "solar_a",3 , 1, new ItemStack(EMTBlocks.solar_aircompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_a.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_a.2").setRecipes("EMT.solarAir","EMT.solarDAir","EMT.solarTAir").build()).setParents("EMT.SOLAR").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_EARTH", "solar_e",4 , 1, new ItemStack(EMTBlocks.solar_earthcompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_e.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_e.2").setRecipes("EMT.solarE","EMT.solarDE","EMT.solarTE").build()).setParents("EMT.SOLAR").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_EN", "solar_en",5 , 1, new ItemStack(EMTBlocks.solar_darkcompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_en.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_en.2").setRecipes("EMT.solarEn","EMT.solarDEn","EMT.solarTEn").build()).setParents("EMT.SOLAR").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_FIRE", "solar_f",3 , -1, new ItemStack(EMTBlocks.solar_firecompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_f.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_f.2").setRecipes("EMT.solarF","EMT.solarDF","EMT.solarTF").build()).setParents("EMT.SOLAR").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_ORDER", "solar_f",4 , -1, new ItemStack(EMTBlocks.solar_ordercompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_o.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_o.2").setRecipes("EMT.solarO","EMT.solarDO","EMT.solarTO").build()).setParents("EMT.SOLAR").buildAndRegister();
        new REB().setBaseInfo("EMT.SOLAR_WATER", "solar_w",5 , -1, new ItemStack(EMTBlocks.solar_watercompressed)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":solar_w.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":solar_w.2").setRecipes("EMT.solarW","EMT.solarDW","EMT.solarTW").build()).setParents("EMT.SOLAR").buildAndRegister();

        new REB().setBaseInfo("EMT.GENERATOR", "generator",0 , 2, new ItemStack(EMTBlocks.energy_generator)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":g.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":emt.2").setRecipes("EMT.pot_generator").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.GENERATOR_AIR", "generator_a",0 , 3, new ItemStack(EMTBlocks.aer_generator)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":ga.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":ga.2").setRecipes("EMT.gAir").build()).setParents("EMT.GENERATOR").buildAndRegister();
        new REB().setBaseInfo("EMT.GENERATOR_AUR", "generator_aur",1 , 3, new ItemStack(EMTBlocks.auram_generator)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":gaur.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":gaur.2").setRecipes("EMT.gAur").build()).setParents("EMT.GENERATOR").buildAndRegister();
        new REB().setBaseInfo("EMT.GENERATOR_FIRE", "generator_f",-1 , 3, new ItemStack(EMTBlocks.fire_generator)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":gf.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":gf.2").setRecipes("EMT.gIgnis").build()).setParents("EMT.GENERATOR").buildAndRegister();

        new REB().setBaseInfo("EMT.UUCr", "uu",-2 , 2, new ItemStack(EMTItems.materials_uumatterdrop)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":uu.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":uu.2").setRecipes("EMT.crystalUU").build()).setParents("EMT.FIRST","EMT.WINGS_B","UNLOCKALCHEMY").buildAndRegister();
        new REB().setBaseInfo("EMT.UU_M", "uu_m",-2 , 3, new ItemStack(Items.DIAMOND)).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.HEX).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":uu_m.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":uu_m.2").setRecipes("EMT.uutoCoal","EMT.uutoDi","EMT.uutoGold","EMT.uutoCopperCr","EMT.uutoLeadCr","EMT.uutoTinCr","EMT.uutoIrid","EMT.uutoUran","EMT.uutoGlow","EMT.uutoIronOre","EMT.uutoUranOre","EMT.uutoLapis","EMT.uutoRed","EMT.uutoResin").build()).setParents("EMT.UUCr").buildAndRegister();


    }






    private static class RAB extends ResearchAddendumBuilder
    {
    }

    private static class RSB extends ResearchStageBuilder
    {
    }

    private static class REB extends ResearchEntryBuilder
    {
        public ResearchEntryBuilder setBaseInfo(String key, String name, int x, int y, Object... icons)
        {
            return super.setBaseInfo(key, "EMT", "research_name." + emt.MOD_ID + ":" + name, x, y, icons);
        }
    }
    private static Method addResearchToCategory = null;
    public static void addResearchToCategory(ResearchEntry ri) {
        if(addResearchToCategory == null)
            try
            {
                addResearchToCategory = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
                addResearchToCategory.setAccessible(true);
            } catch(NoSuchMethodException | SecurityException e)
            {

            }

        try
        {
            addResearchToCategory.invoke(null, ri);
        } catch(Throwable e)
        {

        }

    }
    private static void $() {
    }
}
