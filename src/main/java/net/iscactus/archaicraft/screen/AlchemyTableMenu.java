package net.iscactus.archaicraft.screen;

import net.iscactus.archaicraft.block.ModBlocks;
import net.iscactus.archaicraft.block.entity.AlchemyTableBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlchemyTableMenu extends AbstractContainerMenu {
    public final AlchemyTableBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public AlchemyTableMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(
                extraData.readBlockPos()), new SimpleContainerData(13));
    }

    public AlchemyTableMenu(int containerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ALCHEMY_TABLE_MENU.get(), containerId);
        checkContainerSize(inventory, 13);
        blockEntity = ((AlchemyTableBlockEntity)entity);
        this.level = inventory.player.level();
        this.data = data;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 107, 17));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 84, 25));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 130, 25));
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 107, 58));

            addStorageSlots(iItemHandler);
        });

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem())
            return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < 36) {
            if (!moveItemStackTo(sourceStack, 36,
                    49, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 49) {
            if (!moveItemStackTo(sourceStack, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.ALCHEMY_TABLE.get());
    }

    private void addStorageSlots(IItemHandler iItemHandler) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlot(new SlotItemHandler(iItemHandler, x + y * 3 + 4, 17 + x * 18, 17 + y * 18));
            }
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 142));
        }
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 20;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }
}
