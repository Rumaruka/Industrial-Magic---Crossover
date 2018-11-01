package com.rumaruka.emt.item.tool;


import ic2.api.item.ElectricItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemThaumiumChainsaw extends ItemDiamondChainsaw {
    public ItemThaumiumChainsaw() {
        this.efficiency = 21F;
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        maxCharge = 100000;
        cost = 250;
        hitCost = 350;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if(ElectricItem.manager.use(stack,hitCost,attacker)){
            attacker.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
        }
        return false;
    }

    @Override
    public double getTransferLimit(ItemStack paramItemStack) {
        return 600;
    }
}
