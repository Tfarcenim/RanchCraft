package tfar.rcraft.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tfar.rcraft.RanchCraft;
import tfar.rcraft.menu.TenderizerMenu;

public class TenderizerScreen extends ContainerScreen<TenderizerMenu> {

	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(RanchCraft.MODID,"textures/gui/angler.png");

	public TenderizerScreen(TenderizerMenu screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.ySize += 2 * 18;
		this.playerInventoryTitleY = this.ySize - 92;
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.func_230459_a_(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
		//this.blit(matrixStack, i, j + 3 * 18 + 17, 0, 126, this.xSize, 96);
	}
}
