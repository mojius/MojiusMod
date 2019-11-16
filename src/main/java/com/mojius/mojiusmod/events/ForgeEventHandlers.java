package com.mojius.mojiusmod.events;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.entities.TNTArrowEntity;

import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MojiusMod.MOD_ID)
public class ForgeEventHandlers {

	@SubscribeEvent
	public static void onArrowNock(ArrowNockEvent event)
	{
		
	}
	
	@SubscribeEvent
	public static void OnArrowHits(ProjectileImpactEvent.Arrow event)
	{
		TNTArrowEntity.explode(event);
	}
	
	
	
}