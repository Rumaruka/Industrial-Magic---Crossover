package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.emt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemQuantumWing  extends ItemNanoWing{
    public static int maxCharge = 10000000;
    public int tier = 4;

    public ItemQuantumWing(ArmorMaterial material, int par3, EntityEquipmentSlot par4) {
        super(material, par3, par4);
        this.setMaxStackSize(1);
        this.setMaxDamage(27);
        visDiscount = 6;
        transferLimit = 12000;
        energyPerDamage = 20000;
    }

    @Override
    public float getFallDamageMult() {
        return 0.0f;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH + ":textures/models/armor/quantumwing.png";
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        this.useWings(player, stack, world, 0.33f, 0.5f, 0.2f, 7);
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }
    @Override
    public int getTier(ItemStack itemStack) {
        return 4;
    }

    public double getDamageAbsorptionRatio() {
        return 1D;
    }
}
