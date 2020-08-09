package tfar.rcraft.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.RanchCraft;
import tfar.rcraft.item.FlyRodItem;

public class ModItems {

	public static Item ANGLER;
	public static Item CUDDLER;
	public static Item FLY_ROD;
	public static Item MALLET;
	public static Item TENDERIZER;
	public static Item WHEEL;

	public static final ItemGroup TAB = new ItemGroup(RanchCraft.MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WHEEL);
		}
	};

	public static void register(RegistryEvent.Register<Item> event) {
		ANGLER = RegisterHelper.register(new BlockItem(ModBlocks.ANGLER,new Item.Properties().group(TAB)),"angler", event.getRegistry());
		CUDDLER = RegisterHelper.register(new BlockItem(ModBlocks.CUDDLER,new Item.Properties().group(TAB)),"cuddler", event.getRegistry());
		FLY_ROD = RegisterHelper.register(new FlyRodItem(new Item.Properties().maxDamage(128).group(TAB)),"fly_rod", event.getRegistry());
		MALLET = RegisterHelper.register(new Item(new Item.Properties().maxDamage(128).group(TAB)),"mallet", event.getRegistry());
		TENDERIZER = RegisterHelper.register(new BlockItem(ModBlocks.TENDERIZER,new Item.Properties().group(TAB)),"tenderizer", event.getRegistry());
		WHEEL = RegisterHelper.register(new Item(new Item.Properties().group(TAB)),"wheel", event.getRegistry());
	}
}
