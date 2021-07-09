package com.mygdx.mechwargame.core.world.generator.items;

import com.badlogic.gdx.Gdx;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.item.modification.EnhancedSensors;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.item.modification.TargetingModule;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ModificationStockGenerator {

    private static List<Class<? extends Modification>> basicItems = Arrays.asList(
            TargetingModule.class,
            EnhancedSensors.class
    );

    private static List<Class<? extends Modification>> advancedItems = Arrays.asList(

    );

    private static List<Class<? extends Modification>> rareItems = Arrays.asList(

    );

    public static void generate(int level,
                                List<Item> items) {
        Random random = new Random();

        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            Class<? extends Modification> aClass = basicItems.get(random.nextInt(basicItems.size()));
            try {
                items.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                Gdx.app.log("error", "cannot instantiate weapon " + aClass);

            }
        }

        if (level > 1 && !advancedItems.isEmpty()) {
            for (int i = 0; i < random.nextInt(3) + 1; i++) {
                Class<? extends Modification> aClass = advancedItems.get(random.nextInt(advancedItems.size()));
                try {
                    items.add(aClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    Gdx.app.log("error", "cannot instantiate weapon " + aClass);

                }
            }
        }

        if (level > 2 && !rareItems.isEmpty()) {
            for (int i = 0; i < random.nextInt(2); i++) {
                Class<? extends Modification> aClass = rareItems.get(random.nextInt(rareItems.size()));
                try {
                    items.add(aClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    Gdx.app.log("error", "cannot instantiate weapon " + aClass);

                }
            }
        }
    }


}
