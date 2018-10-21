package com.rumaruka.emt.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ItemIC2Baubles extends ItemBase implements IBauble {
    public static int wornTick;
    public Random random = new Random();

    public ItemIC2Baubles() {
        super("bauble");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);

        wornTick = 0;
    }



    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        if (itemStack.getItem() == EMTItems.emtbaubles_inventory) {
            return BaubleType.RING;
        }
        if (itemStack.getItem() == EMTItems.emtbaubles_armor) {
            return BaubleType.RING;
        }
        else {
            return null;
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (!player.world.isRemote) {
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() == EMTItems.emtbaubles_armor) {
                    if (player instanceof EntityPlayer) {
                        int energyLeft = EMTConfigHandler.armorBaubleProduction;
                        for (int i = 0; i < ((EntityPlayer) player).inventory.armorInventory.size(); i++) {
                            if (energyLeft > 0) {
                                if ((((EntityPlayer) player).inventory.armorInventory.get(i) != null) && (((EntityPlayer) player).inventory.armorInventory.get(i).getItem() instanceof IElectricItem)) {
                                    double sentPacket = ElectricItem.manager.charge(((EntityPlayer) player).inventory.armorInventory.get(i), energyLeft, 4, false, false);
                                    energyLeft -= sentPacket;
                                }
                            }
                        }
                    }
                }
            }
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() == EMTItems.emtbaubles_inventory) {
                    if (player instanceof EntityPlayer) {
                        int energyLeft = EMTConfigHandler.inventoryBaubleProdution;
                        for (int i = 0; i < ((EntityPlayer) player).inventory.mainInventory.size(); i++) {
                            if (energyLeft > 0) {
                                if ((((EntityPlayer) player).inventory.mainInventory.get(i) != null) && (((EntityPlayer) player).inventory.mainInventory.get(i).getItem() instanceof IElectricItem)) {
                                    double sentPacket = ElectricItem.manager.charge(((EntityPlayer) player).inventory.mainInventory.get(i), energyLeft, 4, false, false);
                                    energyLeft -= sentPacket;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}
