package com.outrightwings.truly_custom_horse_tack.block;

import com.outrightwings.truly_custom_horse_tack.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
@Mod.EventBusSubscriber(modid=Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    //Horse Jumps
    public static final RegistryObject<WallBlock> JUMP_WHITE = BLOCKS.register("jump_white",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_ORANGE = BLOCKS.register("jump_orange",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_MAGENTA = BLOCKS.register("jump_magenta",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_LIGHT_BLUE = BLOCKS.register("jump_light_blue",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_YELLOW = BLOCKS.register("jump_yellow",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_LIME = BLOCKS.register("jump_lime",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_PINK = BLOCKS.register("jump_pink",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_GRAY = BLOCKS.register("jump_gray",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_LIGHT_GRAY = BLOCKS.register("jump_light_gray",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_CYAN = BLOCKS.register("jump_cyan",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_PURPLE = BLOCKS.register("jump_purple",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_BLUE = BLOCKS.register("jump_blue",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_BROWN = BLOCKS.register("jump_brown",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_GREEN = BLOCKS.register("jump_green",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_RED = BLOCKS.register("jump_red",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<WallBlock> JUMP_BLACK = BLOCKS.register("jump_black",() -> new JumpBlock("simple_jumps",jumpProperties()));
    public static final RegistryObject<Block> SADDLER = BLOCKS.register("saddler",()->new SaddlerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).noOcclusion()));
    public static final RegistryObject<Block> SADDLE_RACK = BLOCKS.register("saddle_rack",()->new SaddleRack(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistryObject<Block> HEAD_STAND = BLOCKS.register("head_stand",()->new HeadStand(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MODID);
    public static final RegistryObject<BlockEntityType<SaddlerRackBlockEntity>> SADDLE_RACK_BE = BLOCK_ENTITIES.register("saddle_rack_block_entity", () -> BlockEntityType.Builder.of(SaddlerRackBlockEntity::new, SADDLE_RACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<HeadStandBlockEntity>> HEAD_STAND_BE = BLOCK_ENTITIES.register("head_stand_block_entity", () -> BlockEntityType.Builder.of(HeadStandBlockEntity::new, HEAD_STAND.get()).build(null));


    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties = new Item.Properties();
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }
    }
    private static BlockBehaviour.Properties jumpProperties(){
        return BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F);
    }
}
