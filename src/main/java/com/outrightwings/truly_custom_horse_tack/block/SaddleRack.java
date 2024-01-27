package com.outrightwings.truly_custom_horse_tack.block;

import com.outrightwings.truly_custom_horse_tack.block.entity.HeadStandBlockEntity;
import com.outrightwings.truly_custom_horse_tack.block.entity.SaddlerRackBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SaddleRack extends Block implements EntityBlock {

    public SaddleRack(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof SaddlerRackBlockEntity stand){
                ItemStack stack = player.getItemInHand(hand);
                if(stack.getItem() == Blocks.AIR.asItem()){
                    //System.out.println("Nothing in hand\n");
                    player.addItem(stand.itemHandler.extractItem(0,1,false));
                } else {
                    //System.out.println("Item in hand\n");
                    stand.itemHandler.insertItem(0,stack,false);
                }
                //System.out.println(stand.itemHandler.serializeNBT()+"\n\n");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SaddlerRackBlockEntity be) {
                be.drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlocks.SADDLE_RACK_BE.get().create(pos,state);
    }
}
