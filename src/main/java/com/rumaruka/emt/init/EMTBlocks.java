package com.rumaruka.emt.init;

import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import com.rumaruka.emt.block.generator.*;
import com.rumaruka.emt.block.itemblocks.ItemAerGenerator;
import com.rumaruka.emt.block.itemblocks.ItemAuramGenerator;
import com.rumaruka.emt.block.itemblocks.ItemEnergyGenerator;
import com.rumaruka.emt.block.itemblocks.ItemIgnisGenerator;
import com.rumaruka.emt.block.solar.air.BlockAirCompressedSolar;
import com.rumaruka.emt.block.solar.air.BlockAirDoubleCompressedSolar;
import com.rumaruka.emt.block.solar.air.BlockAirTripleCompressedSolar;
import com.rumaruka.emt.block.solar.compressed.BlockCompressedSolar;
import com.rumaruka.emt.block.solar.compressed.BlockDoubleCompressedSolar;
import com.rumaruka.emt.block.solar.compressed.BlockTripleCompressedSolar;
import com.rumaruka.emt.block.solar.dark.BlockDarkCompressedSolar;
import com.rumaruka.emt.block.solar.dark.BlockDoubleDarkCompressedSolar;
import com.rumaruka.emt.block.solar.dark.BlockTripleDarkCompressedSolar;
import com.rumaruka.emt.block.solar.earth.BlockDoubleEarthCompressedSolar;
import com.rumaruka.emt.block.solar.earth.BlockEarthCompressedSolar;
import com.rumaruka.emt.block.solar.earth.BlockTripleEarthCompressedSolar;
import com.rumaruka.emt.block.solar.fire.BlockDoubleFireCompressedSolar;
import com.rumaruka.emt.block.solar.fire.BlockFireCompressedSolar;
import com.rumaruka.emt.block.solar.fire.BlockTripleFireCompressedSolar;
import com.rumaruka.emt.block.solar.order.BlockDoubleOrderCompressedSolar;
import com.rumaruka.emt.block.solar.order.BlockOrderCompressedSolar;
import com.rumaruka.emt.block.solar.order.BlockTripleOrderCompressedSolar;
import com.rumaruka.emt.block.solar.water.BlockDoubleWaterCompressedSolar;
import com.rumaruka.emt.block.solar.water.BlockTripleWaterCompressedSolar;
import com.rumaruka.emt.block.solar.water.BlockWaterCompressedSolar;
import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.emt;
import ic2.core.ref.TeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Constructor;

public class EMTBlocks {

    //Common Solar
    public static Block solar_compressed;
    public static Block solar_doublecompressed;
    public static Block solar_triplecompressed;

    //Air Solar
    public static Block solar_aircompressed;
    public static Block solar_airdoublecompressed;
    public static Block solar_airtriplecompressed;
    //Dark Solar
    public static Block solar_darkcompressed;
    public static Block solar_darkdoublecompressed;
    public static Block solar_darktriplecompressed;
    //Earth Solar
    public static Block solar_earthcompressed;
    public static Block solar_earthdoublecompressed;
    public static Block solar_earthtriplecompressed;
    //Fire Solar
    public static Block solar_firecompressed;
    public static Block solar_firedoublecompressed;
    public static Block solar_firetriplecompressed;

    //Order Solar
    public static Block solar_ordercompressed;
    public static Block solar_orderdoublecompressed;
    public static Block solar_ordertriplecompressed;
    //Water Solar
    public static Block solar_watercompressed;
    public static Block solar_waterdoublecompressed;
    public static Block solar_watertriplecompressed;

    //Generaor
    public static Block aer_generator;
    public static Block auram_generator;
    public static Block fire_generator;
    public static Block energy_generator;


    public static void init(){
    solar_compressed = new BlockCompressedSolar(Material.IRON).setUnlocalizedName("solar_compressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_doublecompressed = new BlockDoubleCompressedSolar(Material.IRON).setUnlocalizedName("solar_doublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_triplecompressed = new BlockTripleCompressedSolar(Material.IRON).setUnlocalizedName("solar_triplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_aircompressed = new BlockAirCompressedSolar(Material.IRON).setUnlocalizedName("solar_aircompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_airdoublecompressed = new BlockAirDoubleCompressedSolar(Material.IRON).setUnlocalizedName("solar_airdoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_airtriplecompressed = new BlockAirTripleCompressedSolar(Material.IRON).setUnlocalizedName("solar_airtriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_darkcompressed = new BlockDarkCompressedSolar(Material.IRON).setUnlocalizedName("solar_darkcompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_darkdoublecompressed = new BlockDoubleDarkCompressedSolar(Material.IRON).setUnlocalizedName("solar_darkdoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_darktriplecompressed = new BlockTripleDarkCompressedSolar(Material.IRON).setUnlocalizedName("solar_darktriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_earthcompressed = new BlockEarthCompressedSolar(Material.IRON).setUnlocalizedName("solar_earthcompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_earthdoublecompressed = new BlockDoubleEarthCompressedSolar(Material.IRON).setUnlocalizedName("solar_earthdoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_earthtriplecompressed = new BlockTripleEarthCompressedSolar(Material.IRON).setUnlocalizedName("solar_earthtriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_ordercompressed = new BlockOrderCompressedSolar(Material.IRON).setUnlocalizedName("solar_ordercompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_orderdoublecompressed = new BlockDoubleOrderCompressedSolar(Material.IRON).setUnlocalizedName("solar_orderdoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_ordertriplecompressed = new BlockTripleOrderCompressedSolar(Material.IRON).setUnlocalizedName("solar_ordertriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_firecompressed = new BlockFireCompressedSolar(Material.IRON).setUnlocalizedName("solar_firecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_firedoublecompressed = new BlockDoubleFireCompressedSolar(Material.IRON).setUnlocalizedName("solar_firedoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_firetriplecompressed = new BlockTripleFireCompressedSolar(Material.IRON).setUnlocalizedName("solar_firetriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    solar_watercompressed = new BlockWaterCompressedSolar(Material.IRON).setUnlocalizedName("solar_watercompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_waterdoublecompressed = new BlockDoubleWaterCompressedSolar(Material.IRON).setUnlocalizedName("solar_waterdoublecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    solar_watertriplecompressed = new BlockTripleWaterCompressedSolar(Material.IRON).setUnlocalizedName("solar_watertriplecompressed").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);

    aer_generator=new BlockAerEssentialGenerator(Material.IRON).setUnlocalizedName("aer_generator").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    auram_generator=new BlockAuramEssentialGenerator(Material.IRON).setUnlocalizedName("auram_generator").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    fire_generator=new BlockFireEssentialGenerator(Material.IRON).setUnlocalizedName("fire_generator").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    energy_generator=new BlockEnergyEssentialGenerator(Material.IRON).setUnlocalizedName("energy_generator").setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);


    }

public static void InGameRegister(){
    registerBlock(solar_compressed,solar_compressed.getUnlocalizedName().substring(5));
    registerBlock(solar_doublecompressed,solar_doublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_triplecompressed,solar_triplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_aircompressed,solar_aircompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_airdoublecompressed,solar_airdoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_airtriplecompressed,solar_airtriplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_firecompressed,solar_firecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_firedoublecompressed,solar_firedoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_firetriplecompressed,solar_firetriplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_earthcompressed,solar_earthcompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_earthdoublecompressed,solar_earthdoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_earthtriplecompressed,solar_earthtriplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_watercompressed,solar_watercompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_waterdoublecompressed,solar_waterdoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_watertriplecompressed,solar_watertriplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_darkcompressed,solar_darkcompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_darkdoublecompressed,solar_darkdoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_darktriplecompressed,solar_darktriplecompressed.getUnlocalizedName().substring(5));

    registerBlock(solar_ordercompressed,solar_ordercompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_orderdoublecompressed,solar_orderdoublecompressed.getUnlocalizedName().substring(5));
    registerBlock(solar_ordertriplecompressed,solar_ordertriplecompressed.getUnlocalizedName().substring(5));


    registerBlock(aer_generator, ItemAerGenerator.class,aer_generator.getUnlocalizedName().substring(5));
    registerBlock(auram_generator, ItemAuramGenerator.class,auram_generator.getUnlocalizedName().substring(5));
    registerBlock(energy_generator, ItemEnergyGenerator.class,energy_generator.getUnlocalizedName().substring(5));
    registerBlock(fire_generator, ItemIgnisGenerator.class,fire_generator.getUnlocalizedName().substring(5));




}

public static void Render(){
    registerRender(solar_compressed);
    registerRender(solar_doublecompressed);
    registerRender(solar_triplecompressed);

    registerRender(solar_aircompressed);
    registerRender(solar_airdoublecompressed);
    registerRender(solar_airtriplecompressed);

    registerRender(solar_darkcompressed);
    registerRender(solar_darkdoublecompressed);
    registerRender(solar_darktriplecompressed);

    registerRender(solar_watercompressed);
    registerRender(solar_waterdoublecompressed);
    registerRender(solar_watertriplecompressed);

    registerRender(solar_firecompressed);
    registerRender(solar_firedoublecompressed);
    registerRender(solar_firetriplecompressed);

    registerRender(solar_earthcompressed);
    registerRender(solar_earthdoublecompressed);
    registerRender(solar_earthtriplecompressed);

    registerRender(solar_ordercompressed);
    registerRender(solar_orderdoublecompressed);
    registerRender(solar_ordertriplecompressed);

    registerRender(aer_generator);
    registerRender(auram_generator);
    registerRender(fire_generator);
    registerRender(energy_generator);

}

















    @Deprecated
    public static Block registerBlock(Block block)
    {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }
    @Deprecated
    public static Block registerBlock(Block block, String name)
    {
        if (block.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a Block with no name: " + block);
        if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
            throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
        return registerBlock(block.getRegistryName() != null ? block : block.setRegistryName(name));
    }
    @Deprecated
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs)
    {
        if (Strings.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("Attempted to register a block with no name: " + block);
        }
        if (Loader.instance().isInState(LoaderState.CONSTRUCTING))
        {
            FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", Loader.instance().activeModContainer());
        }
        try
        {
            assert block != null : "registerBlock: block cannot be null";
            if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
                throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
            ItemBlock i = null;
            if (itemclass != null)
            {
                Class<?>[] ctorArgClasses = new Class<?>[itemCtorArgs.length + 1];
                ctorArgClasses[0] = Block.class;
                for (int idx = 1; idx < ctorArgClasses.length; idx++)
                {
                    ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
                }
                Constructor<? extends ItemBlock> itemCtor = itemclass.getConstructor(ctorArgClasses);
                i = itemCtor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
            }
            // block registration has to happen first
            ForgeRegistries.BLOCKS.register(block.getRegistryName() == null ? block.setRegistryName(name) : block);
            if (i != null)
                ForgeRegistries.ITEMS.register(i.setRegistryName(name));
            return block;
        } catch (Exception e)
        {
            FMLLog.log(Level.ERROR, e, "Caught an exception during block registration");
            throw new LoaderException(e);
        }
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(emt.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
