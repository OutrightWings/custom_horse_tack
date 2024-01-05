package com.outrightwings.truly_custom_horse_tack.block;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class JumpBlock extends WallBlock {
    private final TagKey<Block> connectables;

    public JumpBlock(String tag, Properties properties){
        super(properties);
        connectables = BlockTags.create(new ResourceLocation(Main.MODID,tag));
        this.shapeByIndex = makeShapes(1F, 1.0F, 16.0F, 0.0F, 13.0F, 16.0F);
        this.collisionShapeByIndex = makeShapes(1F, 1.0F, 16.0F, 0.0F, 16.0F, 16.0F);
    }
    public boolean connectsTo(BlockState state, boolean bool, Direction direction) {
        return state.is(connectables);
    }
}
