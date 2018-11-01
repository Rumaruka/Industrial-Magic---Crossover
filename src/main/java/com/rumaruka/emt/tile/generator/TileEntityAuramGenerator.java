package com.rumaruka.emt.tile.generator;

import com.rumaruka.emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityAuramGenerator extends TileEnitytBaseGeneratorAspect {
    public TileEntityAuramGenerator(){
        aspect= Aspect.AURA;
        output= EMTEssentiasOutputs.outputs.get(aspect.getTag());

    }
}
