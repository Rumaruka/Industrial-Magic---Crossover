package com.rumaruka.emt.item.events;

import com.rumaruka.emt.item.armor.boots.ItemElectricBootsTraveller;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class BootsJumpEvent {

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();


            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (stack.getItem() instanceof ItemElectricBootsTraveller)
                if(ElectricItem.manager.use(stack,100,player)){

                    player.motionY += 0.003f;

                }

        }
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (stack.getItem() instanceof ItemElectricBootsTraveller)
                if(ElectricItem.manager.use(stack,1000,player)){
                    player.motionY += 0.3333354f;

                }

        }
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();


            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (stack.getItem() instanceof ItemElectricBootsTraveller)
                if(ElectricItem.manager.use(stack,10000,player)){
                    player.motionY += 0.5f;

                }
        }
    }


}
