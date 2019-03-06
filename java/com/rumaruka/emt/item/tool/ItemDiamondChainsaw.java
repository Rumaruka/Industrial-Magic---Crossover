package com.rumaruka.emt.item.tool;

import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import ic2.core.IC2;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ItemDiamondChainsaw extends ItemAxe implements IElectricItem {
    public int maxCharge = 50000;
    public int cost = 200;
    public int hitCost = 300;
    public int tier = 2;

    public ItemDiamondChainsaw( ) {
        super(ToolMaterial.DIAMOND);
        efficiency = 16f;
        setMaxDamage(27);
        setMaxStackSize(1);
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
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        ElectricItem.manager.use(stack,cost,entityLiving);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.DIAMOND_AXE.canHarvestBlock(state, stack) || Items.DIAMOND_SWORD.canHarvestBlock(state, stack) || Items.SHEARS.canHarvestBlock(state, stack);

    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if(!ElectricItem.manager.canUse(stack,cost)){
            return 0.0f;
        }
        if(Items.WOODEN_AXE.getDestroySpeed(stack, state)>1.0f || Items.WOODEN_SWORD.getDestroySpeed(stack, state)>1.0f||Items.WOODEN_SWORD.getDestroySpeed(stack, state)>1.0f){
            return efficiency;
        }
        else {
            return super.getDestroySpeed(stack, state);
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(stack, hitCost, attacker)) {
            attacker.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
        }
        return false;
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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        for(int i = 0; i< player.inventory.mainInventory.size(); i++){
            ItemStack torchItem = player.inventory.mainInventory.get(i);
            if(torchItem == ItemStack.EMPTY || !Objects.requireNonNull(torchItem.getItem().getRegistryName()).getResourcePath().contains("torch")){
                continue;
            }
            Item item = torchItem.getItem();
            if(!(item instanceof ItemBook)){
                continue;
            }
            int oldMeta = torchItem.getItemDamage();

            if(player.capabilities.isCreativeMode){
                torchItem.setItemDamage(oldMeta);
            }
            else if(torchItem.getCount() <=0){
                ForgeEventFactory.onPlayerDestroyItem(player,torchItem,EnumHand.MAIN_HAND);
            }

            return EnumActionResult.SUCCESS;
        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if(!IC2.platform.isSimulating()){
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
        if (IC2.keyboard.isModeSwitchKeyDown(playerIn)) {
            NBTTagCompound nbt =  StackUtil.getOrCreateNbtData(itemstack);
            if (!nbt.hasKey("shearsMode")) {
                nbt.setBoolean("shearsMode", true);
            }

            if (!nbt.getBoolean("shearsMode")) {
                nbt.setBoolean("shearsMode", true);

                IC2.platform.messagePlayer(playerIn, "ic2.tooltip.mode", "ic2.tooltip.mode.normal");
            }
            else {
                nbt.setBoolean("shearsMode", false);
                IC2.platform.messagePlayer(playerIn, "ic2.tooltip.mode", "ic2.tooltip.mode.noShear");
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemstack);
        if(!nbt.hasKey("shearsMode")){
            nbt.setBoolean("shearsMode",true);

        }
        if(!nbt.getBoolean("shearsMode")||player.world.isRemote){
            return false;
        }
        IBlockState b =  player.world.getBlockState(pos);
        if(b instanceof IShearable){
            IShearable target = (IShearable) b;
            if(target.isShearable(itemstack,player.world,pos)){
                ArrayList<ItemStack>drops = (ArrayList<ItemStack>) target.onSheared(itemstack,player.world,pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,itemstack));
                Random r = new Random();

                for(ItemStack stack:drops){
                    float f = 0.7f;
                    double xOffset = (double) (r.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double yOffset = (double) (r.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    double zOffset = (double) (r.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(player.world,  pos.getX() + xOffset,  pos.getY() + yOffset, pos.getZ() + zOffset, stack);
                    entityitem.setPickupDelay(10);
                    player.world.spawnEntity(entityitem);
                }

                player.addStat(StatList.MINE_BLOCK_STATS.get(Block.getIdFromBlock((Block) b)),1);




            }
        }
        return false;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        if (!nbt.hasKey("shearsMode")) {
            nbt.setBoolean("shearsMode", true);
        }

        if (!nbt.getBoolean("shearsMode") || target.world.isRemote) {
            return false;
        }
        if (target instanceof IShearable) {
            IShearable t = (IShearable) target;
            if (t.isShearable(stack, target.world, new BlockPos((int) target.posX, (int) target.posY, (int) target.posZ))) {
                ArrayList<ItemStack> drops = (ArrayList<ItemStack>) t.onSheared(stack, target.world, new BlockPos((int) target.posX, (int) target.posY, (int) target.posZ), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));

                Random rand = new Random();
                for (ItemStack s : drops) {
                    EntityItem ent = target.entityDropItem(s, 1.0F);
                    ent.motionY += rand.nextFloat() * 0.05F;
                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(entityIn instanceof EntityLivingBase){
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            if(!nbt.hasKey("shearsMode")){
                nbt.setBoolean("shearsMode",true);
            }
        }
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if(EMTConfigHandler.enchanting==false){
            return 0;

        }
        else {
            return 4;
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EMTConfigHandler.enchanting;
    }

    private Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    private Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
    @Override
    public boolean canProvideEnergy(ItemStack paramItemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack paramItemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack paramItemStack) {
        return tier;
    }

    @Override
    public double getTransferLimit(ItemStack paramItemStack) {
        return 300;
    }
}
