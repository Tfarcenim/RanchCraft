package tfar.ranchcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import tfar.ranchcraft.datagen.assets.ModItemModelProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenStart {

    @SubscribeEvent
    public static void startGathering(GatherDataEvent e) {
        DataGenerator dataGen = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();
        if (e.includeClient()) {
            dataGen.addProvider(new ModItemModelProvider(dataGen,helper));
        }
    }
}
