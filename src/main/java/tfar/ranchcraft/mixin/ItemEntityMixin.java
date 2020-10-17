package tfar.ranchcraft.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.ranchcraft.event.MixinEvents;

@Mixin(ItemEntity.class)
abstract class ItemEntityMixin extends Entity {
	@Shadow public abstract ItemStack getItem();

	public ItemEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(method = "tick",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/item/ItemEntity;applyFloatMotion()V"))
	public void wetItem(CallbackInfo ci) {
		if (this.getItem().getItem() == Items.LEATHER) {
			MixinEvents.wetLeather((ItemEntity)(Object)this);
		}
	}
}
