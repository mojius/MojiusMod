package com.mojius.mojiusmod.block;

import com.mojius.mojiusmod.MojiusMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RedBricksBlock extends Block {

		public RedBricksBlock() {
			super(Properties.
				create(Material.ROCK)
				.hardnessAndResistance(2.0f, 6.0f)
				.sound(SoundType.STONE)
				.harvestTool(ToolType.PICKAXE)
				);
			setRegistryName(MojiusMod.MOD_ID, "red_bricks");
	}
}
