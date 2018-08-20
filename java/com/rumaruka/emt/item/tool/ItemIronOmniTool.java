package com.rumaruka.emt.item.tool;
import com.rumaruka.emt.client.creativetabs.EMTCreativeTabs;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;


public class ItemIronOmniTool extends ItemPickaxe implements IElectricItem {

   public int maxCharge = 10000;
   public int cost;

    public int transferLimit=200;
    public int hitCost = 125;
    public ItemIronOmniTool( ) {
        super(ToolMaterial.DIAMOND);
        this.efficiency =13f;
        this.setMaxStackSize(1);
        if(!EMTConfigHandler.toolsInBore){
            this.setMaxDamage(27);
        }
        else {
            this.setMaxDamage(200);
        }

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
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
       if(!EMTConfigHandler.toolsInBore){
           cost=100;
       }
       else {
           cost=1;
       }
       ElectricItem.manager.use(stack,cost,entityLiving);
       return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.IRON_AXE.canHarvestBlock(state, stack) || Items.IRON_SWORD.canHarvestBlock(state, stack) || Items.IRON_PICKAXE.canHarvestBlock(state, stack) || Items.IRON_SHOVEL.canHarvestBlock(state, stack) || Items.SHEARS.canHarvestBlock(state, stack);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {


        if (!ElectricItem.manager.canUse(stack, cost)) {
            return 0.0F;
        }

        if (Items.WOODEN_AXE.getDestroySpeed(stack, state ) > 1.0F || Items.WOODEN_SWORD.getDestroySpeed(stack, state ) > 1.0F || Items.WOODEN_PICKAXE.getDestroySpeed(stack, state) > 1.0F || Items.WOODEN_SHOVEL.getDestroySpeed(stack, state) > 1.0F || Items.SHEARS.getDestroySpeed(stack, state ) > 1.0F) {
            return efficiency;
        }
        else {
            return super.getDestroySpeed(stack, state );
        }
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand)
    {


            if (entity.world.isRemote) {
                return false;
            }
            if (entity instanceof net.minecraftforge.common.IShearable) {
                if(!EMTConfigHandler.toolsInBore){
                    cost=100;
                }
                else {
                    cost=1;
                }
                ElectricItem.manager.use(itemstack,cost,entity);

                net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable) entity;
                BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
                if (target.isShearable(itemstack, entity.world, pos)) {
                    java.util.List<ItemStack> drops = target.onSheared(itemstack, entity.world, pos,
                            net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, itemstack));

                    java.util.Random rand = new java.util.Random();
                    for (ItemStack stack : drops) {
                        net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(stack, 1.0F);
                        ent.motionY += rand.nextFloat() * 0.05F;
                        ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                        ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                    }
                    itemstack.damageItem(1, entity);
                }
                return true;
            }

        return false;

    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!ElectricItem.manager.use(stack, hitCost,attacker)) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker),8.0f);
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        for(int i = 0; i< player.inventory.mainInventory.size(); i++){
            ItemStack torchItem = player.inventory.mainInventory.get(i);
            if(torchItem == ItemStack.EMPTY || !torchItem.getItem().getRegistryName().getResourcePath().contains("torch")){
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
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return EMTConfigHandler.enchanting;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        if(!EMTConfigHandler.enchanting){
            return 0;
        }
        return 4;
    }

    public Item getChargedItem(ItemStack itemStack){
        return this;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 2;
    }
    private Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }
}
