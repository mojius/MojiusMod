package com.mojius.mojiusmod.items;

import java.util.Objects;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.init.InitEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;	
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

public class GrassmanEggItem extends Item {

    public GrassmanEggItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(ItemGroup.MISC));
        setRegistryName(MojiusMod.MOD_ID, "grassman_spawn_egg");
    }

    /**
     * Called when this item is used when targetting a Block
     */
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block == Blocks.SPAWNER) {
                TileEntity tileentity = world.getTileEntity(blockpos);
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity)tileentity).getSpawnerBaseLogic();
                    abstractspawner.setEntityType(InitEntity.GRASSMAN);
                    tileentity.markDirty();
                    world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            if (InitEntity.GRASSMAN.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

}