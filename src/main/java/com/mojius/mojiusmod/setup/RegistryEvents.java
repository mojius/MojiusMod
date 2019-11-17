package com.mojius.mojiusmod.setup;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.block.RedBricksBlock;
import com.mojius.mojiusmod.entities.GrassmanEntity;
import com.mojius.mojiusmod.entities.TNTArrowEntity;
import com.mojius.mojiusmod.init.InitBlock;
import com.mojius.mojiusmod.init.InitEntity;
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

		
		@SubscribeEvent
		public static void registerEntity(final RegistryEvent.Register<EntityType<?>> event)
		{
			event.getRegistry().registerAll(
			 
			InitEntity.TNTARROW,
			InitEntity.GRASSMAN);
					
			
			
			InitEntity.registerEntityWorldSpawns();
			
		}
		
		
		@SubscribeEvent
		public static void registerSound(final RegistryEvent.Register<SoundEvent> event)
		{
			
		}
		

		
		
		public static ResourceLocation registerResourceLocation(String name)
		{
			return new ResourceLocation(MojiusMod.MOD_ID, name);
		}
		
	}

