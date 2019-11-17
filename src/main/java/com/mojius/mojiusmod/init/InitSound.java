package com.mojius.mojiusmod.init;

import com.mojius.mojiusmod.MojiusMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MojiusMod.MOD_ID)
public class InitSound {
	@ObjectHolder("entity.grassman.ambient")
	public static SoundEvent GRASSMAN_AMBIENT = createSoundEvent("entity.grassman.ambient");
	@ObjectHolder("entity.grassman.hurt")
	public static SoundEvent GRASSMAN_HURT = createSoundEvent("entity.grassman.hurt");
	@ObjectHolder("entity.grassman.death")
	public static SoundEvent GRASSMAN_DEATH = createSoundEvent("entity.grassman.death");
	
	private static SoundEvent createSoundEvent(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(MojiusMod.MOD_ID, soundName);
		SoundEvent event = new SoundEvent(soundID).setRegistryName(soundID);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
