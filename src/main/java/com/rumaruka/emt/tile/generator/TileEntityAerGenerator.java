package com.rumaruka.emt.tile.generator;

import com.rumaruka.emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityAerGenerator extends TileEnitytBaseGeneratorAspect {

    public TileEntityAerGenerator(){
        aspect= Aspect.AIR;
        output= EMTEssentiasOutputs.outputs.get(aspect.getTag());

    }
}
