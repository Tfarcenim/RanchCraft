package tfar.rcraft.init;

import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.RanchCraft;
import tfar.rcraft.item.FlyRodItem;
import tfar.rcraft.item.IncubatingEggItem;
import tfar.rcraft.item.MultiToolItem;
import tfar.rcraft.item.TannedSatchelItem;

public class ModItems {

	public static Item ANGLER;
	public static Item CUDDLER;
	public static Item FLY_ROD;
	public static Item INCUBATOR;
	public static Item MALLET;
	public static Item MULTITOOL;
	public static Item SOAKED_LEATHER;
	public static Item TANNED_LEATHER;
	public static Item TANNED_SATCHEL;
	public static Item TANNING_RACK;
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
		INCUBATOR = RegisterHelper.register(new BlockItem(ModBlocks.INCUBATOR,new Item.Properties().group(TAB)),"incubator", event.getRegistry());
		MALLET = RegisterHelper.register(new Item(new Item.Properties().maxDamage(128).group(TAB)),"mallet", event.getRegistry());
		MULTITOOL = RegisterHelper.register(new MultiToolItem(new Item.Properties().group(TAB)),"multitool", event.getRegistry());
		SOAKED_LEATHER = RegisterHelper.register(new Item(new Item.Properties().group(TAB)),"soaked_leather", event.getRegistry());
		TANNED_LEATHER = RegisterHelper.register(new Item(new Item.Properties().group(TAB)),"tanned_leather", event.getRegistry());
		TANNED_SATCHEL = RegisterHelper.register(new TannedSatchelItem(new Item.Properties().group(TAB)),"tanned_satchel", event.getRegistry());
		TANNING_RACK = RegisterHelper.register(new BlockItem(ModBlocks.TANNING_RACK,new Item.Properties().group(TAB)),"tanning_rack", event.getRegistry());
		TENDERIZER = RegisterHelper.register(new BlockItem(ModBlocks.TENDERIZER,new Item.Properties().group(TAB)),"tenderizer", event.getRegistry());
		WHEEL = RegisterHelper.register(new Item(new Item.Properties().group(TAB)),"wheel", event.getRegistry());

		Item.Properties properties = new Item.Properties().group(TAB);

		String[] strings = new String[]{"pig","sheep","chicken","cow","horse","polar_bear","ocelot","parrot","cat","donkey","mooshroom",
						"fox","turtle","rabbit","llama","panda","wolf"};

		for (String string : strings) {
			RegisterHelper.register(new IncubatingEggItem(properties, Registry.ITEM.getValue(new ResourceLocation(string+"_spawn_egg"))
							.orElseThrow(() -> new IllegalArgumentException(string+" not found"))),"incubating_"+string+"_egg",event.getRegistry());
		}
	}
}
