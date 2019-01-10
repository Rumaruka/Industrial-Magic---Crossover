package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.EMT;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemNanoGoggles extends ItemElectricGoggles{

    public ItemNanoGoggles(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        visDiscount = 6;
        tier = 3;
        maxCharge = 1000000;
        energyPerDamage = 5000;
        transferLimit = 1600;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return EMT.MOD_ID+ ":textures/models/armor/thaumicnanohelmet.png";
    }
    @Override
    public double getDamageAbsorptionRatio() {
        return 0.9D;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 3;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (EMTConfigHandler.nightVisionOff == false) {
            if (ElectricItem.manager.canUse(itemStack, 1 / 1000)) {

                int x = MathHelper.floor(player.posX);
                int z = MathHelper.floor(player.posZ);
                int y = MathHelper.floor(player.posY);

                int lightlevel = player.world.getBlockLightOpacity(new BlockPos(x, y, z));
                if (lightlevel >= 0)
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, -3,true,false));
                ElectricItem.manager.use(itemStack, 1 / 1000, player);
            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 300, 0, true,false));
            }
        }
    }
    }

