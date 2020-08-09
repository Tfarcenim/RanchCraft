package tfar.rcraft.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.block.AnglerBlock;
import tfar.rcraft.block.ContainerBlock;
import tfar.rcraft.block.TenderizerBlock;
import tfar.rcraft.blockentity.AnglerBlockEntity;
import tfar.rcraft.blockentity.CuddlerBlockEntity;
import tfar.rcraft.blockentity.TenderizerBlockEntity;

public class ModBlocks {

	public static Block ANGLER;
	public static Block TENDERIZER;
	public static Block CUDDLER;

	public static void register(RegistryEvent.Register<Block> e) {
		ANGLER = RegisterHelper.register(new AnglerBlock(AbstractBlock.Properties.from(Blocks.CHEST), AnglerBlockEntity::new),"angler",e.getRegistry());
		TENDERIZER = RegisterHelper.register(new TenderizerBlock(AbstractBlock.Properties.from(Blocks.CHEST), TenderizerBlockEntity::new),"tenderizer",e.getRegistry());
		CUDDLER = RegisterHelper.register(new ContainerBlock(AbstractBlock.Properties.from(Blocks.CHEST), CuddlerBlockEntity::new),"cuddler",e.getRegistry());
	}
}
