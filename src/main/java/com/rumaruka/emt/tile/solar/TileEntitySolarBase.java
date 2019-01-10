package com.rumaruka.emt.tile.solar;

import com.rumaruka.emt.tile.TileEntityBase;
import ic2.api.energy.prefab.BasicSource;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class TileEntitySolarBase extends TileEntityBase implements ITickable, IWrenchable {


    public BasicSource energy;
    public static Random r = new Random();


    public boolean isInit;
    public boolean isSunVisible;
    public boolean canRain;
    public boolean noSunlight;
    public int tick;
    public double output;

    public TileEntitySolarBase(){
        this.tick=r.nextInt(64);
    }




    @Override
    public void update() {
        energy.update();
        checkConditions();

    }
    public void checkConditions() {
        if (!isInit && world != null) {
            canRain = world.getBiomeProvider().getBiome(pos).getRainfall() > 0;
                        isInit = true;
        }
        if (noSunlight) {
            return;
        }
        if (tick-- == 0) {
            updateSunState();
            tick = 64;
        }
        createEnergy();
    }

    public void createEnergy() {
        if(isSunVisible)
            energy.addEnergy(output);
    }

    public void updateSunState() {
        boolean isRaining = canRain && (world.isRaining() || world.isThundering());
        isSunVisible = world.isDaytime() && !isRaining && world.canBlockSeeSky(pos);
    }



    @Override
    public void onChunkUnload() {
        energy.onChunkUnload();
    }

    @Override
    public void invalidate() {
        energy.invalidate();
        super.invalidate();
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return null;
    }


}
