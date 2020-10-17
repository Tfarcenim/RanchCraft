package tfar.ranchcraft.datagen.assets;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.ranchcraft.RanchCraft;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RanchCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
