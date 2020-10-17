package tfar.ranchcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;
import tfar.ranchcraft.init.ModBlockEntities;
import tfar.ranchcraft.item.IncubatingEggItem;
import tfar.ranchcraft.menu.IncubatorMenu;
import tfar.ranchcraft.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IncubatorBlockEntity extends AbstractMachineBlockEntity implements INamedContainerProvider, INameable {

	protected ITextComponent customName;

	public static final int INPUT = 0;
	public static final int OUTPUT = 1;


	public final ItemStackHandler handler = new ItemStackHandler(2) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			markDirty();
			needsRefresh = true;
		}

		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return stack.getItem() instanceof IncubatingEggItem && slot == INPUT && super.isItemValid(slot, stack);
		}
	};

	public IncubatorBlockEntity() {
		super(ModBlockEntities.INCUBATOR);
	}

	@Override
	public void refresh() {
		if (canProcess()) {
			active = true;
			progress = 20 * 60;
		} else {
			active = false;
		}
	}

	@Override
	public void process() {
		ItemStack inputStack = handler.getStackInSlot(INPUT);
		handler.setStackInSlot(OUTPUT,new ItemStack(((IncubatingEggItem)inputStack.getItem()).spawnEgg));
		inputStack.shrink(1);
	}

	public boolean canProcess() {
		ItemStack inputStack = handler.getStackInSlot(INPUT);
		if (inputStack.isEmpty())return false;
		ItemStack outputStack = handler.getStackInSlot(OUTPUT);
		if (outputStack.isEmpty())return true;
		if (((IncubatingEggItem)inputStack.getItem()).spawnEgg == outputStack.getItem()) {
			return outputStack.getCount() < handler.getSlotLimit(OUTPUT);
		} else {
			return false;
		}
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		handler.deserializeNBT(nbt.getCompound(Constants.INV));
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(Constants.INV, handler.serializeNBT());
		return super.write(compound);
	}

	public void setCustomName(ITextComponent text) {
		this.customName = text;
	}

	@Override
	public ITextComponent getName() {
		return customName != null ? customName : getDefaultName();
	}

	ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.rcraft.incubator");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return customName;
	}

	@Nullable
	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return new IncubatorMenu(p_createMenu_1_, p_createMenu_2_, handler);
	}
}
