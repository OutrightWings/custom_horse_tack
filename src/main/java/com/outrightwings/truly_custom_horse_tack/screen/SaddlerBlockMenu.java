package com.outrightwings.truly_custom_horse_tack.screen;

import com.outrightwings.truly_custom_horse_tack.Config;
import com.outrightwings.truly_custom_horse_tack.block.ModBlocks;
import com.outrightwings.truly_custom_horse_tack.item.CustomTackItem;
import com.outrightwings.truly_custom_horse_tack.item.MixedDye;
import com.outrightwings.truly_custom_horse_tack.item.TackPatternItem;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;

import java.util.List;

public class SaddlerBlockMenu extends AbstractContainerMenu {
    private static final int PATTERN_NOT_SET = -1;
    private static final int INV_SLOT_START = 4;
    private static final int INV_SLOT_END = 31;
    private static final int USE_ROW_SLOT_START = 31;
    private static final int USE_ROW_SLOT_END = 40;
    private final ContainerLevelAccess access;
    final DataSlot selectedSlotIndex = DataSlot.standalone();
    private List<TackPattern> selectablePatterns = List.of();
    Runnable slotUpdateListener = () -> {
    };
    final Slot saddleSlot;
    final Slot dyeSlot;
    private final Slot patternSlot;
    private final Slot resultSlot;
    long lastSoundTime;
    private final Container inputContainer = new SimpleContainer(3) {
        public void setChanged() {
            super.setChanged();
            SaddlerBlockMenu.this.slotsChanged(this);
            SaddlerBlockMenu.this.slotUpdateListener.run();
        }
    };
    private final Container outputContainer = new SimpleContainer(1) {
        public void setChanged() {
            super.setChanged();
            SaddlerBlockMenu.this.slotUpdateListener.run();
        }
    };

    public SaddlerBlockMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }
    public SaddlerBlockMenu(int id, Inventory inventory, final ContainerLevelAccess access) {
        super(ModMenus.SADDLER_BLOCK_MENU.get(), id);
        this.access = access;
        this.saddleSlot = this.addSlot(new Slot(this.inputContainer, 0, 9, 15) {
            public boolean mayPlace(ItemStack item) { return item.getItem() instanceof CustomTackItem; }
        });
        this.dyeSlot = this.addSlot(new Slot(this.inputContainer, 1, 9, 33) {
            public boolean mayPlace(ItemStack item) {
                return item.getItem() instanceof DyeItem || item.getItem() instanceof MixedDye;
            }
        });
        this.patternSlot = this.addSlot(new Slot(this.inputContainer, 2, 9, 51) {
            public boolean mayPlace(ItemStack item) {
                return item.getItem() instanceof TackPatternItem;
            }
        });
        this.resultSlot = this.addSlot(new Slot(this.outputContainer, 0, 32, 35) {
            public boolean mayPlace(ItemStack item) {
                return false;
            }

            public void onTake(Player player, ItemStack item) {
                SaddlerBlockMenu.this.saddleSlot.remove(1);
                if(Config.consume_dye){
                    SaddlerBlockMenu.this.dyeSlot.remove(1);
                }
                if (!SaddlerBlockMenu.this.saddleSlot.hasItem() || !SaddlerBlockMenu.this.dyeSlot.hasItem()) {
                    SaddlerBlockMenu.this.selectedSlotIndex.set(-1);
                }

                access.execute((level, pos) -> {
                    long l = level.getGameTime();
                    if (SaddlerBlockMenu.this.lastSoundTime != l) {
                        level.playSound(null, pos, SoundEvents.UI_LOOM_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        SaddlerBlockMenu.this.lastSoundTime = l;
                    }

                });
                super.onTake(player, item);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedSlotIndex);
    }
    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.SADDLER.get());
    }
    public boolean clickMenuButton(Player player, int slot) {
        if (slot >= 0 && slot < this.selectablePatterns.size()) {
            this.selectedSlotIndex.set(slot);
            this.setupResultSlot(this.selectablePatterns.get(slot));
            return true;
        } else {
            return false;
        }
    }

    private List<TackPattern> getSelectablePatterns(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return TackPattern.getTackPatterns("none");
        } else {
            Item item = itemStack.getItem();
            if (item instanceof TackPatternItem tackItem) {
                return TackPattern.getTackPatterns(tackItem.getPatternName());
            } else {
                return List.of();
            }
        }
    }

    private boolean isValidPatternIndex(int selected) {
        return selected >= 0 && selected < this.selectablePatterns.size();
    }

    public void slotsChanged(Container container) {
        //Get Items in all slots
        ItemStack saddleSlotItem = this.saddleSlot.getItem();
        ItemStack dyeSlotItem = this.dyeSlot.getItem();
        ItemStack patternSlotItem = this.patternSlot.getItem();
        if (!saddleSlotItem.isEmpty() && !dyeSlotItem.isEmpty()) {
            //Have a saddle and dye, start showing available patterns
            int i = this.selectedSlotIndex.get();
            boolean flag = this.isValidPatternIndex(i);
            List<TackPattern> list = this.selectablePatterns;
            this.selectablePatterns = this.getSelectablePatterns(patternSlotItem);

            TackPattern selectedPattern;
            if (this.selectablePatterns.size() == 1) { //Only 1 possible pattern
                this.selectedSlotIndex.set(0);
                selectedPattern = this.selectablePatterns.get(0);
            } else if (!flag) { //no selected Pattern
                this.selectedSlotIndex.set(-1);
                selectedPattern = null;
            } else {
                //Get pattern at selected slot
                TackPattern pattern = list.get(i);
                int j = this.selectablePatterns.indexOf(pattern);
                if (j != -1) {
                    selectedPattern = pattern;
                    this.selectedSlotIndex.set(j);
                } else {
                    selectedPattern = null;
                    this.selectedSlotIndex.set(-1);
                }
            }
            //Have a pattern selected
            if (selectedPattern != null) {
                CompoundTag compoundtag = BlockItem.getBlockEntityData(saddleSlotItem);
                boolean nbtTooFull = compoundtag != null && compoundtag.contains("Patterns", 9) && !saddleSlotItem.isEmpty() && compoundtag.getList("Patterns", 10).size() >= 6;
                if (nbtTooFull) {
                    this.selectedSlotIndex.set(-1);
                    this.resultSlot.set(ItemStack.EMPTY);
                } else {
                    this.setupResultSlot(selectedPattern);
                }
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
            this.broadcastChanges();
        } else {
            //Don't show patterns as there is nothing to apply them to
            this.resultSlot.set(ItemStack.EMPTY);
            this.selectablePatterns = List.of();
            this.selectedSlotIndex.set(-1);
        }
    }

    public void registerUpdateListener(Runnable p_39879_) {
        this.slotUpdateListener = p_39879_;
    }

    //Where should stuff go when you shift click it?
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == this.resultSlot.index) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != this.dyeSlot.index && index != this.saddleSlot.index && index != this.patternSlot.index) {
                if (itemstack1.getItem() instanceof HorseArmorItem) {
                    if (!this.moveItemStackTo(itemstack1, this.saddleSlot.index, this.saddleSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() instanceof DyeItem || itemstack1.getItem() instanceof MixedDye) {
                    if (!this.moveItemStackTo(itemstack1, this.dyeSlot.index, this.dyeSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() instanceof TackPatternItem) {
                    if (!this.moveItemStackTo(itemstack1, this.patternSlot.index, this.patternSlot.index + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 31) {
                    if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    //Wipe inventory clear when player exits
    public void removed(Player player) {
        super.removed(player);
        this.clearContainer(player, this.inputContainer);
    }

    //Create output item
    private void setupResultSlot(TackPattern tackPattern) {
        ItemStack saddleSlotItem = this.saddleSlot.getItem();
        ItemStack dyeSlotItem = this.dyeSlot.getItem();
        ItemStack outputSlotItem = ItemStack.EMPTY;
        //Stuff in slots proceed
        if (!saddleSlotItem.isEmpty() && !dyeSlotItem.isEmpty()) {
            //Copy input to output
            outputSlotItem = saddleSlotItem.copy();
            outputSlotItem.setCount(1);


            CompoundTag tagData = outputSlotItem.getTag();
            ListTag listtag;
            if (tagData != null && tagData.contains("Patterns", 9)) {
                listtag = tagData.getList("Patterns", 10);
            } else {
                listtag = new ListTag();
                if (tagData == null) {
                    tagData = new CompoundTag();
                }

                tagData.put("Patterns", listtag);
            }

            CompoundTag newData = new CompoundTag();
            newData.putString("Pattern", tackPattern.getSerializedName());

            if(dyeSlotItem.getItem() instanceof DyeItem dyeItem){
                DyeColor dyecolor = dyeItem.getDyeColor();
                newData.putInt("Color", dyecolor.getId());
            } else if( dyeSlotItem.getItem() instanceof MixedDye mixedDye){
                newData.putInt("Color", mixedDye.getColor(dyeSlotItem));
            }

            listtag.add(newData);
            outputSlotItem.setTag(tagData);
        }

        if (!ItemStack.matches(outputSlotItem, this.resultSlot.getItem())) {
            this.resultSlot.set(outputSlotItem);
        }

    }

    /* GETTERS */
    public Slot getSaddleSlot() {
        return this.saddleSlot;
    }
    public Slot getDyeSlot() {
        return this.dyeSlot;
    }
    public Slot getPatternSlot() {
        return this.patternSlot;
    }
    public Slot getResultSlot() {
        return this.resultSlot;
    }
    public int getSelectedSlotIndex() {
        return this.selectedSlotIndex.get();
    }
    public List<TackPattern> getSelectablePatterns() { return this.selectablePatterns; }
}
