package tfar.rcraft.block;

import net.minecraft.tileentity.TileEntity;

import java.util.function.Supplier;

public class AnglerBlock extends ContainerBlock {

	public AnglerBlock(Properties properties, Supplier<TileEntity> blockEntitySupplier) {
		super(properties, blockEntitySupplier);
	}
}
