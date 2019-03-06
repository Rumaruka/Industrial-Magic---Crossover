package com.rumaruka.emt.item;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.item.IPseudoDamageItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import thaumcraft.api.items.IScribeTools;

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
