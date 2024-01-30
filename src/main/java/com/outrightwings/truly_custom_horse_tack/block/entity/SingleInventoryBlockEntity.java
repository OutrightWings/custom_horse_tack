package com.outrightwings.truly_custom_horse_tack.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class SingleInventoryBlockEntity extends BlockEntity implements WorldlyContainer{
    private NonNullList<ItemStack> stacks;
    public SingleInventoryBlockEntity(BlockEntityType<?> be, BlockPos pos, BlockState state) {
        super(be, pos, state);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }

    public void drops() {
        Containers.dropItemStack(this.level, this.worldPosition.getX(),this.worldPosition.getY(),this.worldPosition.getZ(), this.stacks.get(0));
    }
    @Override
    public int[] getSlotsForFace(Direction dir) {
        return new int[1];
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_19235_, ItemStack p_19236_, @Nullable Direction p_19237_) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.stacks.get(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return stacks.get(0);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack copy = this.stacks.get(0).copy();
        this.stacks.get(0).shrink(1);
        setChanged();
        return copy;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_18951_) {
        ItemStack copy = this.stacks.get(0).copy();
        this.stacks.get(0).shrink(1);
        return copy;
    }

    @Override
    public void setItem(int p_18944_, ItemStack item) {
        this.stacks.set(0,item.copy());
        setChanged();
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return false;
    }
    public abstract boolean validItem(ItemStack item);
    @Override
    public void clearContent() {
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    public void setChanged() {
        if (this.level == null) return;
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        super.setChanged();
    }
    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.stacks);
        if (this.level != null)
            if (this.level.isClientSide) this.requestModelDataUpdate();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        ContainerHelper.saveAllItems(compound, this.stacks);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

}
