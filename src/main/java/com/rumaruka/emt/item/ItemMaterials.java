package com.rumaruka.emt.item;

import com.rumaruka.emt.init.EMTItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.InventoryUtils;

import thaumcraft.common.config.ModConfig;
public class ItemMaterials extends Item {

    public ItemMaterials() {

        this.setMaxDamage(0);
        this.setMaxStackSize(64);

        setHasSubtypes(true);
    }


    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        if (stack == ItemStack.EMPTY && stack.getItem() == EMTItems.materials_lightningsummoner && this.getMaxItemUseDuration(stack) - count >= 40) {
            World w = player.world;
            player.swingArm(EnumHand.MAIN_HAND);
            float f = 1.0F;
            float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
            float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
            double playerX = player.prevPosX + ((player.posX - player.prevPosX) * f);
            double playerY = (player.prevPosY + ((player.posY - player.prevPosY) * f) + 1.6200000000000001D) - player.posY;
            double playerZ = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);
            Vec3d playerLoc = Vec3d.ZERO.addVector(playerX, playerY, playerZ);
            float f3 = MathHelper.cos((-f2 * 0.01745329F) - 3.141593F);
            float f4 = MathHelper.sin((-f2 * 0.01745329F) - 3.141593F);
            float f5 = -MathHelper.cos(-f1 * 0.01745329F);
            float f6 = MathHelper.sin(-f1 * 0.01745329F);
            float f7 = f4 * f5;
            float f8 = f6;
            float f9 = f3 * f5;
            float d3 = 5000;
            Vec3d vec3d = playerLoc.addVector(f7 * d3, f8 * d3, f9 * d3);
            RayTraceResult rayTraceResult = player.world.rayTraceBlocks(playerLoc, vec3d, true);
            if (rayTraceResult != null) {
                if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    int x2 = (int) rayTraceResult.hitVec.x;
                    int y2 = (int) rayTraceResult.hitVec.y;
                    int z2 = (int) rayTraceResult.hitVec.z;
                    w.spawnEntity(new EntityLightningBolt(w, x2, y2, z2, true));
                    w.addWeatherEffect(new EntityLightningBolt(w, x2, y2, z2, true));
                }else if(rayTraceResult.typeOfHit== RayTraceResult.Type.ENTITY){
                    int x = (int) rayTraceResult.hitVec.x;
                    int y = (int) rayTraceResult.hitVec.y;
                    int z = (int) rayTraceResult.hitVec.z;
                    w.spawnEntity(new EntityLightningBolt(w, x, y, z, true));
                    w.addWeatherEffect(new EntityLightningBolt(w, x, y, z, true));
                }

            }
            player.stopActiveHand();
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(stack.getItem()==EMTItems.materials_lightningsummoner){
            ArrowNockEvent event = new ArrowNockEvent(playerIn,stack,handIn,worldIn,false);
            MinecraftForge.EVENT_BUS.post(event);
            if(event.isCanceled()){
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,stack);
            }
            playerIn.setActiveHand(EnumHand.MAIN_HAND);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,stack);
    }


    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        if(stack.getItem()==EMTItems.materials_lightningsummoner){
            return EnumAction.BOW;
        }
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        if(stack.getItem()==EMTItems.materials_lightningsummoner){
            return 7200;
        }
        return super.getMaxItemUseDuration(stack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
        if ((!entity.world.isRemote) && ((stack.getItem()==EMTItems.materials_taintedfeather)) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase) entity).isEntityUndead()) && (!((EntityLivingBase) entity).isPotionActive(PotionFluxTaint.instance)) && (worldIn.rand.nextInt(4321) <= stack.getMaxStackSize())) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionFluxTaint.instance, 120, 0, false,false));
            if ((entity instanceof EntityPlayer)) {
                InventoryUtils.consumePlayerItem((EntityPlayer) entity, stack.getItem(), stack.getItemDamage());
            }
        }
    }
}

