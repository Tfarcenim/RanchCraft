package tfar.rcraft.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.blockentity.AnglerBlockEntity;
import tfar.rcraft.blockentity.TenderizerBlockEntity;

public class ModBlockEntities {

	public static TileEntityType<?> ANGLER;
	public static TileEntityType<?> TENDERIZER;

	public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
		ANGLER = RegisterHelper.register(TileEntityType.Builder.create(AnglerBlockEntity::new,ModBlocks.ANGLER).build(null),"angler", event.getRegistry());
		TENDERIZER = RegisterHelper.register(TileEntityType.Builder.create(TenderizerBlockEntity::new,ModBlocks.TENDERIZER).build(null),"tenderizer", event.getRegistry());
	}
}
