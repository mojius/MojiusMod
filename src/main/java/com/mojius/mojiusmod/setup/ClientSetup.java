package com.mojius.mojiusmod.setup;


import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.client.renders.GrassmanRender;
import com.mojius.mojiusmod.client.renders.TNTArrowRender;
import com.mojius.mojiusmod.entities.GrassmanEntity;
import com.mojius.mojiusmod.entities.TNTArrowEntity;
import com.mojius.mojiusmod.init.InitItem;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MojiusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
	public static void ForgeClientEvent(FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(GrassmanEntity.class, new GrassmanRender.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(TNTArrowEntity.class, new TNTArrowRender.RenderFactory());
    }
    
	
}

