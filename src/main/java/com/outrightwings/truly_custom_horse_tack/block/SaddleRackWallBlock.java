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

public class SaddleRackWallBlock extends Rotate4Block{
    private static final Map<Direction, VoxelShape> SHAPE_MAP = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Shapes.or(Block.box(14, 0, 2.5, 16, 12, 13.5),Block.box(0,5,3.5,16,9,12.5)),
            Direction.SOUTH, Shapes.or(Block.box(0, 0, 2.5, 2, 12, 13.5),Block.box(0,5,3.5,16,9,12.5)),
            Direction.EAST, Shapes.or(Block.box(2.5, 0, 14, 13.5, 12, 16),Block.box(3.5,5,0,12.5,9,16)),
            Direction.WEST, Shapes.or(Block.box(2.5, 0, 0, 13.5, 12, 2),Block.box(3.5,5,0,12.5,9,16))
    ));
    public SaddleRackWallBlock(Properties properties) {
        super(properties);
    }
    public VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
        Direction direction = p_48816_.getValue(FACING);
        return SHAPE_MAP.get(direction);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlocks.SADDLE_RACK_WALL_BE.get().create(pos,state);
    }

}
