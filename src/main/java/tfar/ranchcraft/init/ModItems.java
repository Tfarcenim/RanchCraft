package tfar.ranchcraft.init;

import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import tfar.ranchcraft.RanchCraft;
import tfar.ranchcraft.item.*;

import java.util.Arrays;
import java.util.HashSet;

public class ModItems {

    public static Item ANGLER;
    public static Item CAST_IRON_STOVE;
    public static Item CUDDLER;
    public static Item FLY_ROD;
    public static Item INCUBATOR;

    public static Item GOLD_MALLET;
    public static Item IRON_MALLET;
    public static Item STONE_MALLET;
    public static Item DIAMOND_MALLET;
    public static Item NETHERITE_MALLET;
    public static Item WOOD_MALLET;

    public static Item MULTITOOL;
    public static Item SOAKED_LEATHER;
    public static Item TANNED_CHEST;
    public static Item TANNED_LEATHER;
    public static Item TANNED_SATCHEL;
    public static Item TANNING_RACK;
    public static Item TENDERIZER;
    public static Item WHEEL;

    public static Item CORN_SEEDS;
    public static Item CORN_COB;

    public static Item RICE;
    public static Item RICE_SEEDS;

    public static Item CORN_ON_THE_COB;
    public static Item CEREAL;
    public static Item CREAMED_CORN;
    public static Item HAMBURGER;
    public static Item CHICKEN_TACO;
    public static Item ONIGIRI;
    public static Item SUSHI;
    public static Item FRIED_RICE;
    public static Item DIRTY_RICE;
    public static Item HOPPIN_JOHN;
    public static Item ROCK_CANDY;
    public static Item BACON_STRIPS;
    public static Item CHICKEN_NUGGETS;
    public static Item FRIED_EGG;
    public static Item BEEF_JERKY;
    public static Item STEAMED_CARROT;
    public static Item FRUIT_SALAD;
    public static Item HONEY_GLAZED_HAM;
    public static Item CHICKEN_OMLETTE;
    public static Item BIG_BREAKFAST;

    protected static final ItemGroup TAB = new ItemGroup(RanchCraft.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WHEEL);
        }
    };

    public static void register(RegistryEvent.Register<Item> event) {

        ANGLER = RegisterHelper.register(new BlockItem(ModBlocks.ANGLER, new Item.Properties().group(TAB)), "angler", event.getRegistry());
        CAST_IRON_STOVE = RegisterHelper.register(new BlockItem(ModBlocks.CAST_IRON_STOVE, new Item.Properties().group(TAB)), "cast_iron_stove", event.getRegistry());
        CUDDLER = RegisterHelper.register(new BlockItem(ModBlocks.CUDDLER, new Item.Properties().group(TAB)), "cuddler", event.getRegistry());
        FLY_ROD = RegisterHelper.register(new FlyRodItem(new Item.Properties().maxDamage(128).group(TAB)), "fly_rod", event.getRegistry());
        INCUBATOR = RegisterHelper.register(new BlockItem(ModBlocks.INCUBATOR, new Item.Properties().group(TAB)), "incubator", event.getRegistry());
        WOOD_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.DIAMOND,new HashSet<>(),new Item.Properties().group(TAB)), "wood_mallet", event.getRegistry());
        STONE_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.STONE,new HashSet<>(),new Item.Properties().group(TAB)), "stone_mallet", event.getRegistry());
        IRON_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.IRON,new HashSet<>(),new Item.Properties().group(TAB)), "iron_mallet", event.getRegistry());
        GOLD_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.GOLD,new HashSet<>(),new Item.Properties().group(TAB)), "gold_mallet", event.getRegistry());
        DIAMOND_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.DIAMOND,new HashSet<>(),new Item.Properties().group(TAB)), "diamond_mallet", event.getRegistry());
        NETHERITE_MALLET = RegisterHelper.register(new MalletItem(1,1,ItemTier.NETHERITE,new HashSet<>(),new Item.Properties().group(TAB)), "netherite_mallet", event.getRegistry());

        MULTITOOL = RegisterHelper.register(new MultiToolItem(new Item.Properties().group(TAB)), "multitool", event.getRegistry());
        SOAKED_LEATHER = RegisterHelper.register(new Item(new Item.Properties().group(TAB)), "soaked_leather", event.getRegistry());
        TANNED_CHEST = RegisterHelper.register(new BlockItem(ModBlocks.TANNED_CHEST, new Item.Properties().group(TAB)), "tanned_chest", event.getRegistry());
        TANNED_LEATHER = RegisterHelper.register(new Item(new Item.Properties().group(TAB)), "tanned_leather", event.getRegistry());
        TANNED_SATCHEL = RegisterHelper.register(new TannedSatchelItem(new Item.Properties().group(TAB)), "tanned_satchel", event.getRegistry());
        TANNING_RACK = RegisterHelper.register(new BlockItem(ModBlocks.TANNING_RACK, new Item.Properties().group(TAB)), "tanning_rack", event.getRegistry());
        TENDERIZER = RegisterHelper.register(new BlockItem(ModBlocks.TENDERIZER, new Item.Properties().group(TAB)), "tenderizer", event.getRegistry());
        WHEEL = RegisterHelper.register(new Item(new Item.Properties().group(TAB)), "wheel", event.getRegistry());

        Item.Properties properties = new Item.Properties().group(TAB);

        String[] strings = new String[]{"pig", "sheep", "chicken", "cow", "horse", "polar_bear", "ocelot", "parrot", "cat", "donkey", "mooshroom",
                "fox", "turtle", "rabbit", "llama", "panda", "wolf"};

        for (String string : strings) {
            RegisterHelper.register(new IncubatingEggItem(properties, Registry.ITEM.getOptional(new ResourceLocation(string + "_spawn_egg"))
                    .orElseThrow(() -> new IllegalArgumentException(string + " not found"))), "incubating_" + string + "_egg", event.getRegistry());
        }

        CORN_SEEDS = RegisterHelper.register(new BlockNamedItem(ModBlocks.CORN_COB, new Item.Properties().group(TAB)), "corn_seeds", event.getRegistry());
        CORN_COB = RegisterHelper.register(new Item(new Item.Properties().group(TAB)), "corn_cob", event.getRegistry());
        RICE_SEEDS = RegisterHelper.register(new BlockNamedItem(ModBlocks.RICE, new Item.Properties().group(TAB)), "rice_seeds", event.getRegistry());
        RICE = RegisterHelper.register(new Item(new Item.Properties().group(TAB)), "rice", event.getRegistry());

        CORN_ON_THE_COB = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CORN_ON_THE_COB)), "corn_on_the_cob", event.getRegistry());
        CEREAL = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CEREAL)), "cereal", event.getRegistry());
        CREAMED_CORN = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CREAMED_CORN)), "creamed_corn", event.getRegistry());
        HAMBURGER = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.HAMBURGER)), "hamburger", event.getRegistry());
        CHICKEN_TACO = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CHICKEN_TACO)), "chicken_taco", event.getRegistry());
        ONIGIRI = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.ONIGIRI)), "onigiri", event.getRegistry());
        SUSHI = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.SUSHI)), "sushi", event.getRegistry());
        FRIED_RICE = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.FRIED_RICE)), "fried_rice", event.getRegistry());
        DIRTY_RICE = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.DIRTY_RICE)), "dirty_rice", event.getRegistry());
        HOPPIN_JOHN = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.HOPPIN_JOHN)), "hoppin_john", event.getRegistry());
        ROCK_CANDY = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.ROCK_CANDY)), "rock_candy", event.getRegistry());
        BACON_STRIPS = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.BACON_STRIPS)), "bacon_strips", event.getRegistry());
        CHICKEN_NUGGETS = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CHICKEN_NUGGETS)), "chcken_nuggets", event.getRegistry());
        FRIED_EGG = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.FRIED_EGG)), "fried_egg", event.getRegistry());
        BEEF_JERKY = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.BEEF_JERKY)), "beef_jerky", event.getRegistry());
        STEAMED_CARROT = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.STEAMED_CARROT)), "steamed_carrot", event.getRegistry());
        FRUIT_SALAD = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.FRUIT_SALAD)), "fruit_salad", event.getRegistry());
        HONEY_GLAZED_HAM = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.HONEY_GLAZED_HAM)), "honey_glazed_ham", event.getRegistry());
        CHICKEN_OMLETTE = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.CHICKEN_OMLETTE)), "chicken_omlette", event.getRegistry());
        BIG_BREAKFAST = RegisterHelper.register(new Item(new Item.Properties().food(ModFoods.BIG_BREAKFAST)), "big_breakfast", event.getRegistry());
    }

    private static Item[] items;

    public static Item[] getItems() {
        if (items == null) {
            items = Arrays.stream(ModItems.class.getFields()).map(field -> {
                try {
                    return field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).map(Item.class::cast).toArray(Item[]::new);
        }
        return items;
    }
}
