package com.rumaruka.emt.tile.solar.compressed;


import ic2.core.IC2;
import ic2.core.block.generator.tileentity.TileEntityBaseGenerator;
import ic2.core.init.MainConfig;
import ic2.core.network.GuiSynced;
import ic2.core.util.BiomeUtil;
import ic2.core.util.ConfigUtil;
import ic2.core.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Random;


public class TileEntityCompressedSolar extends TileEntityBaseGenerator {
    @GuiSynced
    public float skyLight;
    private int ticker;
    private static final int tickRate = 128;
    private static final double energyMultiplier = ConfigUtil.getDouble(MainConfig.get(), "balance/energy/generator/solar");

    public TileEntityCompressedSolar() {
        super(4.0D, 2, 4);
        this.ticker = IC2.random.nextInt(tickRate);
    }

    protected void onLoaded() {
        super.onLoaded();
        this.updateSunVisibility();
    }

    public boolean gainEnergy() {
        if (++this.ticker % 128 == 0) {
            this.updateSunVisibility();
        }

        if (this.skyLight > 0.0F) {
            this.energy.addEnergy(energyMultiplier * (double)this.skyLight);
            return true;
        } else {
            return false;
        }
    }

    public boolean gainFuel() {
        return false;
    }

    public void updateSunVisibility() {
        this.skyLight = getSkyLight(this.getWorld(), this.pos.up());
    }

    public static float getSkyLight(World world, BlockPos pos) {
        if (world.provider.isNether()) {
            return 0.0F;
        } else {
            float sunBrightness = Util.limit((float)Math.cos((double)world.getCelestialAngleRadians(1.0F)) * 2.0F + 0.2F, 0.0F, 1.0F);
            if (!BiomeDictionary.hasType(BiomeUtil.getBiome(world, pos), BiomeDictionary.Type.SANDY)) {
                sunBrightness *= 1.0F - world.getRainStrength(1.0F) * 5.0F / 16.0F;
                sunBrightness *= 1.0F - world.getThunderStrength(1.0F) * 5.0F / 16.0F;
                sunBrightness = Util.limit(sunBrightness, 0.0F, 1.0F);
            }

            return (float)world.getLightFor(EnumSkyBlock.SKY, pos) / 15.0F * sunBrightness;
        }
    }

    public boolean needsFuel() {
        return false;
    }

    public boolean getGuiState(String name) {
        if ("sunlight".equals(name)) {
            return this.skyLight > 0.0F;
        } else {
            return super.getGuiState(name);
        }
    }

    protected boolean delayActiveUpdate() {
        return true;
    }

}
