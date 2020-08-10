package tfar.rcraft.event;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfar.rcraft.init.ModItems;

public class MixinEvents {

	public static void wetLeather(ItemEntity itemEntity) {
		World world = itemEntity.world;
		itemEntity.remove();
		world.addEntity(new ItemEntity(world,itemEntity.getPosX(),itemEntity.getPosY(),itemEntity.getPosZ(),new ItemStack(ModItems.SOAKED_LEATHER,itemEntity.getItem().getCount())));
	}
}
