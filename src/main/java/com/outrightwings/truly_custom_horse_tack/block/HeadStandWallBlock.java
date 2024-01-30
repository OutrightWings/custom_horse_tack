package com.outrightwings.truly_custom_horse_tack.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class HeadStandWallBlock extends Rotate4Block{
    private static final Map<Direction, VoxelShape> SHAPE_MAP = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Shapes.or(Block.box(15, 2, 2, 16, 14, 14),Block.box(8,3,6.5,16,7,9.5)),
            Direction.SOUTH, Shapes.or(Block.box(0, 2, 2, 1, 14, 14),Block.box(0,3,6.5,8,7,9.5)),
            Direction.EAST, Shapes.or(Block.box(2, 2, 15, 14, 14, 16),Block.box(6.5,3,8,9.5,7,16)),
            Direction.WEST, Shapes.or(Block.box(2, 2, 0, 14, 14, 1),Block.box(6.5,3,0,9.5,7,8))
    ));

    public HeadStandWallBlock(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
        Direction direction = p_48816_.getValue(FACING);
        return SHAPE_MAP.get(direction);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlocks.HEAD_STAND_WALL_BE.get().create(pos,state);
    }
}
