package tfar.rcraft.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

public class ModBlockEntities {

	public static void registerBlockEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		RegisterHelper.register(TileEntityType.Builder.create())
	}
}
