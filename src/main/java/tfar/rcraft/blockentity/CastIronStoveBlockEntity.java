package tfar.rcraft.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import tfar.rcraft.init.ModBlockEntities;
import tfar.rcraft.menu.CastIronStoveMenu;
import tfar.rcraft.util.Constants;

import javax.annotation.Nullable;

public class CastIronStoveBlockEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider, INameable {

    protected ITextComponent customName;
    protected AbstractCookingRecipe recipe;

    protected boolean needsRefresh = true;
    public boolean active = false;
    protected int progress = 0;
    public boolean isFood;

    public final ItemStackHandler handler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
            if (slot != 1 || remainingBurnTime <= 0) {
				needsRefresh = true;
			}
        }
    };

    public CastIronStoveBlockEntity() {
        super(ModBlockEntities.CAST_IRON_STOVE);
    }

    public void refresh() {
        if (canProcess()) {
            if (!active) {
                start();
            }
        } else {
            active = false;
        }
    }

    public void start() {
        this.processTime = this.progress = recipe.getCookTime();
        ItemStack fuel = handler.getStackInSlot(1);
        if (remainingBurnTime <= 0 && !fuel.isEmpty()) {
            active = true;
            burnFuel();
        }
    }

    public void burnFuel() {
        ItemStack fuel = handler.getStackInSlot(1);
        totalFuelBurnTime = remainingBurnTime = ForgeHooks.getBurnTime(fuel);
        fuel.shrink(1);
    }

    public boolean canProcess() {
        ItemStack stack0 = handler.getStackInSlot(0);
        if (stack0.isEmpty()) return false;
        ItemStack output = handler.getStackInSlot(2);

        if (recipe == null) {
            recipe = world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new RecipeWrapper(handler), world).orElse(null);
        } else {
            if (!recipe.matches(new RecipeWrapper(handler),world)) {
                recipe = world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new RecipeWrapper(handler), world).orElse(null);
            }
        }

        if (recipe == null) return false;
        isFood = recipe.getCraftingResult(null).isFood();
        if (output.isEmpty()) {
            return true;
        } else {
            //probably a bad idea
            return ItemHandlerHelper.canItemStacksStack(recipe.getCraftingResult(null), output);
        }
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
                if (isFood)
                    progress--;
                if (progress <= 0) {
                    process();
                    needsRefresh = true;
                }
                if (remainingBurnTime <= 0) {

                    ItemStack fuel = handler.getStackInSlot(1);
                    if (fuel.isEmpty()) {
                        active = false;
                        progress = processTime;
                    } else {
                        burnFuel();
                    }
                }
            }
            if (remainingBurnTime > 0)
                remainingBurnTime--;
        }
    }

    public void process() {
        handler.getStackInSlot(0).shrink(1);
        ItemStack stack = recipe.getCraftingResult(null).copy();
        ItemStack output = handler.getStackInSlot(2);
        if (!output.isEmpty()) {
            output.grow(1);
        } else {
            handler.setStackInSlot(2, stack);
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        handler.deserializeNBT(nbt.getCompound(Constants.INV));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put(Constants.INV, handler.serializeNBT());
        compound.putInt("");
        return super.write(compound);
    }

    public void setCustomName(ITextComponent text) {
        this.customName = text;
    }

    @Override
    public ITextComponent getName() {
        return customName != null ? customName : getDefaultName();
    }

    ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.rcraft.cast_iron_stove");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return customName;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new CastIronStoveMenu(p_createMenu_1_, p_createMenu_2_, handler, furnaceData);
    }

    private int remainingBurnTime;
    private int totalFuelBurnTime;
    protected int processTime;
    protected final IIntArray furnaceData = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return remainingBurnTime;
                case 1:
                    return totalFuelBurnTime;
                case 2:
                    return progress;
                case 3:
                    return processTime;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    remainingBurnTime = value;
                    break;
                case 1:
                    totalFuelBurnTime = value;
                    break;
                case 2:
                    progress = value;
                    break;
                case 3:
                    processTime = value;
            }

        }

        public int size() {
            return 4;
        }
    };

}
