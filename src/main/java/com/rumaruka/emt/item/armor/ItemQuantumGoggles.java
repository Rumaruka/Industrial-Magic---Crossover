package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.EMT;
import ic2.api.item.IC2Items;
import ic2.core.IC2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemQuantumGoggles extends ItemNanoGoggles {

    private static final Map<Integer, Integer> potionCost = new HashMap();
    public ItemQuantumGoggles(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        maxCharge = 10000000;
        tier = 4;
        visDiscount = 8;
        transferLimit = 12000;
        energyPerDamage = 20000;

        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.POISON)), Integer.valueOf(10000));
        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.WITHER)), Integer.valueOf(15000));
        potionCost.put(Integer.valueOf(Potion.getIdFromPotion(MobEffects.NAUSEA)), Integer.valueOf(5000));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return EMT.TEXTURE_PATH + ":textures/models/armor/thaumicquantumhelmet.png";
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
        try {
            Field f;
            f = Keyboard.class.getDeclaredField("playerKeys");
            f.setAccessible(true);
            Map<EntityPlayer, Set<Enum>> playerKeys = (Map<EntityPlayer, Set<Enum>>) f.get(IC2.keyboard);

            Enum hub = null;
            Set<Enum> set = playerKeys.get(player);

            if (set != null) {
                for (Enum e : set) {
                    if (e.ordinal() == 6) {
                        hub = e;
                    }
                }
                set.remove(hub);
            }

            IC2Items.getItem("quantumHelmet").getItem().onArmorTick(world, player, itemStack);

            if (hub != null) {
                set.add(hub);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
     }
}

