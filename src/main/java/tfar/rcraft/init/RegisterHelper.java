package tfar.rcraft.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tfar.rcraft.RanchCraft;

public class RegisterHelper {

	static <T extends IForgeRegistryEntry<T>> T register(T obj, String name, IForgeRegistry<T> registry) {
		registry.register(obj.setRegistryName(new ResourceLocation(RanchCraft.MODID, name)));
		return obj;
	}
}
