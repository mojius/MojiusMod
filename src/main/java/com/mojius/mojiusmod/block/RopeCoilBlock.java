package com.mojius.mojiusmod.block;

import java.util.Random;

import com.mojius.mojiusmod.MojiusMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;



public class RopeCoilBlock extends HorizontalBlock {
	
		public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	 protected static final VoxelShape ROPE_COIL_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	
	public RopeCoilBlock() {
		super(Properties.
				create(Material.WOOL)
				.hardnessAndResistance(0.8f, 2.0f)
				.sound(SoundType.CLOTH)
				);
		setRegistryName(MojiusMod.MOD_ID, "rope_coil");
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		      return (func_220064_c(worldIn, pos.down()));
		   }
	   
	   public void animateTick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      if (!isValidPosition(state, worldIn, pos)) {
	          worldIn.destroyBlock(pos, true);
	      }
	   }
	   public BlockRenderLayer getRenderLayer() {
		      return BlockRenderLayer.CUTOUT;
		   }
	   
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		   //worldIn.addBlockEvent(pos.offset((Direction)state.get(FACING)).down(), new WitherRoseBlock(), 1, 0);
		   }
	   
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
		      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
		   }
	   
	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		      builder.add(FACING);
		   }
	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		         return ROPE_COIL_AABB;
		      }
	   
}
