package tfar.rcraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ItemStackItemStackHandler extends ItemStackHandler {

	protected final ItemStack stack;

	public ItemStackItemStackHandler(int slots, ItemStack stack) {
		super(slots);
		this.stack = stack;
		load(stack.getTag());
	}

	@Override
	public CompoundNBT serializeNBT() {
		ListNBT nbtTagList = new ListNBT();
		for (int i = 0; i < stacks.size(); i++) {
			if (!stacks.get(i).isEmpty()) {
				CompoundNBT itemTag = new CompoundNBT();
				itemTag.putInt("Slot", i);
				stacks.get(i).write(itemTag);
				nbtTagList.add(itemTag);
			}
		}
		CompoundNBT nbt = new CompoundNBT();
		nbt.put("Items", nbtTagList);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		ListNBT tagList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tagList.size(); i++) {
			CompoundNBT itemTags = tagList.getCompound(i);
			int slot = itemTags.getInt("Slot");

			if (slot >= 0 && slot < stacks.size()) {
				stacks.set(slot, ItemStack.read(itemTags));
			}
		}
		onLoad();
	}

	@Override
	protected void onContentsChanged(int slot) {
		super.onContentsChanged(slot);
		save();
	}

	public void save() {
		stack.getOrCreateTag().put(tfar.rcraft.util.Constants.INV,serializeNBT());
	}

	public void load(@Nullable CompoundNBT nbt) {
		if (nbt != null) {
			deserializeNBT(nbt.getCompound(tfar.rcraft.util.Constants.INV));
		}
		onLoad();
	}
}
