package com.rumaruka.emt.item;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBase extends Item {

    public ItemBase(String unlocName, String textureName) {
        super();
        setUnlocalizedName(emt.MOD_ID + unlocName);
        setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);


    }

    ItemBase(String unlocName) {
        super();
        setUnlocalizedName(emt.MOD_ID + ".item." + unlocName);
       setCreativeTab(EMTCreativeTabs.EMT_CREATIVEtabs);
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return (EMTTextHelper.GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
