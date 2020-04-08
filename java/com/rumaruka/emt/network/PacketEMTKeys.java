package com.rumaruka.emt.network;

import com.rumaruka.emt.init.EMTItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;

public class PacketEMTKeys implements IMessage, IMessageHandler<PacketEMTKeys,IMessage> {


    @Override
    public IMessage onMessage(PacketEMTKeys message, MessageContext ctx) {
        if(ctx.getServerHandler().player.inventory.armorInventory.get(2)!= ItemStack.EMPTY && ctx.getServerHandler().player.inventory.armorInventory.get(2).getItem()== EMTItems.quantumarmor){
            Objects.requireNonNull(ctx.getServerHandler().player.inventory.armorInventory.get(2).getTagCompound()).setBoolean("unequip", true);
        }

        return null;
    }
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }


}
