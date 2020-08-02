package tfar.rcraft.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.RanchCraft;

public class ModItems {

	public static Item WHEEL;

	public static final ItemGroup TAB = new ItemGroup(RanchCraft.MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WHEEL);
		}
	};

	public static void registerItems(RegistryEvent.Register<Item> event) {
		WHEEL = RegisterHelper.register(new Item(new Item.Properties().group(TAB)),"wheel", event.getRegistry());
	}
}
