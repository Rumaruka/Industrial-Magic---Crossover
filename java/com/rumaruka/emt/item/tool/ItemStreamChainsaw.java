package com.rumaruka.emt.item.tool;

import ic2.api.item.ElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

import java.util.ArrayList;

public class ItemStreamChainsaw extends ItemThaumiumChainsaw {
    boolean alternateServer;
    boolean alternateClient;
    public static ArrayList oreDictLogs = new ArrayList();

    public ItemStreamChainsaw() {
        this.efficiency = 25F;
        alternateServer = false;
        alternateClient = false;
        this.maxStackSize = 1;
        this.setMaxDamage(27);
        maxCharge = 1000000;
        cost = 400;
        hitCost = 500;
        tier = 3;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        IBlockState bi = worldIn.getBlockState(pos);
        if ((!player.isSneaking()) && (Utils.isWoodLog(worldIn, pos)) && ElectricItem.manager.getCharge(itemstack) > cost) {
            if (!worldIn.isRemote) {
                if (BlockUtils.breakFurthestBlock(worldIn, pos, bi, player)) {
                    ElectricItem.manager.use(itemstack, cost, player);
                    worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundsTC.bubble, SoundCategory.BLOCKS, 0.15f, 1.0f, false);
                    this.alternateServer = (!this.alternateServer);
                }
            } else {
                player.swingArm(EnumHand.MAIN_HAND);
                ElectricItem.manager.use(itemstack, cost, player);
                this.alternateClient = (!this.alternateClient);
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        World world = player.world;
        IBlockState bi = world.getBlockState(pos);
        if ((!player.isSneaking()) && (Utils.isWoodLog(world, pos))) {
            if (!world.isRemote) {
                BlockUtils.breakFurthestBlock(world, pos, bi, player);

                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundsTC.bubble, SoundCategory.BLOCKS, 0.15f, 1.0f, false);
            }
            ElectricItem.manager.use(itemstack, cost, player);
            return true;
        }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public double getTransferLimit(ItemStack paramItemStack) {
        return 900;
    }
}

