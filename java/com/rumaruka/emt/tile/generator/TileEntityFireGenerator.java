package com.rumaruka.emt.tile.generator;

import com.rumaruka.emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityFireGenerator extends TileEnitytBaseGeneratorAspect {

    public TileEntityFireGenerator(){
        aspect= Aspect.FIRE;
        output= EMTEssentiasOutputs.outputs.get(aspect.getTag());
    }
}
