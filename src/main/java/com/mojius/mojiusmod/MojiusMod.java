package com.mojius.mojiusmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojius.mojiusmod.events.ForgeEventHandlers;
import com.mojius.mojiusmod.setup.ClientSetup;
import com.mojius.mojiusmod.setup.RegistryEvents;
import com.mojius.mojiusmod.world.types.WorldTypeSky;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mojiusmod")
public class MojiusMod {
	
	public static MojiusMod instance;
	public static final String MOD_ID = "mojiusmod"; //If you have to change your modid, change it here so you can change it everywhere
	public static final WorldType SKY_WORLD = new WorldTypeSky();
	
	  public static Logger logger = LogManager.getLogger("mojiusmod");
	
	public MojiusMod()
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(RegistryEvents.class);
		FMLJavaModLoadingContext.get().getModEventBus().register(ClientSetup.class);
	}
	
	@SuppressWarnings("unused")
	private static void ForgeCommonEvent(FMLCommonSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(ForgeEventHandlers.class);
		
	}
	

}
	

	

