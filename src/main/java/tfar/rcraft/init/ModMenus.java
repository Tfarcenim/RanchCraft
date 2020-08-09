package tfar.rcraft.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.menus.AnglerMenu;
import tfar.rcraft.menus.CuddlerMenu;
import tfar.rcraft.menus.TenderizerMenu;

public class ModMenus {

	public static ContainerType<AnglerMenu> ANGLER;
	public static ContainerType<CuddlerMenu> CUDDLER;
	public static ContainerType<TenderizerMenu> TENDERIZER;

	public static void register(RegistryEvent.Register<ContainerType<?>> event) {
		ANGLER = (ContainerType<AnglerMenu>) RegisterHelper.register(new ContainerType<>(AnglerMenu::new),"angler", event.getRegistry());
		CUDDLER = (ContainerType<CuddlerMenu>) RegisterHelper.register(new ContainerType<>(CuddlerMenu::new),"cuddler", event.getRegistry());
		TENDERIZER = (ContainerType<TenderizerMenu>) RegisterHelper.register(new ContainerType<>(TenderizerMenu::new),"tenderizer", event.getRegistry());
	}
}
