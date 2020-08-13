package tfar.rcraft.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import tfar.rcraft.blockentity.TanningRackBlockEntity;

public class TanningRackBlockEntityRenderer extends TileEntityRenderer<TanningRackBlockEntity> {

	public TanningRackBlockEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(TanningRackBlockEntity blockEntity, float var2, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int light, int var6) {
		if (this.renderDispatcher.renderInfo != null) {

			if (blockEntity.handler.getStackInSlot(0).isEmpty() && blockEntity.handler.getStackInSlot(1).isEmpty()) return;

			final double spacing = .189;
			final double offset = .31;
			//translate x,y,z
			matrixStack.translate(0, .0625 + 0, 0);
			ItemStack item = blockEntity.handler.getStackInSlot(1);
			if (item.isEmpty())
				item = blockEntity.handler.getStackInSlot(0);

			//pushmatrix
			matrixStack.push();
			//translate x,y,z
			matrixStack.translate(spacing * 0 + offset, 0, spacing * 0 + offset);
			//scale x,y,z
			matrixStack.scale(0.25F, 0.25F, 0.25F);

			Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.FIXED,
							light, OverlayTexture.NO_OVERLAY, matrixStack, iRenderTypeBuffer);
			//popmatrix
			matrixStack.pop();
		}
	}
}