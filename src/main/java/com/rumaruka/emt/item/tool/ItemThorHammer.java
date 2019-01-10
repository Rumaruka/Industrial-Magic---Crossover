package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemThorHammer extends ItemSword {
    public ItemThorHammer( ) {
        super(ToolMaterial.DIAMOND);
        setMaxDamage(2000);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        player.swingArm(handIn);
        ItemStack stack = player.getHeldItem(handIn);

        float f = 1.0F;
        float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
        float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
        double d = player.prevPosX + ((player.posX - player.prevPosX) * f);
        double d1 = (player.prevPosY + ((player.posY - player.prevPosY) * f) + 1.6200000000000001D) - player.posY;
        double d2 = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);

        Vec3d vec = Vec3d.ZERO.addVector(d, d1, d2);
        float f3 = MathHelper.cos((-f2 * 0.01745329F) - 3.141593F);
        float f4 = MathHelper.sin((-f2 * 0.01745329F) - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        double d3 = 5000D;
        Vec3d vec1 = Vec3d.ZERO.addVector(f7 * d3, f8 * d3, f9 * d3);

        RayTraceResult rayTraceResult = player.world.rayTraceBlocks(vec, vec1, true);
        if (rayTraceResult == null) {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            int i = (int) rayTraceResult.hitVec.x;
            int j = (int) rayTraceResult.hitVec.y;
            int k = (int) rayTraceResult.hitVec.z;
            EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, i, j, k, false);

            worldIn.spawnEntity(lightningBolt);
            worldIn.addWeatherEffect(lightningBolt);
        } else if (rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY) {
            Entity entityhit = rayTraceResult.entityHit;
            double x = entityhit.posX;
            double y = entityhit.posY;
            double z = entityhit.posZ;
            EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, x, y, z, true);
            worldIn.spawnEntity(lightningBolt);
            worldIn.addWeatherEffect(lightningBolt);
        }
        if (player.capabilities.isCreativeMode) {
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        } else {
            stack.damageItem(20, player);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        EntityLightningBolt lightningBolt = new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false);
        attacker.world.spawnEntity(lightningBolt);
        attacker.world.addWeatherEffect(lightningBolt);
        target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker),12f);
        stack.damageItem(1,attacker);
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.broken.The Hammer of Thor"));
    }
}
