package com.rumaruka.emt.proxy;


import com.rumaruka.emt.client.EMTKeys;
import com.rumaruka.emt.init.EMTBlocks;
import com.rumaruka.emt.init.EMTItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {
        EMTKeys.registerKeys();

    }

    @Override
    public void registerRenders() {
        EMTBlocks.Render();
        EMTItems.Renders();

    }


}
