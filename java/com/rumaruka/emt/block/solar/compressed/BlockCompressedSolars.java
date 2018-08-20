package com.rumaruka.emt.block.solar.compressed;

import com.rumaruka.emt.tile.solar.compressed.TileEntityCompressedSolar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCompressedSolars extends BlockContainer {
    public BlockCompressedSolars() {
        super(Material.IRON);
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCompressedSolar();
    }
}
