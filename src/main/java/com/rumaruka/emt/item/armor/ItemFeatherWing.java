package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.client.model.ModelWings;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.util.StackUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IVisDiscountGear;

import javax.annotation.Nullable;

public class  ItemFeatherWing extends ItemArmor implements IVisDiscountGear {




    public ItemFeatherWing(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setMaxDamage(120);
        setMaxStackSize(1);
        isDamageable();
    }

    public float getFallDamageMult() {
        return 0.75F;
    }

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        try {
            if (entityLiving instanceof EntityPlayer) {
                ModelWings mw = new ModelWings();
                mw.isJumping = itemStack.getTagCompound().getBoolean("isJumping");
                return mw;
            }
        } catch (NullPointerException e) {
            return new ModelWings();
        }
        return new ModelWings();
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        useWings(player, stack, world, 0.11f, 0.9f, 0.9f, 0);
    }
    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 0;
    }
    public void useWings(EntityPlayer player, ItemStack stack, World world, float motionY, float motionXZ, float f1, int amount) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);

        boolean isJmuping = nbtData.getBoolean("isJumping");
        boolean isHolding = nbtData.getBoolean("isHolding");

        nbtData.setBoolean("isJumping", IC2.keyboard.isJumpKeyDown(player));

        if (isJmuping) {
            byte f = nbtData.getByte("f");
            nbtData.setBoolean("isHolding", true);
            nbtData.setByte("f", (byte) (f + 1));
            if (f > 7)
                nbtData.setByte("f", (byte) 7);
        }
        else if (isHolding) {
            byte f = nbtData.getByte("f");
            nbtData.setBoolean("isHolding", false);
            player.motionY = motionY * f;
            ElectricItem.manager.use(stack, ((motionY * f) * 10) * amount, player);
            if (player.motionX < 0.5 && player.motionZ < 0.5 && player.motionX > -0.5 && player.motionZ > -0.5) {
                player.motionX /= motionXZ;
                player.motionZ /= motionXZ;
            }
            world.playSound(player.posX,player.posY,player.posZ,SoundEvents.ENTITY_ENDERDRAGON_FIREBALL_EPLD,SoundCategory.AMBIENT,1,1,false);
            for (int i = 0; i < 4; i++) {
                world.spawnParticle(EnumParticleTypes.CLOUD, player.posX - 1 + (world.rand.nextInt(100) / 50d), player.posY - 1, player.posZ - 1 + (world.rand.nextInt(100) / 50d), 0, -0.5, 0);
            }
            nbtData.setByte("f", (byte) 0);
        }

        if (isJmuping && !player.onGround && player.motionY < 0) {
            player.motionY *= f1;
        }

        if (player.isInWater() && !player.capabilities.isCreativeMode) {
            player.motionY += -0.03;
        }


        if (player.isSneaking() && !player.onGround && player.motionY < 0) {
            player.motionY *= 0.6;
        }
    }
}
