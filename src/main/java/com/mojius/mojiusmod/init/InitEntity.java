package com.mojius.mojiusmod.init;

import com.mojius.mojiusmod.entities.GrassmanEntity;
import com.mojius.mojiusmod.entities.TNTArrowEntity;
import com.mojius.mojiusmod.setup.RegistryEvents;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.registries.ObjectHolder;

public class InitEntity {

	@ObjectHolder("mojiusmod:grassman")
    public static EntityType<?> GRASSMAN = EntityType.Builder.create(GrassmanEntity::new, EntityClassification.MONSTER)
			.size(0.6f, 1.8f)
			.build("grassman")
			.setRegistryName(RegistryEvents.registerResourceLocation("grassman"));

	@ObjectHolder("mojiusmod:tnt_arrow")
    public static EntityType<TNTArrowEntity> TNTARROW = (EntityType<TNTArrowEntity>) EntityType.Builder.<TNTArrowEntity>create(TNTArrowEntity::new, EntityClassification.MISC)
			.size(0.5F, 0.5F)
			.setCustomClientFactory((spawnEntity, world) -> new TNTArrowEntity(InitEntity.TNTARROW, world)) //TODO: Figure what the fuck this does
			.build("tnt_arrow")
			.setRegistryName(RegistryEvents.registerResourceLocation("tnt_arrow"));

	public static void registerEntityWorldSpawns()
	{
		registerEntityWorldSpawn(GRASSMAN, EntityClassification.MONSTER, 10, 3, 6,
				Biomes.DARK_FOREST_HILLS, 
				Biomes.DARK_FOREST,
				Biomes.FLOWER_FOREST,
				Biomes.JUNGLE,
				Biomes.JUNGLE_EDGE,
				Biomes.JUNGLE_HILLS,
				Biomes.FOREST,
				Biomes.MOUNTAIN_EDGE,
				Biomes.MOUNTAINS,
				Biomes.SWAMP,
				Biomes.SWAMP_HILLS,
				Biomes.PLAINS,
				Biomes.WOODED_HILLS,
				Biomes.WOODED_MOUNTAINS,
				Biomes.SUNFLOWER_PLAINS,
				Biomes.BIRCH_FOREST,
				Biomes.BIRCH_FOREST_HILLS,
				Biomes.TALL_BIRCH_FOREST
				);
	}	
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, EntityClassification classification, int weight, int minAmount, int maxAmount, Biome...biomes)
	{
		for (Biome biome : biomes)
		{
			if (biome != null)
			{
				biome.getSpawns(classification).add(new SpawnListEntry(entity, weight, minAmount, maxAmount));
			}
		}
		
		
	}
	
	
	
	
}			

