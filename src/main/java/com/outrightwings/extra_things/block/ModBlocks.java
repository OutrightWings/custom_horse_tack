package com.outrightwings.extra_things.block;

import com.outrightwings.extra_things.Main;
import com.outrightwings.extra_things.item.ModCreativeTab;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
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
    public static final RegistryObject<Block> SADDLER = BLOCKS.register("saddler",()->new SaddlerBlock(BlockBehaviour.Properties.copy(Blocks.LOOM)));


    //Vanilla Backporting!
    public static final RegistryObject<Block> CHERRY_BUTTON = BLOCKS.register("cherry_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_LEAVES = BLOCKS.register("cherry_leaves",()-> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)));
    public static final RegistryObject<Block> CHERRY_LOG = BLOCKS.register("cherry_log",()-> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_PLANKS = BLOCKS.register("cherry_planks",()-> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = BLOCKS.register("cherry_pressure_plate",()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_SAPLING = BLOCKS.register("cherry_sapling",()-> new FlowerBlock(MobEffects.SATURATION, 9,BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> CHERRY_SLAB = BLOCKS.register("cherry_slab",()-> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_STAIRS = BLOCKS.register("cherry_stairs",()-> new StairBlock(()->CHERRY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = BLOCKS.register("cherry_trapdoor",()-> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(Blocks::never)));
    public static final RegistryObject<Block> CHERRY_WOOD = BLOCKS.register("cherry_wood",()-> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = BLOCKS.register("potted_cherry_sapling",()-> new FlowerPotBlock(CHERRY_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = BLOCKS.register("stripped_cherry_log",()-> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = BLOCKS.register("stripped_cherry_wood",()-> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = BLOCKS.register("cherry_fence_gate",()-> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_FENCE = BLOCKS.register("cherry_fence",() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_DOOR = BLOCKS.register("cherry_door",()->new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, Blocks.JUNGLE_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                if(!block.getDescriptionId().contains("potted")) {
                    Item.Properties properties = new Item.Properties().tab(ModCreativeTab.instance);
                    Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                    event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);

                }
            });
        }
    }
    private static BlockBehaviour.Properties jumpProperties(){
        return BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F);
    }
}
