package tfar.rcraft.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.blockentity.AnglerBlockEntity;
import tfar.rcraft.blockentity.CuddlerBlockEntity;
import tfar.rcraft.blockentity.TenderizerBlockEntity;
import tfar.rcraft.menus.CuddlerMenu;

public class ModBlockEntities {

	public static TileEntityType<?> ANGLER;
	public static TileEntityType<?> CUDDLER;
	public static TileEntityType<?> TENDERIZER;

	public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
		ANGLER = RegisterHelper.register(TileEntityType.Builder.create(AnglerBlockEntity::new,ModBlocks.ANGLER).build(null),"angler", event.getRegistry());
		CUDDLER = RegisterHelper.register(TileEntityType.Builder.create(CuddlerBlockEntity::new,ModBlocks.CUDDLER).build(null),"cuddler", event.getRegistry());
		TENDERIZER = RegisterHelper.register(TileEntityType.Builder.create(TenderizerBlockEntity::new,ModBlocks.TENDERIZER).build(null),"tenderizer", event.getRegistry());
	}
}
