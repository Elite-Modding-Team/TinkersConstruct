package slimeknights.tconstruct.tools.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import slimeknights.mantle.inventory.BaseContainer;

public class ContainerSideInventory<T extends TileEntity> extends BaseContainer<T> {

  public final int columns;
  public final int slotCount;

  public ContainerSideInventory(T tile, int x, int y, int columns) {
    this(tile, null, x, y, columns);
  }

  public ContainerSideInventory(T tile, EnumFacing dir, int x, int y, int columns) {
    super(tile, dir);

    this.columns = columns;
    this.slotCount = itemHandler.getSlots();

    int rows = slotCount / columns;
    if(slotCount % columns != 0) {
      rows++;
    }

    int index = 0;
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < columns; c++) {
        if(index >= slotCount) {
          break;
        }
        this.addSlotToContainer(createSlot(itemHandler, index, x + c * 18, y + r * 18));
        index++;
      }
    }
  }

  protected Slot createSlot(IItemHandler itemHandler, int index, int x, int y) {
    if(itemHandler instanceof IItemHandlerModifiable) {
      return new ExternalSlotItemHandler(itemHandler, (IItemHandlerModifiable) itemHandler, index, x, y);
    }

    return new SlotItemHandler(itemHandler, index, x, y);
  }

  private static class ExternalSlotItemHandler extends SlotItemHandler {

    private final IItemHandler itemHandler;
    private final IItemHandlerModifiable modifiableItemHandler;
    private final int index;

    private ExternalSlotItemHandler(IItemHandler itemHandler, IItemHandlerModifiable modifiableItemHandler, int index, int xPosition, int yPosition) {
      super(itemHandler, index, xPosition, yPosition);
      this.itemHandler = itemHandler;
      this.modifiableItemHandler = modifiableItemHandler;
      this.index = index;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
      return !stack.isEmpty() && itemHandler.isItemValid(index, stack);
    }

    @Nonnull
    @Override
    public ItemStack getStack() {
      return itemHandler.getStackInSlot(index);
    }

    @Override
    public void putStack(@Nonnull ItemStack stack) {
      modifiableItemHandler.setStackInSlot(index, stack);
      this.onSlotChanged();
    }

    @Override
    public void onSlotChange(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack) {
    }

    @Override
    public int getSlotStackLimit() {
      return itemHandler.getSlotLimit(index);
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
      return this.getSlotStackLimit();
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
      return !itemHandler.extractItem(index, 1, true).isEmpty();
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int amount) {
      ItemStack currentStack = this.getStack();
      if(currentStack.isEmpty() || amount <= 0) {
        return ItemStack.EMPTY;
      }

      ItemStack removedStack = currentStack.splitStack(Math.min(amount, currentStack.getCount()));
      modifiableItemHandler.setStackInSlot(index, currentStack.isEmpty() ? ItemStack.EMPTY : currentStack);
      return removedStack;
    }

    @Override
    public boolean isSameInventory(Slot other) {
      return other instanceof ExternalSlotItemHandler && ((ExternalSlotItemHandler) other).itemHandler == this.itemHandler;
    }
  }

  public int getSlotCount() {
    return slotCount;
  }

  public int getSizeInventory() {
    return itemHandler.getSlots();
  }
}
