package com.rumaruka.emt;


import com.rumaruka.emt.command.CommandOutput;
import com.rumaruka.emt.init.EMTBlocks;
import com.rumaruka.emt.init.EMTEntities;
import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.init.EMTOreDictionary;
import com.rumaruka.emt.init.EMTRecipes;
import com.rumaruka.emt.init.EMTTile;
import com.rumaruka.emt.proxy.CommonProxy;
import com.rumaruka.emt.util.EMTClientHandler;
import com.rumaruka.emt.util.EMTConfigHandler;
import com.rumaruka.emt.util.EMTEssentiasOutputs;
import com.rumaruka.emt.util.EMTEventHandler;
import com.rumaruka.emt.util.EMTIC2Recipes;
import com.rumaruka.emt.util.EMTThauminiconsRecipes;
import com.rumaruka.emt.util.KnowledgeEMT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

@Mod(
        modid = EMT.MOD_ID,
        name = EMT.NAME,
        version = EMT.VERSION,
        dependencies = EMT.DEPENDS

)
public class EMT {


    public static final String NAME = "Electro-Magic Tools";
    public static final String MOD_ID = "emt";
    public static final String VERSION = "B_EMT3.4";
    public static final String TEXTURE_PATH = "emt";
    public static final String CLIENT_PROXY = "com.rumaruka.emt.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.rumaruka.emt.proxy.CommonProxy";
    public static final String CHANNEL = "EMT";
    public static final String DEPENDS = "required-after:thaumcraft@[6.1.BETA26,);required-after:ic2@[2.8.93-ex112,)";

    @SidedProxy(
            clientSide = CLIENT_PROXY,
            serverSide = COMMON_PROXY
    )
    public static CommonProxy proxy;
  //  public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
    public static ResearchCategory RES_CAT;




    @Instance(MOD_ID)
    public static EMT instance;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {


        instance = this;
        EMTBlocks.init();
        EMTBlocks.InGameRegister();

        EMTItems.init();
        EMTItems.InGameRegister();

        EMTConfigHandler.init(e.getSuggestedConfigurationFile());

        FMLCommonHandler.instance().bus().register(new EMTEventHandler());
        if(FMLCommonHandler.instance().getSide().isClient())
            FMLCommonHandler.instance().bus().register(new EMTClientHandler());

        MinecraftForge.EVENT_BUS.register(new KnowledgeEMT());
        KnowledgeEMT.clInit.call();
        EMTEssentiasOutputs.addPrimalOutputs();
        EMTEssentiasOutputs.addOutputs();

        registerPackets();







    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        EMTOreDictionary.setup();

        RES_CAT = ResearchCategories.registerCategory(catName,null,null,icon,back,back2);
        EMTEntities.registerEMTEntities();
        EMTRecipes.setup();
        EMTThauminiconsRecipes.setup();
        EMTIC2Recipes.setup();
        EMTTile.registerTiles();


        proxy.load();

    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.registerRenders();



        KnowledgeEMT.init.call();
        EMTThauminiconsRecipes.insertAspects.call();
    }

    @Mod.EventHandler
    public void onFMLServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandOutput());

    }

    public void registerPackets() {

    }
    public static final String catName ="EMT";
    public static final ResourceLocation icon = new ResourceLocation("emt","textures/misc/emt.png");
    public static final ResourceLocation back = new ResourceLocation("emt","textures/misc/background.png");
    public static final ResourceLocation back2 = new ResourceLocation("thaumcraft","textures/gui/gui_research_back_over.png");
}
