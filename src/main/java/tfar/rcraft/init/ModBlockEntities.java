package tfar.rcraft.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.blockentity.*;

public class ModBlockEntities {

	public static TileEntityType<?> ANGLER;
	public static TileEntityType<?> CUDDLER;
	public static TileEntityType<?> INCUBATOR;
	public static TileEntityType<? extends TannedChestBlockEntity> TANNED_CHEST;
	public static TileEntityType<?> TANNING_RACK;
	public static TileEntityType<?> TENDERIZER;

	public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
		ANGLER = RegisterHelper.register(TileEntityType.Builder.create(AnglerBlockEntity::new,ModBlocks.ANGLER).build(null),"angler", event.getRegistry());
		CUDDLER = RegisterHelper.register(TileEntityType.Builder.create(CuddlerBlockEntity::new,ModBlocks.CUDDLER).build(null),"cuddler", event.getRegistry());
		INCUBATOR = RegisterHelper.register(TileEntityType.Builder.create(IncubatorBlockEntity::new,ModBlocks.INCUBATOR).build(null),"incubator", event.getRegistry());
		TANNED_CHEST = (TileEntityType<? extends TannedChestBlockEntity>) RegisterHelper.register(TileEntityType.Builder.create(TannedChestBlockEntity::new,ModBlocks.TANNING_RACK).build(null),"tanned_chest", event.getRegistry());
		TANNING_RACK = RegisterHelper.register(TileEntityType.Builder.create(TanningRackBlockEntity::new,ModBlocks.TANNING_RACK).build(null),"tanning_rack", event.getRegistry());
		TENDERIZER = RegisterHelper.register(TileEntityType.Builder.create(TenderizerBlockEntity::new,ModBlocks.TENDERIZER).build(null),"tenderizer", event.getRegistry());
	}
}
