package tfar.ranchcraft.item;

import net.minecraft.item.Item;

public class IncubatingEggItem extends Item {

	public final Item spawnEgg;

	public IncubatingEggItem(Properties properties, Item spawnEgg) {
		super(properties);
		this.spawnEgg = spawnEgg;
	}
}
