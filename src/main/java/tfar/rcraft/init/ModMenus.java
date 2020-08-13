package tfar.rcraft.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.menus.*;

public class ModMenus {

	public static ContainerType<TannedChestMenu> TANNED_CHEST;
	public static ContainerType<AnglerMenu> ANGLER;
	public static ContainerType<CuddlerMenu> CUDDLER;
	public static ContainerType<IncubatorMenu> INCUBATOR;
	public static ContainerType<TannedSatchelMenu> TANNED_SATCHEL;
	public static ContainerType<TenderizerMenu> TENDERIZER;

	public static void register(RegistryEvent.Register<ContainerType<?>> event) {
		ANGLER = (ContainerType<AnglerMenu>) RegisterHelper.register(new ContainerType<>(AnglerMenu::new),"angler", event.getRegistry());
		CUDDLER = (ContainerType<CuddlerMenu>) RegisterHelper.register(new ContainerType<>(CuddlerMenu::new),"cuddler", event.getRegistry());
		INCUBATOR = (ContainerType<IncubatorMenu>) RegisterHelper.register(new ContainerType<>(IncubatorMenu::new),"incubator", event.getRegistry());
		TANNED_CHEST = (ContainerType<TannedChestMenu>) RegisterHelper.register(new ContainerType<>(TannedChestMenu::new),"tanned_chest", event.getRegistry());
		TANNED_SATCHEL = (ContainerType<TannedSatchelMenu>) RegisterHelper.register(new ContainerType<>(TannedSatchelMenu::new),"tanned_satchel", event.getRegistry());
		TENDERIZER = (ContainerType<TenderizerMenu>) RegisterHelper.register(new ContainerType<>(TenderizerMenu::new),"tenderizer", event.getRegistry());
	}
}
