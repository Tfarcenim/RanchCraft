package tfar.rcraft;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfar.rcraft.client.screen.AnglerScreen;
import tfar.rcraft.client.screen.CuddlerScreen;
import tfar.rcraft.client.screen.IncubatorScreen;
import tfar.rcraft.client.screen.TenderizerScreen;
import tfar.rcraft.init.*;
import tfar.rcraft.menus.TenderizerMenu;

@Mod(RanchCraft.MODID)
public class RanchCraft {
    public static final String MODID = "rcraft";

    public RanchCraft() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addGenericListener(Block.class, ModBlocks::register);
        bus.addGenericListener(Item.class, ModItems::register);
        bus.addGenericListener(ContainerType.class, ModMenus::register);
        bus.addGenericListener(EntityType.class, ModEntities::register);
        bus.addGenericListener(TileEntityType.class, ModBlockEntities::register);

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModMenus.ANGLER, AnglerScreen::new);
        ScreenManager.registerFactory(ModMenus.CUDDLER, CuddlerScreen::new);
        ScreenManager.registerFactory(ModMenus.INCUBATOR, IncubatorScreen::new);
        ScreenManager.registerFactory(ModMenus.TENDERIZER, TenderizerScreen::new);

    }
}
