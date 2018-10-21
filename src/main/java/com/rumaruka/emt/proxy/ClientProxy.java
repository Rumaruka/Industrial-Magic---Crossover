package com.rumaruka.emt.proxy;


import com.rumaruka.emt.client.EMTKeys;
import com.rumaruka.emt.emt;

import com.rumaruka.emt.init.EMTItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {
        EMTKeys.registerKeys();

    }

    @Override
    public void registerRenders() {
        //EMTBlocks.Render();
        EMTItems.Renders();

    }


}
