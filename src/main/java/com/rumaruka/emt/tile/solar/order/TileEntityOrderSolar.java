package com.rumaruka.emt.tile.solar.order;

import com.rumaruka.emt.init.EMTBlocks;
import com.rumaruka.emt.tile.solar.TileEntitySolarBase;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class TileEntityOrderSolar extends TileEntitySolarBase {

    public TileEntityOrderSolar(){
        super();
        energy=new BasicSource(this,10000,3);
        output= EMTConfigHandler.compressedSolarOutput;
    }

    @Override
    public void createEnergy() {
        if(isSunVisible){
            energy.addEnergy((output)*3);
        }
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return Arrays.asList(new ItemStack[]{new ItemStack(EMTBlocks.solar_ordercompressed)});
    }
}
