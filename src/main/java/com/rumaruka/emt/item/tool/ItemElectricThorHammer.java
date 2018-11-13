package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.entity.EntityArcher;
import com.rumaruka.emt.util.EMTConfigHandler;
import com.rumaruka.emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemElectricThorHammer extends ItemSword implements IElectricItem {
    public int maxCharge = 2000000;
    private final int hitCost = 5000;
    private final int lightningCost = 75000;


    public ItemElectricThorHammer() {
        super(ToolMaterial.DIAMOND);

        this.setMaxDamage(27);
        this.setMaxStackSize(1);
    }
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab==EMTCreativeTabs.EMT_CREATIVEtabs){
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
    /*
    public void getSubItems(EMTCreativeTabs tab, NonNullList<ItemStack> items) {

        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            items.add(charged);
        }
        if (getEmptyItem(itemStack) == this) {

            items.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }*/

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(stack, hitCost, attacker)) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 19F);
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add(EMTTextHelper.localize("tooltip.EMT.hammer.electric"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        player.swingArm(handIn);
        ItemStack stack = player.getHeldItem(handIn);
        if (ElectricItem.manager.canUse(stack, lightningCost)) {

            // Corners
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 8,false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 8,false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 8,false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 8,false));

            // Fronts
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX, player.posY, player.posZ - 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX, player.posY, player.posZ + 8, false));

            // Others
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 1, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 2, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ + 3, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ + 4, false));

            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 1, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 2, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 8, player.posY, player.posZ - 3, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 8, player.posY, player.posZ - 4, false));

            world.spawnEntity(new EntityLightningBolt(world, player.posX + 1, player.posY, player.posZ + 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 2, player.posY, player.posZ - 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 3, player.posY, player.posZ + 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX + 4, player.posY, player.posZ - 8, false));

            world.spawnEntity(new EntityLightningBolt(world, player.posX - 1, player.posY, player.posZ + 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 2, player.posY, player.posZ - 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 3, player.posY, player.posZ + 8, false));
            world.spawnEntity(new EntityLightningBolt(world, player.posX - 4, player.posY, player.posZ - 8, false));

            EntityArcher archer;
            archer = new EntityArcher(world);
            archer.setPosition(player.posX + 8, player.posY + 2, player.posZ - 8);
            world.spawnEntity(archer);

            EntityArcher archer1;
            archer1 = new EntityArcher(world);
            archer1.setPosition(player.posX - 8, player.posY + 2, player.posZ + 8);
            world.spawnEntity(archer1);

            EntityArcher archer2;
            archer2 = new EntityArcher(world);
            archer2.setPosition(player.posX - 8, player.posY + 2, player.posZ - 8);
            world.spawnEntity(archer2);

            EntityArcher archer3;
            archer3 = new EntityArcher(world);
            archer3.setPosition(player.posX + 8, player.posY + 2, player.posZ + 8);
            world.spawnEntity(archer3);


            ElectricItem.manager.use(stack, lightningCost, player);



        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if (EMTConfigHandler.enchanting == false) {
            return 0;
        } else {
            return 4;
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return EMTConfigHandler.enchanting;
    }
    private Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    private Item getChargedItem(ItemStack itemStack) {
        return this;
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
        return 4;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 100;
    }
}
