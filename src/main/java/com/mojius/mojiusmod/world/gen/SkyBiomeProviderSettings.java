package com.mojius.mojiusmod.world.gen;

import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class SkyBiomeProviderSettings implements IBiomeProviderSettings {
   private WorldInfo worldInfo;
   private SkyGenerationSettings generatorSettings;

   public SkyBiomeProviderSettings setWorldInfo(WorldInfo info) {
      this.worldInfo = info;
      return this;
   }

   public SkyBiomeProviderSettings setGeneratorSettings(SkyGenerationSettings settings) {
      this.generatorSettings = settings;
      return this;
   }

   public WorldInfo getWorldInfo() {
      return this.worldInfo;
   }

   public SkyGenerationSettings getGeneratorSettings() {
      return this.generatorSettings;
   }
}