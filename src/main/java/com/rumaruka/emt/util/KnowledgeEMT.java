package com.rumaruka.emt.util;

import com.rumaruka.emt.api.ResearchAddendumBuilder;
import com.rumaruka.emt.api.ResearchEntryBuilder;
import com.rumaruka.emt.api.ResearchStageBuilder;
import com.rumaruka.emt.emt;
import com.rumaruka.emt.init.EMTItems;
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
        new REB().setBaseInfo("EMT.TOOLS", "emt", 0, -2, new ItemStack(EMTItems.ironomnitool)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":emt.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":emt.2").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.WINGS", "emt", 0, -4, new ItemStack(EMTItems.quantumwing)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":emt.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":emt.2").build()).setParents("EMT.FIRST").buildAndRegister();
        new REB().setBaseInfo("EMT.ARMORS", "emt", 0, -6, new ItemStack(EMTItems.quantumarmor)).setStages(new RSB().setText("research_stage." + emt.MOD_ID + ":emt.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 1)).build(), new RSB().setText("research_stage." + emt.MOD_ID + ":emt.2").build()).setParents("EMT.FIRST").buildAndRegister();

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
