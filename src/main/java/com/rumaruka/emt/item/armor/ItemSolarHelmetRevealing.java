package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.emt;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.item.armor.ItemArmorSolarHelmet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ItemSolarHelmetRevealing extends  ItemQuantumGoggles {
    private int genDay;
    private int genNight;
    private static final Map potionCost = new HashMap();

    public ItemSolarHelmetRevealing(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        this.genDay = 65536;
        this.genNight = 4096;
        maxCharge = 20000000;
        visDiscount = 7;
        tier = 4;
        transferLimit = 18000;
        energyPerDamage = 20000;

        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.POISON)), Integer.valueOf(10000));
        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.WITHER)), Integer.valueOf(15000));
        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.NAUSEA)), Integer.valueOf(5000));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH+":textures/models/armor/solarrevealinghelmet.png";
    }
    @Override
    public double getDamageAbsorptionRatio() {
        return 1D;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 4;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        super.onArmorTick(world, player, itemStack);

        if (world.isRemote) {
            return;
        }

        if(world.canBlockSeeSky(new BlockPos((int)player.posX, (int)player.posY + 1, (int)player.posZ))&& !world.isRaining()) {
            double enerj = (world.isDaytime() ? (double)genDay : (double) genNight) / 12000D;

            for (int i = 0; i < player.inventory.armorInventory.size(); i++) {
                if (enerj > 0) {
                    if ((player.inventory.mainInventory.get(i) != ItemStack.EMPTY) && (player.inventory.mainInventory.get(i) .getItem() instanceof IElectricItem)) {
                        double sentPacket = ElectricItem.manager.charge(player.inventory.mainInventory.get(i) , enerj ,4, false, false);
                        enerj -= sentPacket;
                    }
                } else {
                    return;
                }
            }
            for (int j = 0; j < player.inventory.mainInventory.size(); j++) {
                if (enerj > 0) {
                    if ((player.inventory.mainInventory.get(j) != ItemStack.EMPTY) && (player.inventory.mainInventory.get(j).getItem() instanceof IElectricItem)) {
                        double sentPacket = ElectricItem.manager.charge(player.inventory.mainInventory.get(j), enerj, 4, false, false);
                        enerj -= sentPacket;
                    }
                } else {
                    return;
                }
            }
        }
    }
}

