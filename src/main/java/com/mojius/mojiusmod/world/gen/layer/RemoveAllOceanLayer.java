package com.mojius.mojiusmod.world.gen.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum RemoveAllOceanLayer implements ICastleTransformer {
   INSTANCE;

	   public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
		      return ModdedLayerUtil.isShallowOcean(center) ? 1 : center;
		   }
		}