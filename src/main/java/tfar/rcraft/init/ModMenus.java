package tfar.rcraft.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.menus.AnglerMenu;
import tfar.rcraft.menus.TenderizerMenu;

public class ModMenus {

	public static ContainerType<TenderizerMenu> TENDERIZER;
	public static ContainerType<AnglerMenu> ANGLER;

	public static void register(RegistryEvent.Register<ContainerType<?>> event) {
		ANGLER = (ContainerType<AnglerMenu>) RegisterHelper.register(new ContainerType<>(AnglerMenu::new),"angler", event.getRegistry());
		TENDERIZER = (ContainerType<TenderizerMenu>) RegisterHelper.register(new ContainerType<>(TenderizerMenu::new),"tnderizer", event.getRegistry());
	}
}
