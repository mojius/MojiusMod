package com.mojius.mojiusmod.world.types;

import com.mojius.mojiusmod.world.gen.SkyBiomeProvider;
import com.mojius.mojiusmod.world.gen.SkyBiomeProviderSettings;
import com.mojius.mojiusmod.world.gen.SkyChunkGenerator;
import com.mojius.mojiusmod.world.gen.SkyGenerationSettings;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

public class WorldTypeSky extends WorldType {

	public WorldTypeSky() {
		super("sky_type");
	}

	@Override
	public double getHorizon(World world)
    {
        return -256.0D;
    }
	
	public ChunkGenerator<?> createChunkGenerator(World world)
	{
		 if (world.getDimension().getType() == DimensionType.OVERWORLD)
		 {
				SkyGenerationSettings settings = new SkyGenerationSettings();
				SkyBiomeProviderSettings biomeSettings = new SkyBiomeProviderSettings();
				biomeSettings.setWorldInfo(world.getWorldInfo());
				biomeSettings.setGeneratorSettings(settings);
				return (ChunkGenerator<?>)new SkyChunkGenerator(world, new SkyBiomeProvider(biomeSettings), settings);
		}
		return super.createChunkGenerator(world);		
	}
	

	
}
