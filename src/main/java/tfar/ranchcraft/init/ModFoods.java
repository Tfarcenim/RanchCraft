package tfar.ranchcraft.init;

import com.mojang.datafixers.util.Pair;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {

    public static final Food CORN_ON_THE_COB = copyFrom(Foods.APPLE).fastToEat().build();
    public static final Food CEREAL = copyFrom(Foods.MUSHROOM_STEW).build();
    public static final Food CREAMED_CORN = copyFrom(Foods.MUSHROOM_STEW).build();
    public static final Food HAMBURGER = copyFromExcludingEffects(Foods.GOLDEN_APPLE).build();
    public static final Food CHICKEN_TACO = copyFromExcludingEffects(Foods.GOLDEN_APPLE).build();
    public static final Food ONIGIRI = copyFrom(Foods.APPLE).build();
    public static final Food SUSHI = copyFrom(Foods.BAKED_POTATO).build();
    public static final Food FRIED_RICE = copyFrom(Foods.SWEET_BERRIES).build();
    public static final Food DIRTY_RICE = copyFromExcludingEffects(Foods.GOLDEN_APPLE).build();
    public static final Food HOPPIN_JOHN = copyFromExcludingEffects(Foods.GOLDEN_APPLE).build();
    public static final Food ROCK_CANDY = copyFrom(Foods.APPLE).fastToEat().build();
    public static final Food BACON_STRIPS = new Food.Builder().hunger(4).saturation(0.2F).meat().build();
    public static final Food CHICKEN_NUGGETS = copyFrom(Foods.APPLE).fastToEat().meat().build();
    public static final Food FRIED_EGG = copyFrom(Foods.APPLE).fastToEat().build();
    public static final Food BEEF_JERKY = new Food.Builder().hunger(4).saturation(0.4F).fastToEat().meat().build();
    public static final Food STEAMED_CARROT = copyFrom(Foods.BAKED_POTATO).build();
    public static final Food FRUIT_SALAD = copyFrom(Foods.BAKED_POTATO).build();
    public static final Food HONEY_GLAZED_HAM = copyFromExcludingEffects(Foods.GOLDEN_APPLE).build();
    public static final Food CHICKEN_OMLETTE = new Food.Builder().hunger(10).saturation(1F).meat().build();
    public static final Food BIG_BREAKFAST = new Food.Builder().hunger(20).saturation(1)
            .effect(new EffectInstance(Effects.SATURATION,1200,0),1).build();

    public static Food.Builder copyFromExcludingEffects(Food food) {
        Food.Builder foodBuilder = new Food.Builder();
        foodBuilder.hunger(food.getHealing()).saturation(food.getSaturation());
        if (food.isMeat()) {
            foodBuilder.meat();
        }
        if (food.canEatWhenFull()) {
            foodBuilder.setAlwaysEdible();
        }
        if (food.isFastEating()) {
            foodBuilder.fastToEat();
        }
        return foodBuilder;
    }

    public static Food.Builder copyFrom(Food food) {
        Food.Builder foodBuilder = copyFromExcludingEffects(food);
        for (Pair<EffectInstance, Float> effect : food.getEffects()) {
            foodBuilder.effect(effect::getFirst,effect.getSecond());
        }
        return foodBuilder;
    }
}
