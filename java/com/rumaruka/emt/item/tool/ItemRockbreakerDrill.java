package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.BlockUtils;

import thaumcraft.common.lib.utils.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


public class ItemRockbreakerDrill extends ItemThaumiumDrill {
    private static final Block[] isEffective = { Blocks.NETHER_BRICK, Blocks.NETHERRACK, Blocks.GLOWSTONE, Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_ORE, Blocks.EMERALD_ORE, Blocks.EMERALD_ORE, Blocks.STONEBRICK, Blocks.GLASS, Blocks.STONE, Blocks.GOLD_ORE, Blocks.IRON_ORE, Blocks.COAL_ORE, Blocks.COBBLESTONE,
            Blocks.DIAMOND_ORE, Blocks.LAPIS_ORE, Blocks.DIRT, Blocks.GRAVEL, Blocks.SAND, Blocks.SANDSTONE, Blocks.SOUL_SAND, Blocks.CLAY, Blocks.GRASS, Blocks.SNOW_LAYER, Blocks.SNOW, Blocks.FARMLAND, Blocks.HARDENED_CLAY, Blocks.STAINED_HARDENED_CLAY, Blocks.MOSSY_COBBLESTONE };
    EnumFacing side;
    public int searchCost = 1000;
    public int hitCost = 400;

    public ItemRockbreakerDrill() {
        side = EnumFacing.DOWN;
        this.efficiency = 25F;
        this.setMaxStackSize(1);
        if (EMTConfigHandler.toolsInBore == false) {
            this.setMaxDamage(27);
        }
        else {
            this.setMaxDamage(2571);
        }
        maxCharge = 1000000;
        transferLimit = 1000;
        tier = 3;
    }
    private boolean isEffectiveAgainst(Block block) {
        for (int var3 = 0; var3 < isEffective.length; var3++) {
            if (isEffective[var3] == block) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        RayTraceResult rayTraceResult = BlockUtils.getTargetBlock(player.world,player,true);
       if(rayTraceResult!=null&&rayTraceResult.typeOfHit== RayTraceResult.Type.BLOCK){
           side = rayTraceResult.sideHit;
       }

        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {

        if (EMTConfigHandler.toolsInBore == false) {
            cost = 350;
        }
        else {
            cost = 1;
        }
        if (entityLiving.isSneaking()) {
            if (ElectricItem.manager.canUse(stack, cost)) {
                ElectricItem.manager.use(stack, cost, entityLiving);
            }
            return true;
        }
        Block b = state.getBlock();

        if (ForgeHooks.isToolEffective(worldIn, pos, stack) || isEffectiveAgainst(b)) {
            for (int aa = -1; aa <= 1; aa++) {
                for (int bb = -1; bb <= 1; bb++) {
                    int xx = 1;
                    int yy = 1;
                    int zz = 1;
                    if (side == EnumFacing.UP) {
                        xx = aa;
                        zz = bb;
                    }
                    else if (side == EnumFacing.SOUTH) {
                        xx = aa;
                        yy = bb;
                    }
                    else {
                        zz = aa;
                        yy = bb;
                    }
                    Block b1 = state.getBlock();
                    if (!ForgeHooks.isToolEffective(worldIn, pos, stack) && !isEffectiveAgainst(b1)) {
                        continue;
                    }
                    if (ElectricItem.manager.canUse(stack, cost)) {
                        ElectricItem.manager.use(stack, cost, entityLiving);
                    }
                    worldIn.setBlockToAir(new BlockPos(pos.getX() + xx, pos.getY() + yy, pos.getZ() + zz));

                }
            }
        }
        return true;
    }



    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        if (!player.isSneaking()) {
            for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
                ItemStack torchItem = player.inventory.mainInventory.get(i);
                if (torchItem == ItemStack.EMPTY || !Objects.requireNonNull(torchItem.getItem().getRegistryName()).getResourcePath().contains("torch")) {
                    continue;
                }

                Item item = torchItem.getItem();
                if(!(item instanceof ItemBlock)){
                    continue;
                }
                int oldMeta = torchItem.getItemDamage();
                if (player.capabilities.isCreativeMode) {
                    torchItem.setItemDamage(oldMeta);
                }


            }
        }
      else{
          ElectricItem.manager.use(itemStack,searchCost,player);

              worldIn.playSound(pos.getX()+0.5d,pos.getY()+0.5d,pos.getZ()+0.5d, SoundsTC.wandfail, SoundCategory.MASTER,0.2f,0.2f+worldIn.rand.nextFloat()*0.2f,true);

            return EnumActionResult.SUCCESS;
         }
      return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!((Entity) (player)).world.isRemote && (!(entity instanceof EntityPlayer))) {
            entity.setFire(2);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(stack, hitCost, attacker)) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
        }
        return false;
    }
}
