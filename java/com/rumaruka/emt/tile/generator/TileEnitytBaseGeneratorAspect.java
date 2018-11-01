package com.rumaruka.emt.tile.generator;

import com.rumaruka.emt.tile.TileEntityBase;
import com.rumaruka.emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;

import java.util.Random;

public class TileEnitytBaseGeneratorAspect extends TileEntityBase implements ITickable {


    public BasicSource energy = new BasicSource(this, 1000000000, 3);
    public Aspect aspect;
    public double output;
    public int tick;
    public boolean isActive = false;



    public TileEnitytBaseGeneratorAspect() {
        tick=30;
        output = 0;

    }

    @Override
    public void update() {
        energy.update();
        if (tick > 0)
            tick--;

        if (tick == 0) {
            createEnergy();
            tick = EMTConfigHandler.essenstialGeneratorTicks;
        }

    }

    public void createEnergy() {
        if(!this.world.isRemote&& EssentiaHandler.drainEssentia(this,aspect, EnumFacing.UP,8,false,5)){
            energy.addEnergy(output);
        }
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
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energy.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        energy.writeToNBT(compound);
        return compound;
    }
}