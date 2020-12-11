package com.mojius.mojiusmod.init;

import com.mojius.mojiusmod.entities.GrassmanEntity;
import com.mojius.mojiusmod.entities.TNTArrowEntity;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.registries.ObjectHolder;

public class InitEntity {

	@ObjectHolder("mojiusmod:grassman")
    public static EntityType<GrassmanEntity> GRASSMAN;

	@ObjectHolder("mojiusmod:tnt_arrow")
    public static EntityType<TNTArrowEntity> TNTARROW;
	
	public static void registerGrassman(EntityType<GrassmanEntity> type, PlacementType spawnType, Heightmap.Type heightmap, EntitySpawnPlacementRegistry.IPlacementPredicate<GrassmanEntity> p_209343_3_){
        EntitySpawnPlacementRegistry.register(type, spawnType, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, p_209343_3_);
    }
	
	
}			

