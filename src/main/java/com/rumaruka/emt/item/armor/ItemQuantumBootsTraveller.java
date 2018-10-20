package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.emt;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemQuantumBootsTraveller extends ItemElectricBootsTraveller{

    public ItemQuantumBootsTraveller(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn,renderIndexIn,equipmentSlotIn);
        maxCharge = 10000000;
        visDiscount = 5;
        transferLimit = 12000;
        energyPerDamage = 20000;
    }
    @Override
    public double getDamageAbsorptionRatio() {
        return 1D;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 4;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH + ":textures/models/armor/quantumbootstravel.png";
    }
}
