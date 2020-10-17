package tfar.ranchcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;

public abstract class AbstractMachineBlockEntity extends TileEntity implements ITickableTileEntity {

	protected ITextComponent customName;
	protected boolean needsRefresh = true;
	public boolean active = false;
	protected int progress = 0;

	public AbstractMachineBlockEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (needsRefresh) {
				refresh();
				needsRefresh = false;
			}
			if (active) {
				progress--;
				if (progress <= 0) {
					process();
					needsRefresh = true;
				}
			}
		}
	}

	public abstract void refresh();

	public abstract void process();

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		needsRefresh = nbt.getBoolean("needsRefresh");
		active = nbt.getBoolean("active");
		progress = nbt.getInt("progress");
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("needsRefresh", needsRefresh);
		compound.putBoolean("active", active);
		compound.putInt("progress", progress);
		return super.write(compound);
	}
}
