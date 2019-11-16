package com.mojius.mojiusmod.init;

import com.mojius.mojiusmod.items.GrassmanEggItem;
import com.mojius.mojiusmod.items.TNTArrowItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class InitItem {
	
	@ObjectHolder("mojiusmod:tnt_arrow")
	public static TNTArrowItem tnt_arrow;
	
	@ObjectHolder("mojiusmod:grassman_spawn_egg")
	public static GrassmanEggItem grassman_spawn_egg;
	
	
}



