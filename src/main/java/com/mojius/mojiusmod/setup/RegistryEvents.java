package com.mojius.mojiusmod.setup;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.block.RedBricksBlock;
import com.mojius.mojiusmod.entities.GrassmanEntity;
import com.mojius.mojiusmod.entities.TNTArrowEntity;
import com.mojius.mojiusmod.init.InitBlock;
import com.mojius.mojiusmod.init.InitEntity;
import com.mojius.mojiusmod.init.InitSound;
import com.mojius.mojiusmod.items.GrassmanEggItem;
import com.mojius.mojiusmod.items.TNTArrowItem;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

	//Registering blocks, items, etc
	@Mod.EventBusSubscriber(modid = MojiusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public class RegistryEvents
	{
		
		@SubscribeEvent
		public static void registerSound(final RegistryEvent.Register<SoundEvent> event)
		{
			event.getRegistry().registerAll(
					InitSound.GRASSMANAMBIENT = new SoundEvent(new ResourceLocation(MojiusMod.MOD_ID, "entity_grassman_ambient")),
					InitSound.GRASSMANHURT  = new SoundEvent(new ResourceLocation(MojiusMod.MOD_ID, "entity_grassman_hurt")),
					InitSound.GRASSMANDEATH  = new SoundEvent(new ResourceLocation(MojiusMod.MOD_ID, "entity_grassman_death"))
					);
		}
		
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll(new RedBricksBlock());
		}
		
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					new BlockItem(InitBlock.red_bricks, 
					new Item.Properties()
					.group(ItemGroup.BUILDING_BLOCKS))
					.setRegistryName(InitBlock.red_bricks
					.getRegistryName()),
					
					new TNTArrowItem(new Item.Properties()
					.group(ItemGroup.COMBAT)),
					
					new GrassmanEggItem()
			);
			
		}

		
		@SuppressWarnings("unchecked")
		@SubscribeEvent
		public static void registerEntity(final RegistryEvent.Register<EntityType<?>> event)
		{
			event.getRegistry().registerAll(
			 
			InitEntity.TNTARROW  = (EntityType<TNTArrowEntity>) EntityType.Builder.<TNTArrowEntity>create(TNTArrowEntity::new, EntityClassification.MISC)
					.size(0.5F, 0.5F)
					.setCustomClientFactory((spawnEntity, world) -> new TNTArrowEntity(InitEntity.TNTARROW, world)) //TODO: Figure what the fuck this does
					.build("tnt_arrow")
					.setRegistryName(RegistryEvents.registerResourceLocation("tnt_arrow")),
			
			InitEntity.GRASSMAN = EntityType.Builder.create(GrassmanEntity::new, EntityClassification.MONSTER)
					.size(0.6f, 1.8f)
					.build("grassman")
					.setRegistryName(RegistryEvents.registerResourceLocation("grassman")));
					
			
			
			registerEntityWorldSpawns();
			
		}
		
		
		public static void registerEntityWorldSpawns()
		{
			registerEntityWorldSpawn(InitEntity.GRASSMAN, EntityClassification.MONSTER, 10, 3, 6,
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

		
		
		public static ResourceLocation registerResourceLocation(String name)
		{
			return new ResourceLocation(MojiusMod.MOD_ID, name);
		}
		
	}


