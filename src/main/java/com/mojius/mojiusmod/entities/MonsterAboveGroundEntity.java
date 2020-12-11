package com.mojius.mojiusmod.entities;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class MonsterAboveGroundEntity extends MonsterEntity {

	protected MonsterAboveGroundEntity(EntityType<? extends MonsterEntity> type, World p_i48553_2_) {
		super(type, p_i48553_2_);

	}

		      public static boolean func_223325_pingas(EntityType<? extends MonsterEntity> p_223325_0_, IWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_) {
		          return p_223325_1_.getBlockState(p_223325_3_.down()).getBlock() == Blocks.GRASS_BLOCK && p_223325_1_.getDifficulty() != Difficulty.PEACEFUL && func_223323_a(p_223325_1_, p_223325_3_, p_223325_4_) && func_223315_a(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
		       }
}
