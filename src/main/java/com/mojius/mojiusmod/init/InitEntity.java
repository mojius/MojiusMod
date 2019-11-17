package com.mojius.mojiusmod.init;

import com.mojius.mojiusmod.entities.TNTArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class InitEntity {

	@ObjectHolder("mojiusmod:grassman")
    public static EntityType<?> GRASSMAN;

	@ObjectHolder("mojiusmod:tnt_arrow")
    public static EntityType<TNTArrowEntity> TNTARROW;
	
}			

