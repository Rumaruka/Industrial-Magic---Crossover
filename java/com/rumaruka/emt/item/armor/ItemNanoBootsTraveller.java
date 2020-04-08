package com.rumaruka.emt.item.armor;


import com.rumaruka.emt.emt;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemNanoBootsTraveller extends ItemElectricBootsTraveller {
    public ItemNanoBootsTraveller(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        maxCharge = 1000000;
        visDiscount = 4;
        transferLimit = 1600;
        energyPerDamage = 5000;
    }
    @Override
    public double getDamageAbsorptionRatio() {
        return 0.9D;
    }
    @Override
    public int getTier(ItemStack itemStack) {
        return 3;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH + ":textures/models/armor/nanobootstravel.png";
    }
}
