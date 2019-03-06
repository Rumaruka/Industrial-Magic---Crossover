package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemThaumiumDrill extends ItemPickaxe implements IElectricItem {



    public int maxCharge = 100000;
    public int cost;
    public int tier = 2;
    public double transferLimit = 100;

    public ItemThaumiumDrill() {
        super(ToolMaterial.DIAMOND);
        this.efficiency = 20F;
        this.setMaxStackSize(1);
        if (EMTConfigHandler.toolsInBore == false) {
            this.setMaxDamage(27);
        }
        else {
            this.setMaxDamage(240);
        }

    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab==EMTCreativeTabs.EMT_CREATIVEtabs){
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

    private Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    private Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (EMTConfigHandler.toolsInBore == false) {
            cost = 250;
        }
        else {
            cost = 1;
        }
        ElectricItem.manager.use(stack, cost, entityLiving);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.IRON_PICKAXE.canHarvestBlock(state, stack) || Items.IRON_SHOVEL.canHarvestBlock(state, stack);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(!ElectricItem.manager.canUse(stack,cost)){
            return 0.0f;
        }
        if (Items.WOODEN_PICKAXE.getDestroySpeed(stack, state) > 1.0F || Items.WOODEN_SHOVEL.getDestroySpeed(stack, state) > 1.0F) {
            return efficiency;
        }
        else {
            return super.getDestroySpeed(stack, state);
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if (EMTConfigHandler.enchanting == false) {
            return 0;
        }
        else {
            return 4;
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EMTConfigHandler.enchanting;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return tier;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }
}
