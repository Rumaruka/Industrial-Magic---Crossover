package com.rumaruka.emt.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileEntityBase extends TileEntity   {

    public int facing;
    public boolean isOn;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        facing = compound.getInteger("facing");
        isOn = compound.getBoolean("isOn");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("facing", facing);
        compound.setBoolean("isOn", isOn);

        return compound;
    }

    public final Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(pos, 0, nbt);
        return packet;
    }



    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.getNbtCompound();
        readFromNBT(nbt);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if(world.isRemote){
            return;
        }
        this.world.markBlockRangeForRenderUpdate(pos,pos);
    }
}
