package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.EMT;
import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IMetalArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.common.lib.events.PlayerEvents;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemElectricBootsTraveller extends ItemArmor implements IElectricItem, IVisDiscountGear, IMetalArmor, ISpecialArmor , IRechargable {
    public int maxCharge = 100000;
    public int energyPerDamage = 1000;
    public int visDiscount = 2;
    public float jumpBonus = 0.3F;
    public double transferLimit = 100;
    public ItemElectricBootsTraveller(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);

    }
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab== EMTCreativeTabs.EMT_CREATIVEtabs){
            ItemStack itemStack = new ItemStack(this, 1);
            if (getChargedItem(itemStack) == this) {
                ItemStack charged = new ItemStack(this, 1);
                ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
                items.add(charged);
            }
            if (getEmptyItem(itemStack) == this) {
                items.add(new ItemStack(this, 1, getMaxDamage()));
            }
        }
    }
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
       if(source.isUnblockable()){
           return new ArmorProperties(0, 0.0D, 0);
       }
       else {
           double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
           int energyPerDamage = getEnergyPerDamage();
           double damageLimit = energyPerDamage <= 0 ? 0 : (25 * ElectricItem.manager.getCharge(armor)) / energyPerDamage;
           return new ArmorProperties(0, absorptionRatio, (int) damageLimit);
       }
    }


    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage()) {
            return (int) Math.round(20D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
        }
        else {
            return 0;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), 0x7fffffff, true, false, false);
    }
    public double getDamageAbsorptionRatio() {
        return 0.5D;
    }

    private double getBaseAbsorptionRatio() {
        return 0.15D;
    }



    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return visDiscount;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        boolean hasCharge = RechargeHelper.getCharge(itemStack)>0;

            if ((player.world.isRemote) && player.ticksExisted %20==0 ) {
                int e = 0;
                if(itemStack.hasTagCompound()){
                    e = itemStack.getTagCompound().getInteger("energy");

                }
                if(e>0){
                    --e;
                }else if(e<=0&&RechargeHelper.consumeCharge(itemStack,player,1)){
                    e=60;
                }
                itemStack.setTagInfo("energy",new NBTTagInt(e));
            }
        if (hasCharge&&(!player.capabilities.isFlying) && (player.moveForward > 0.0F)&&ElectricItem.manager.use(itemStack,50,player)) {
            if ((player.world.isRemote) && (!player.isSneaking())) {
                    if(!PlayerEvents.prevStep.containsKey(player.getEntityId())){
                        PlayerEvents.prevStep.put(player.getEntityId(),player.stepHeight);
                    }
                    player.stepHeight=2.0f;
            }
            if(player.onGround){
                float bonus = 0.05f;
                if(player.isInWater()){
                    bonus /= 4.0f;
                }
                player.moveRelative(0.0F, 0.0F, bonus, 4.0F);
            }else {
                if(player.isInWater()){
                    player.moveRelative(0.0F, 0.0F, 0.025f, 1.0F);

                }
                player.jumpMovementFactor = 1.5F;
            }

        }
    }
    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            boolean hasArmor = player.getEquipmentAndArmor() != null;

            if (hasArmor)
                player.motionY += jumpBonus;
        }
    }
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();
            if ((entity.inventory.armorInventory.get(0) != null) && (entity.inventory.armorInventory.get(0).getItem() instanceof ItemElectricBootsTraveller)) {
                ItemStack stack = entity.inventory.armorInventory.get(0);
                if (ElectricItem.manager.use(stack, energyPerDamage, entity))
                    event.setCanceled(true);
            }
        }
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return EMT.TEXTURE_PATH + ":textures/models/armor/electricboots.png";
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }
    @Override
    public int getItemEnchantability() {
        if (EMTConfigHandler.enchanting == false) {
            return 0;
        }
        else {
            return 4;
        }
    }
    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        if (EMTConfigHandler.enchanting == false) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getEnergyPerDamage() {
        return energyPerDamage;
    }
    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 2;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }

    @Override
    public boolean isMetalArmor(ItemStack itemStack, EntityPlayer entityPlayer) {
        return true;
    }


    @Override
    public int getMaxCharge(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return 240;
    }

    @Override
    public EnumChargeDisplay showInHud(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return EnumChargeDisplay.PERIODIC;
    }
}
