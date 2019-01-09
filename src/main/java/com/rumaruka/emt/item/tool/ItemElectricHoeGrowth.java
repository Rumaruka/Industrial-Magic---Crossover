package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.utils.Utils;

public class ItemElectricHoeGrowth extends ItemHoe implements IElectricItem {

    public int maxCharge = 200000;
    public int growthCost;
    public int tillCost ;
    public ItemElectricHoeGrowth( ) {
        super(ToolMaterial.DIAMOND);
        this.setMaxStackSize(1);
        growthCost = 10000;
        tillCost = 1000;

    }
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        ElectricItem.manager.use(stack, this.tillCost, player);
        return false;
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

    }
*/


    @Override
    public boolean isRepairable() {
        return false;
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack stack = player.getHeldItem(hand);
        if (player.isUser()) {
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        } else {
            boolean did = false;

            for(int xx = -1; xx <= 1; ++xx) {
                for(int zz = -1; zz <= 1; ++zz) {
                    if (ElectricItem.manager.canUse(stack,tillCost)&&super.onItemUse(player, worldIn, pos.add(xx, 0, zz), hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS) {
                        if (!worldIn.isRemote) {
                            ElectricItem.manager.use(stack,tillCost,player);
                            BlockPos pp = pos.add(xx, 0, zz);
                            FXDispatcher.INSTANCE.drawBamf((double)pp.getX() + 0.5D, (double)pp.getY() + 1.01D, (double)pp.getZ() + 0.5D, 0.3F, 0.12F, 0.1F,  xx == 0 && zz ==0, false, EnumFacing.UP);

                        }

                        if (!did) {
                            did = true;
                        }
                    }
                }
            }

            if (!did&&ElectricItem.manager.canUse(stack,growthCost)) {
                did = Utils.useBonemealAtLoc(worldIn, player, pos);

                if (did&&ElectricItem.manager.canUse(stack,growthCost)) {

                    player.getHeldItem(hand).damageItem(3, player);
                    if (!worldIn.isRemote) {
                        ElectricItem.manager.use(stack,growthCost,player);
                        FXDispatcher.INSTANCE.drawBlockMistParticles(pos, 990099);

                    } else
                        {
                        ElectricItem.manager.use(stack,growthCost,player);
                        FXDispatcher.INSTANCE.drawBlockMistParticles(pos, 990099);
                        worldIn.playBroadcastSound(2005, pos, 0);

                    }


                }

            }

            return EnumActionResult.SUCCESS;
        }
    }





    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
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
        return 1000;
    }
}
