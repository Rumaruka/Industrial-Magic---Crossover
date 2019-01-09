package com.rumaruka.emt.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Random;

public class ItemOneRing extends ItemBase implements IBauble {
    public Random random = new Random();

    public ItemOneRing( ) {
        super("bauble");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.isInvisible()) {
            player.setInvisible(true);
        }
        NBTTagCompound tag = new NBTTagCompound();
        ((EntityPlayer) player).writeToNBT(tag);
        NBTTagCompound forgeTag = tag.getCompoundTag("ForgeData");

        int corruption = 0;

        if (forgeTag.hasKey("MindCorruption"))
            corruption = forgeTag.getInteger("MindCorruption");
        else
            forgeTag.setInteger("MindCorruption", 0);

        //((EntityPlayer) player).capabilities.disableDamage = true;

        if (!player.world.isRemote) {
            if (corruption <= 0) {
                ((EntityPlayer) player).sendMessage(new TextComponentTranslation(EMTTextHelper.PURPLE + "You have worn the Ring. Your soul has now been forever " + EMTTextHelper.PURPLE + "tainted. " + EMTTextHelper.RED + EMTTextHelper.ITALIC + "Beware of wearing the ring. The tainting will only " + EMTTextHelper.RED + EMTTextHelper.ITALIC + "increase, and strange things will start happening.") {
                });
            }
            else if (corruption > 6000 && corruption < 24000 && random.nextInt(2000) == 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 500, 2, false,false));
            }
            else if (corruption >= 6000 && corruption < 24000 && random.nextInt(2000) == 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 500, 2, false,false));
            }
            else if (corruption >= 24000 && corruption < 72000 && random.nextInt(2000) == 0) {
                ((EntityPlayer) player).capabilities.disableDamage = false;

                player.addPotionEffect(new PotionEffect(MobEffects.POISON, 500, 2, false,false));
            }
            else if (corruption >= 72000 && corruption < 120000 && random.nextInt(4000) == 0) {
                ((EntityPlayer) player).capabilities.disableDamage = false;

                player.motionY += 2d;
            }
            else if (corruption >= 120000 && random.nextInt(10000) == 0) {
                ((EntityPlayer) player).capabilities.disableDamage = false;

                player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 5000, 4, false,false));
            }
            //else if (corruption + 100 >= Integer.MAX_VALUE) { // =3333333
            //	player.isDead = true;
            //}
        }
        forgeTag.setInteger("MindCorruption", ++corruption);
        tag.setTag("ForgeData", forgeTag);
        ((EntityPlayer) player).readFromNBT(tag);
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        NBTTagCompound tag = new NBTTagCompound();
        ((EntityPlayer) player).writeToNBT(tag);
        tag.getCompoundTag("ForgeData").setInteger("MindCorruption", 0);
        ((EntityPlayer) player).readFromNBT(tag);

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.setInvisible(false);
        if (!((EntityPlayer) player).capabilities.isCreativeMode)
            ((EntityPlayer) player).capabilities.disableDamage = false;
        NBTTagCompound tag = new NBTTagCompound();
        ((EntityPlayer) player).writeToNBT(tag);
        tag.removeTag("ForgeData");
        ((EntityPlayer) player).readFromNBT(tag);
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 500, 2, false,false));
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return itemstack.getItemDamage() == 0 && player instanceof EntityPlayer;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        NBTTagCompound tag = new NBTTagCompound();
        ((EntityPlayer) player).writeToNBT(tag);
        return tag.getCompoundTag("ForgeData").getInteger("MindCorruption") > 600 ? true : false;
    }


}
