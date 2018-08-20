package com.rumaruka.emt.client.creativetabs;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.init.EMTItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class EMTCreativeTabs extends CreativeTabs {
    public static EMTCreativeTabs EMT_CREATIVEtabs = new EMTCreativeTabs();

    public EMTCreativeTabs() {
        super(emt.MOD_ID);
        setBackgroundImageName("emt.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(EMTItems.ironomnitool);
    }

  

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> itemStacks) {
        super.displayAllRelevantItems(itemStacks);
    }
}
