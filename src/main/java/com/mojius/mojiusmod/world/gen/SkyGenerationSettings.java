package com.mojius.mojiusmod.world.gen;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.GenerationSettings;

public class SkyGenerationSettings extends GenerationSettings {
   private BlockPos spawnPos;

   public SkyGenerationSettings setSpawnPos(BlockPos pos) {
      this.spawnPos = pos;
      return this;
   }

   public BlockPos getSpawnPos() {
      return this.spawnPos;
   }

}