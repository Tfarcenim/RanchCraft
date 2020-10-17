package tfar.ranchcraft.datagen.assets;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.ranchcraft.RanchCraft;
import tfar.ranchcraft.init.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator,ExistingFileHelper existingFileHelper) {
        super(generator, RanchCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Item item : ModItems.getItems()) {
            String path = item.getRegistryName().getPath();
            try {
                if (item instanceof BlockItem)
                    getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
                else if (item instanceof SpawnEggItem) {
                    getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/template_spawn_egg")));
                } else {
                    getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                            .texture("layer0", modLoc("item/" + path));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
