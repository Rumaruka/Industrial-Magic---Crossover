//package com.rumaruka.emt.init;
//
//import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
//import com.rumaruka.emt.emt;
//import com.rumaruka.emt.tile.solar.compressed.TileEntityCompressedSolar;
//import ic2.api.tile.IEnergyStorage;
//import ic2.core.Version;
//import ic2.core.block.*;
//import ic2.core.item.block.ItemBlockTileEntity;
//import ic2.core.ref.TeBlock;
//import ic2.core.util.StackUtil;
//import ic2.core.util.Util;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.item.EnumRarity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.common.Loader;
//import net.minecraftforge.fml.common.ModContainer;
//import net.minecraftforge.fml.common.registry.GameRegistry;
//
//import java.util.Set;
//
//public enum EMTTeBlocks implements ITeBlock, ITeBlock.ITeBlockCreativeRegisterer {
//    emtsolar_one(TileEntityCompressedSolar.class,0,EnumRarity.RARE);
//
//
//
//
//
//
//    private final Class<? extends TileEntityBlock> teClass;
//    private final int itemMeta;
//    private final EnumRarity rarity;
//    private TileEntityBlock dummyTe;
//    public static final EMTTeBlocks[] values = values();
//    private EMTTeBlocks(Class<? extends TileEntityBlock> teClass, int itemMeta)
//    {
//        this(teClass, itemMeta, EnumRarity.UNCOMMON);
//    }
//
//    private EMTTeBlocks(Class<? extends TileEntityBlock> teClass, int itemMeta, EnumRarity rarity)
//    {
//        this.teClass = teClass;
//        this.itemMeta = itemMeta;
//        this.rarity = rarity;
//
//        GameRegistry.registerTileEntity(teClass, getName());
//    }
//
//    public boolean hasItem()
//    {
//        return true;
//    }
//
//    public String getName()
//    {
//        return name();
//    }
//
//    public int getId()
//    {
//        return this.itemMeta;
//    }
//
//    public ResourceLocation getIdentifier()
//    {
//        return IDENTITY;
//    }
//
//    public Class<? extends TileEntityBlock> getTeClass()
//    {
//        return this.teClass;
//    }
//
//    @Override
//    public boolean hasActive() {
//        return true;
//    }
//
//
//    public float getHardness()
//    {
//        return 3.0F;
//    }
//
//    public float getExplosionResistance()
//    {
//        return 15.0F;
//    }
//
//    public TeBlock.HarvestTool getHarvestTool()
//    {
//        return TeBlock.HarvestTool.Pickaxe;
//    }
//
//    public TeBlock.DefaultDrop getDefaultDrop()
//    {
//        return TeBlock.DefaultDrop.Self;
//    }
//
//    public boolean allowWrenchRotating()
//    {
//        return false;
//    }
//
//    public Set<EnumFacing> getSupportedFacings()
//    {
//        return Util.horizontalFacings;
//    }
//
//    public EnumRarity getRarity()
//    {
//        return this.rarity;
//    }
//
//    public static void buildDummies()
//    {
//        ModContainer mc = Loader.instance().activeModContainer();
//        if ((mc == null) || (!"emt".equals(mc.getModId()))) {
//            throw new IllegalAccessError("Don't mess with this please.");
//        }
//        for (EMTTeBlocks block : VALUES) {
//            if (block.teClass != null) {
//                try
//                {
//                    block.dummyTe = ((TileEntityBlock)block.teClass.newInstance());
//                }
//                catch (Exception e)
//                {
//                    if (Util.inDev()) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    public TileEntityBlock getDummyTe()
//    {
//        return this.dummyTe;
//    }
//
//    private static final EMTTeBlocks[] VALUES = values();
//    public static final ResourceLocation IDENTITY = new ResourceLocation(emt.MOD_ID, "machines");
//
//    @Override
//    public void addSubBlocks(NonNullList<ItemStack> list, BlockTileEntity block, ItemBlockTileEntity itemBlockTileEntity, CreativeTabs creativeTabs) {
//        if (creativeTabs == EMTCreativeTabs.EMT_CREATIVEtabs) {
//            EMTTeBlocks[] var5 = values;
//            int var6 = var5.length;
//
//            for(int var7 = 0; var7 < var6; ++var7) {
//                EMTTeBlocks type = var5[var7];
//                if (type.hasItem() && Version.shouldEnable(type.teClass)) {
//                    list.add(block.getItemStack(type));
//                    if (type.getDummyTe() instanceof IEnergyStorage) {
//                        ItemStack filled = block.getItemStack(type);
//                        StackUtil.getOrCreateNbtData(filled).setDouble("energy", (double)((IEnergyStorage)type.getDummyTe()).getCapacity());
//                        list.add(filled);
//                    }
//                }
//            }
//        }
//    }
//}