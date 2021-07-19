package com.rumaruka.emt.item.events;

import com.rumaruka.emt.item.armor.boots.ItemElectricBootsTraveller;
import com.rumaruka.emt.item.armor.boots.ItemNanoBootsTraveller;
import com.rumaruka.emt.item.armor.boots.ItemQuantumBootsTraveller;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collections;

public class BootsFallEvent {
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {

        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();
            ItemStack stack = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if ((stack.getItem() instanceof ItemElectricBootsTraveller)) {
                if(ElectricItem.manager.use(stack,10,entity))
                event.setCanceled(true);

            }

        }
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();

            ItemStack stack = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if ((stack.getItem() instanceof ItemNanoBootsTraveller)) {
                if(ElectricItem.manager.use(stack,100,entity))
                    event.setCanceled(true);

            }
        }
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();

            ItemStack stack = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if ((stack.getItem() instanceof ItemQuantumBootsTraveller)) {
                if(ElectricItem.manager.use(stack,1000,entity))
                    event.setCanceled(true);

            }

        }
    }
}
