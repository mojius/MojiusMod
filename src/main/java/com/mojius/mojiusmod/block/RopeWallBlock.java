package com.mojius.mojiusmod.block;

import com.mojius.mojiusmod.MojiusMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class RopeWallBlock extends HorizontalBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
 protected static final VoxelShape ROPE_WALL_NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
 
	public RopeWallBlock() {
			super(Properties.
					create(Material.WOOL)
					.hardnessAndResistance(0.8f, 2.0f)
					.sound(SoundType.CLOTH)
					);
			setRegistryName(MojiusMod.MOD_ID, "rope_wall_thingy");
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		      builder.add(FACING);
		   }
	   
	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	         return ROPE_WALL_NORTH_AABB;
	      }
 
	   public BlockRenderLayer getRenderLayer() {
		      return BlockRenderLayer.CUTOUT;
		   }
	   
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
		      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
		   }
}
