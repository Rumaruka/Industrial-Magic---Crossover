package com.rumaruka.emt.init;

import com.rumaruka.emt.emt;
import com.rumaruka.emt.entity.EntityArcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EMTEntities {
    private static int startEID = 300;
    private static int entityIDs = 0;
    private static World world;


    public static void registerEMTEntities() {
        //EntityRegistry.registerModEntity(EntityLaser.class, "laser", entityIDs++, EMT.instance, 80, 3, true);
        EntityRegistry.registerModEntity( new ResourceLocation(emt.MOD_ID,"archer"),EntityArcher.class, "entityArcher",entityIDs++, emt.instance, 80, 3, true);
        EntityRegistry.registerEgg( new ResourceLocation(emt.MOD_ID,"archer"),0x99111F, 0xE5685);
       // EntityRegistry.registerModEntity(EntityShield.class, "shield", entityIDs++, EMT.instance, 80, 3, true);
      //  EntityRegistry.registerModEntity(EntityEnergyBall.class, "energyBall", entityIDs++, EMT.instance, 80, 60, true);
    }
}

