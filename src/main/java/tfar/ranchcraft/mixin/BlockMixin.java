package tfar.ranchcraft.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.ranchcraft.event.MixinEvents;

import java.util.List;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "getDrops(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/tileentity/TileEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;"
    ,at = @At("RETURN"))
    private static void cornSeeds(BlockState state, ServerWorld worldIn, BlockPos pos, TileEntity tileEntityIn, Entity entityIn, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        MixinEvents.onBlockDrops(state,worldIn,pos,tileEntityIn,entityIn,stack,cir.getReturnValue());
    }
}
