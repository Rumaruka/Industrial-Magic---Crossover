package com.rumaruka.emt.item.armor.googles;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IC2Items;
import ic2.core.IC2;
import ic2.core.item.ElectricItemManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

public class ItemQuantumGoggles extends ItemNanoGoggles {

    private static final HashMap<Integer, Integer> potionCost = new HashMap<>();
    public ItemQuantumGoggles(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        maxCharge = 10000000;
        tier = 4;
        visDiscount = 8;
        transferLimit = 12000;
        energyPerDamage = 20000;

        potionCost.put(Potion.getIdFromPotion(MobEffects.POISON), 1000);
        potionCost.put(Potion.getIdFromPotion(MobEffects.WITHER), 15000);
        potionCost.put(Potion.getIdFromPotion(MobEffects.NAUSEA), 5000);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return emt.TEXTURE_PATH + ":textures/models/armor/thaumicquantumhelmet.png";
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
       int ref = player.getAir();
       if(ElectricItem.manager.canUse(itemStack,1000)&&ref<100){
           player.setAir(ref+200);
           ElectricItem.manager.use(itemStack,1000,null);
       }
        Iterator i$ = (new LinkedList(player.getActivePotionEffects())).iterator();
        do {
            if (!i$.hasNext()) {
                break;
            }
            {
                PotionEffect effect = (PotionEffect) i$.next();
                Potion id = effect.getPotion();
                Integer cost = (Integer) potionCost.get(id);
                if (cost != null) {
                    cost = Integer.valueOf(cost.intValue() * (effect.getAmplifier() + 1));
                    if (ElectricItem.manager.canUse(itemStack, cost.intValue())) {
                        ElectricItem.manager.use(itemStack, cost.intValue(), null);
                        ItemStack milk = (new ItemStack(Items.MILK_BUCKET));
                        player.curePotionEffects(milk);
                    }
                }
            }
        } while (true);

        if (!EMTConfigHandler.nightVisionOff) {
            if (ElectricItem.manager.canUse(itemStack, 1 / 1000)) {
                int x = MathHelper.floor(player.posX);
                int z = MathHelper.floor(player.posZ);
                int y = MathHelper.floor(player.posY);
                int lightlevel = player.world.getLight(new BlockPos(x, y, z));
                if (lightlevel >= 0)
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, -3));
                ElectricItem.manager.use(itemStack, 1 / 1000, player);
            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 300, 0,true,false));
            }
        }
    }
}

