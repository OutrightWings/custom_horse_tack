package com.outrightwings.truly_custom_horse_tack.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SaddleRack extends SingleInventoryBlock {
    private static final VoxelShape NS = Block.box(0, 0, 3.5, 16.0D, 15.0D, 12.5D);
    private static final VoxelShape EW = Block.box(3.5, 0, 0, 12.5, 15.0D, 16.0D);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public SaddleRack(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    public VoxelShape getShape(BlockState p_48816_, BlockGetter p_48817_, BlockPos p_48818_, CollisionContext p_48819_) {
        Direction direction = p_48816_.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? NS : EW;
    }
    @Override
    public float getRotation(BlockState state) {
        Direction direction = state.getValue(FACING).getClockWise();
        float angle;
        if (direction == Direction.NORTH) {
            angle = 90;
        }else if(direction == Direction.EAST){
            angle = 180;
        }else if(direction == Direction.SOUTH){
            angle = 270;
        }else{
            angle = 0;
        }

        return angle;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlocks.SADDLE_RACK_BE.get().create(pos,state);
    }
}
