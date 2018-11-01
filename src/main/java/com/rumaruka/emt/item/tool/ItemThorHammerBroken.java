package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import thaumcraft.common.entities.projectile.EntityAlumentum;

import javax.annotation.Nullable;
import java.util.List;

public class ItemThorHammerBroken extends ItemSword {
    public ItemThorHammerBroken( ) {
        super(ToolMaterial.DIAMOND);
        setMaxDamage(1000);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.swingArm(handIn);
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX + 8, playerIn.posY, playerIn.posZ - 8));
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX - 8, playerIn.posY, playerIn.posZ + 8));
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX - 8, playerIn.posY, playerIn.posZ - 8));
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX + 8, playerIn.posY, playerIn.posZ + 8));
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX, playerIn.posY + 4, playerIn.posZ));
        worldIn.spawnEntity(new EntityAlumentum(worldIn, playerIn.posX, playerIn.posY + 8, playerIn.posZ));

        if (playerIn.capabilities.isCreativeMode) {
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
        } else {
            itemStack.damageItem(20, playerIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
        }
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 2F);
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.broken.thor"));
        tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.broken.danger"));
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.broken.plsNoRightClick"));
        }
        else {
            tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.broken.plsRightClick"));
        }
    }
}
