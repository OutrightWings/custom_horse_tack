package com.outrightwings.truly_custom_horse_tack.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.outrightwings.truly_custom_horse_tack.Main;
import com.outrightwings.truly_custom_horse_tack.item.ModItems;
import com.outrightwings.truly_custom_horse_tack.item.tack.TackPattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import sekelsta.horse_colors.entity.HorseGeneticEntity;
import sekelsta.horse_colors.entity.ModEntities;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SaddlerBlockScreen extends AbstractContainerScreen<SaddlerBlockMenu> {
    private static final ResourceLocation BG_LOCATION = new ResourceLocation(Main.MODID, "textures/gui/container/saddler.png");
    private static final int PATTERN_COLUMNS = 4;
    private static final int PATTERN_ROWS = 4;
    private static final int SCROLLER_WIDTH = 5;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int PATTERN_IMAGE_SIZE = 14;
    private static final int SCROLLER_FULL_HEIGHT = 56;
    private static final int PATTERNS_X = 55;
    private static final int PATTERNS_Y = 13;
    private static final int SCROLL_X = 113;
    private static final int SCROLL_Y = 13;
    private static final int HORSE_X = 143;
    private static final int HORSE_Y = 65;
    private static final int PREVIEW_X = 163 ;
    private static final int PREVIEW_Y = 65;
    private static final int PREVIEW_WIDTH = 4;
    private static final int PREVIEW_HEIGHT = 4;
    private static final int PREVIEW_BUTTON = 208;
    private static final int MAX_HORSE_PREVIEWS = 2;
    private int currentPreview = 0;
    private ItemStack saddleStack = ItemStack.EMPTY;
    private ItemStack dyeStack = ItemStack.EMPTY;
    private ItemStack patternStack = ItemStack.EMPTY;
    private boolean displayPatterns;
    private boolean hasMaxPatterns;
    private float scrollOffs;
    private boolean scrolling;
    private int startRow;
    public HorseGeneticEntity horsePreview;
    public SaddlerBlockScreen(SaddlerBlockMenu menu, Inventory inventory, Component name) {
        super(menu, inventory, name);
        menu.registerUpdateListener(this::containerChanged);
        this.titleLabelY -= 2;

    }

    protected void init() {
        super.init();
        if (Minecraft.getInstance().level != null){
            horsePreview = new HorseGeneticEntity(ModEntities.HORSE_GENETIC.get(), Minecraft.getInstance().level);
            baseHorseSetup();
        }
    }

    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    private int totalRowCount() {
        return Mth.positiveCeilDiv(this.menu.getSelectablePatterns().size(), PATTERN_COLUMNS);
    }
    public void onClose(){
        super.onClose();
        if (this.horsePreview != null) {
            this.horsePreview.remove(Entity.RemovalReason.DISCARDED);
        }
    }
    protected void renderBg(GuiGraphics poseStack, float tick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);
        int i = this.leftPos;
        int j = this.topPos;
        poseStack.blit(BG_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        Slot slot = this.menu.getSaddleSlot();
        Slot slot1 = this.menu.getDyeSlot();
        Slot slot2 = this.menu.getPatternSlot();
        Slot slot3 = this.menu.getResultSlot();
        if (!slot.hasItem()) {
            poseStack.blit(BG_LOCATION, i + slot.x, j + slot.y, this.imageWidth, 0, 16, 16);
        }

        if (!slot1.hasItem()) {
            poseStack.blit(BG_LOCATION, i + slot1.x, j + slot1.y, this.imageWidth + 16, 0, 16, 16);
        }

        if (!slot2.hasItem()) {
            poseStack.blit(BG_LOCATION, i + slot2.x, j + slot2.y, this.imageWidth + 32, 0, 16, 16);
        }

        int k = (int)(41.0F * this.scrollOffs);
        poseStack.blit(BG_LOCATION, i + SCROLL_X, j + SCROLL_Y + k, 232 + (this.displayPatterns ? 0 : SCROLLER_WIDTH ), 0, SCROLLER_WIDTH, SCROLLER_HEIGHT);

        boolean isHoveredPrev = mouseX >= i+ PREVIEW_X && mouseY >= j+PREVIEW_Y && mouseX < i+PREVIEW_X + PREVIEW_WIDTH && mouseY < j+PREVIEW_Y + PREVIEW_HEIGHT;
        int cornerButton;
        if(isHoveredPrev){
            cornerButton = PREVIEW_BUTTON + PREVIEW_HEIGHT;
        }else{
            cornerButton = PREVIEW_BUTTON;
        }
        poseStack.blit(BG_LOCATION, i + PREVIEW_X, j+ PREVIEW_Y, 0, cornerButton, PREVIEW_WIDTH, PREVIEW_HEIGHT);

        if (this.displayPatterns) {
            int startingX = i + PATTERNS_X;
            int startingY = j + PATTERNS_Y;
            List<TackPattern> list = this.menu.getSelectablePatterns();

            label63:
            for(int row = 0; row < PATTERN_ROWS; ++row) {
                for(int col = 0; col < PATTERN_COLUMNS; ++col) {
                    int currentRow = row + this.startRow;
                    int currentSlot = currentRow * PATTERN_COLUMNS + col;
                    if (currentSlot >= list.size()) {
                        break label63;
                    }

                    int cornerX = startingX + col * PATTERN_IMAGE_SIZE;
                    int cornerY = startingY + row * PATTERN_IMAGE_SIZE;
                    boolean isHovered = mouseX >= cornerX && mouseY >= cornerY && mouseX < cornerX + PATTERN_IMAGE_SIZE && mouseY < cornerY + PATTERN_IMAGE_SIZE;
                    int startingCorner;
                    if (currentSlot == this.menu.getSelectedSlotIndex()) {
                        startingCorner = this.imageHeight + PATTERN_IMAGE_SIZE;
                    } else if (isHovered) {
                        startingCorner = this.imageHeight + (PATTERN_IMAGE_SIZE*2);
                    } else {
                        startingCorner = this.imageHeight;
                    }
                    poseStack.blit(BG_LOCATION, cornerX, cornerY, 0, startingCorner, PATTERN_IMAGE_SIZE, PATTERN_IMAGE_SIZE);
                    poseStack.blit(list.get(currentSlot).getPatternIconLocation(), cornerX, cornerY, 0, 0, PATTERN_IMAGE_SIZE, PATTERN_IMAGE_SIZE,PATTERN_IMAGE_SIZE,PATTERN_IMAGE_SIZE);
                    if(isHovered){
                        poseStack.renderTooltip(this.font,Component.translatable(list.get(currentSlot).getTranslationKey()),cornerX,cornerY);
                    }
                }
            }
        }
        InventoryScreen.renderEntityInInventoryFollowsMouse(poseStack,i+HORSE_X,j+HORSE_Y,25,i+HORSE_X-mouseX,j+HORSE_Y-mouseY, horsePreview);
    }
    private void baseHorseSetup(){
        ItemStack horsesTack = createBaseTack();
        horsePreview.equipSaddle(null);
        horsePreview.setFlag(4, !horsePreview.inventory.getItem(0).isEmpty());
        horsePreview.setItemSlot(EquipmentSlot.CHEST,horsesTack);
        saddleUpPartner();
    }
    private void saddleUpPartner(){
        var passenger = new Allay(EntityType.ALLAY, this.minecraft.level);
        passenger.startRiding(horsePreview);
    }
    private ItemStack createBaseTack(){
        ItemStack horsesTack = new ItemStack(ModItems.CUSTOM_TACK_ITEM.get());

        CompoundTag armorTag = new CompoundTag();
        CompoundTag patternTag = new CompoundTag();
        patternTag.putString("Pattern","wood"+currentPreview);
        patternTag.putInt("Color",1);

        ListTag listtag = new ListTag();
        listtag.add(patternTag);
        armorTag.put("Patterns", listtag);

        horsesTack.setTag(armorTag);
        return horsesTack;
    }
    private ItemStack addWoodToTack(ItemStack outputSlotItem){
        ItemStack baseTack = createBaseTack();
        CompoundTag baseTackTag = baseTack.getTag();
        CompoundTag outputSlotItemTag = outputSlotItem.getTag();
        if(outputSlotItemTag != null){
            ListTag listtag;
            listtag = outputSlotItemTag.getList("Patterns", 10);
            ListTag existing = baseTackTag.getList("Patterns",10);
            existing.addAll(listtag);
            baseTackTag.put("Patterns",existing);
            baseTack.setTag(baseTackTag);
        }
        return baseTack;
    }
    private void updateHorsePreview(ItemStack outputSlotItem){
        var woodToTack = addWoodToTack(outputSlotItem);
        horsePreview.setItemSlot(EquipmentSlot.CHEST,woodToTack);
    }
    private void containerChanged() {
        ItemStack saddleItem = this.menu.getSaddleSlot().getItem();
        ItemStack dyeItem = this.menu.getDyeSlot().getItem();
        ItemStack patternItem = this.menu.getPatternSlot().getItem();
        ItemStack outputItem = this.menu.getResultSlot().getItem();

        if (!ItemStack.matches(saddleItem, this.saddleStack) || !ItemStack.matches(dyeItem, this.dyeStack) || !ItemStack.matches(patternItem, this.patternStack)) {
            this.displayPatterns = !saddleItem.isEmpty() && !dyeItem.isEmpty() && !this.hasMaxPatterns && !this.menu.getSelectablePatterns().isEmpty();
        }

        if (this.startRow >= this.totalRowCount()) {
            this.startRow = 0;
            this.scrollOffs = 0.0F;
        }

        if(!ItemStack.matches(outputItem, Items.AIR.getDefaultInstance())){
            updateHorsePreview(outputItem);
        } else if(!ItemStack.matches(saddleItem, Items.AIR.getDefaultInstance())){
            updateHorsePreview(saddleItem);
        } else{
            updateHorsePreview(createBaseTack());
        }

        this.saddleStack = saddleItem.copy();
        this.dyeStack = dyeItem.copy();
        this.patternStack = patternItem.copy();

    }
    public boolean mouseClicked(double mouseX, double mouseY, int p_99085_) {
        this.scrolling = false;
        if (this.displayPatterns) {
            int i = this.leftPos + PATTERNS_X;
            int j = this.topPos + PATTERNS_Y;

            for(int k = 0; k < PATTERN_ROWS; ++k) {
                for(int l = 0; l < PATTERN_COLUMNS; ++l) {
                    double d0 = mouseX - (double)(i + l * PATTERN_IMAGE_SIZE);
                    double d1 = mouseY - (double)(j + k * PATTERN_IMAGE_SIZE);
                    int i1 = k + this.startRow;
                    int j1 = i1 * PATTERN_COLUMNS + l;
                    if (d0 >= 0.0D && d1 >= 0.0D && d0 < PATTERN_IMAGE_SIZE && d1 < PATTERN_IMAGE_SIZE && this.menu.clickMenuButton(this.minecraft.player, j1)) {
                        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_LOOM_SELECT_PATTERN, 1.0F));
                        this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, j1);
                        return true;
                    }
                }
            }

            i = this.leftPos + SCROLL_X;
            j = this.topPos + SCROLL_Y;
            if (mouseX >= (double)i && mouseX < (double)(i + SCROLLER_WIDTH) && mouseY >= (double)j && mouseY < (double)(j + SCROLLER_FULL_HEIGHT)) {
                this.scrolling = true;
            }
        }
        if(mouseX >= this.leftPos + PREVIEW_X && mouseY >= this.topPos + PREVIEW_Y && mouseX < this.leftPos + PREVIEW_X + PREVIEW_WIDTH && mouseY < this.topPos + PREVIEW_Y + PREVIEW_HEIGHT){
            currentPreview = currentPreview < MAX_HORSE_PREVIEWS-1 ? ++currentPreview : 0;
            containerChanged();
        }
        return super.mouseClicked(mouseX, mouseY, p_99085_);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int p_99089_, double p_99090_, double p_99091_) {
        int i = this.totalRowCount() - 4;
        if (this.scrolling && this.displayPatterns && i > 0) {
            int j = this.topPos + SCROLL_Y;
            int k = j + SCROLLER_FULL_HEIGHT;
            this.scrollOffs = ((float)mouseY - (float)j - ((float)SCROLLER_HEIGHT)/2) / ((float)(k - j) - ((float)SCROLLER_HEIGHT));
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startRow = Math.max((int)((double)(this.scrollOffs * (float)i) + 0.5D), 0);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, p_99089_, p_99090_, p_99091_);
        }
    }

    public boolean mouseScrolled(double p_99079_, double p_99080_, double p_99081_) {
        int i = this.totalRowCount() - 4;
        if (this.displayPatterns && i > 0) {
            float f = (float)p_99081_ / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startRow = Math.max((int)(this.scrollOffs * (float)i + 0.5F), 0);
        }

        return true;
    }

}
