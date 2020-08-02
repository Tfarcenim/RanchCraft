package tfar.rcraft.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import tfar.rcraft.block.AnglerBlock;

public class ModBlocks {

	public static Block ANGLER;

	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		ANGLER = RegisterHelper.register(new AnglerBlock(AbstractBlock.Properties.from(Blocks.CHEST)),"angler",e.getRegistry());
	}
}
