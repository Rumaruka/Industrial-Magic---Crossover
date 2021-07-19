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


        return null;
    }
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }


}
