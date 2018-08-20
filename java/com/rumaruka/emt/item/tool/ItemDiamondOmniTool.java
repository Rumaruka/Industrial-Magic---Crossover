package com.rumaruka.emt.item.tool;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDiamondOmniTool extends ItemIronOmniTool {

    public ItemDiamondOmniTool() {
        super();
        this.efficiency = 16F;

        this.setMaxStackSize(1);
        if (!EMTConfigHandler.toolsInBore) {
            this.setMaxDamage(27);
        }
        else {
            this.setMaxDamage(350);
        }
        maxCharge = 100000;
        hitCost = 250;
    }


    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (!EMTConfigHandler.toolsInBore) {
            cost = 200;
        }
        else {
            cost = 1;
        }
        ElectricItem.manager.use(stack, cost, entityLiving);
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase attacker) {
        if (ElectricItem.manager.use(itemstack, hitCost, attacker)) {
            entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10F);
        }
        return false;
    }

    /* IC2 API METHODS */
    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 400;
    }
}
