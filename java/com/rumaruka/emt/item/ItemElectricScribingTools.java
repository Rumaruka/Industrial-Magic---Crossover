package com.rumaruka.emt.item;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import ic2.core.IC2;
import ic2.core.item.BaseElectricItem;
import ic2.core.item.IPseudoDamageItem;
import ic2.core.item.tool.ItemElectricTool;
import ic2.core.util.LogCategory;
import ic2.core.util.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.common.items.ItemTCBase;
import thaumcraft.common.items.tools.ItemScribingTools;
import thaumcraft.common.tiles.crafting.TileResearchTable;

import javax.annotation.Nullable;
import java.util.List;

public class ItemElectricScribingTools extends Item implements IScribeTools, IElectricItem, IPseudoDamageItem {

    private int maxChange = 400;
    private int cost = 10;

    public ItemElectricScribingTools() {
        super();
        setMaxDamage(maxChange);
        setNoRepair();
        this.setMaxStackSize(1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab== EMTCreativeTabs.EMT_CREATIVEtabs){
            ItemStack itemStack = new ItemStack(this, 1);
            if (getChargedItem(itemStack) == this) {
                ItemStack charged = new ItemStack(this, 1);
                ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
                items.add(charged);
            }
            if (getEmptyItem(itemStack) == this) {
                items.add(new ItemStack(this, 1, getMaxDamage()));
            }
        }
    }



    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }


    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxChange;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 1;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 5;
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        ElectricItem.manager.use(stack,cost,null);
    }

    @Override
    public void setStackDamage(ItemStack itemStack, int i) {
        super.setDamage(itemStack, i);
    }
}
