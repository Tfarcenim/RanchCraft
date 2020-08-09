package tfar.rcraft.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.rcraft.item.FlyRodItem;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

	public boolean isFlyRod = false;

	@ModifyArg(method = "catchingFish",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;nextInt(Ljava/util/Random;II)I",ordinal = 2),index = 1)
	private int modifyWait( int old) {
		if (isFlyRod)
		return old - 100;
		return old;
	}

	@Inject(method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;II)V",at = @At("RETURN"))
	private void addCheck(PlayerEntity player, World p_i50220_2_, int p_i50220_3_, int p_i50220_4_, CallbackInfo ci) {
		if (player.getHeldItemMainhand().getItem() instanceof FlyRodItem) {
			isFlyRod = true;
		}
	}
}
