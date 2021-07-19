package com.rumaruka.emt.item.armor.boots;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Collections;

public class ItemQuantumBootsTraveller extends ItemElectricBootsTraveller{

    public ItemQuantumBootsTraveller(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn,renderIndexIn,equipmentSlotIn);
        maxCharge = 10000000;
        visDiscount = 5;
        transferLimit = 12000;
        energyPerDamage = 20000;
        userEnergy= EMTConfigHandler.quantumBootsUseEnergy;
        speedBonus= (float) EMTConfigHandler.quantumBootsJumpBust;
        jumpBonus= (float) EMTConfigHandler.quantumBootsJumpBust;
    }
    @Override
    public double getDamageAbsorptionRatio() {
        return 0.6D;
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
