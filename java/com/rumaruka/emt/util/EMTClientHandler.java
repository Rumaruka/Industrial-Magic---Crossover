package com.rumaruka.emt.util;

import com.rumaruka.emt.client.EMTKeys;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.init.EMTItems;
import com.rumaruka.emt.network.PacketEMTKeys;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EMTClientHandler {

    public AudioSource audio;
    public Item lastItem;


    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent e){
        if(EMTKeys.keyUnequip.isPressed()){
            emt.INSTANCE.sendToServer(new PacketEMTKeys());
        }
    }
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e){
        if(e.side== Side.CLIENT){
            EntityPlayer player= Minecraft.getMinecraft().player;
            Item currectPlayer = player.inventory.getCurrentItem()== ItemStack.EMPTY ? null : player.inventory.getCurrentItem().getItem();

            if(currectPlayer == EMTItems.diamondchainsaw||currectPlayer==EMTItems.streamchainsaw||currectPlayer==EMTItems.thaumiumchainsaw){
                if(audio==null){
                    audio= IC2.audioManager.createSource(player, PositionSpec.Hand,"Tools/Chainsaw/ChainsawIdle.ogg",true,false,IC2.audioManager.getDefaultVolume());

                }
                if(audio !=null){
                    audio.updatePosition();
                    audio.play();
                }
            }
            if(audio!=null&&currectPlayer!=lastItem){
                audio.stop();
                audio.remove();
                audio=null;
                IC2.audioManager.playOnce(player, PositionSpec.Hand, "Tools/Chainsaw/ChainsawStop.ogg", true, IC2.audioManager.getDefaultVolume());
            }
            lastItem=currectPlayer;
        }
    }
}
