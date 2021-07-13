package com.mygdx.mechwargame.core.world.generator.items;

import com.mygdx.mechwargame.core.facility.Factory;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.core.unit.BlackBear;
import com.mygdx.mechwargame.core.unit.Hellfire;
import com.mygdx.mechwargame.core.unit.Interceptor;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FactoryItemsGenerator {

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        List<Class<? extends BaseUnit>> basicUnits = Arrays.asList(
                BlackBear.class,
                Interceptor.class,
                Hellfire.class
        );

        List<Class<? extends BaseUnit>> advancedUnits = Arrays.asList(

        );

        List<Class<? extends BaseUnit>> rareUnits = Arrays.asList(

        );


        Random random = new Random(galaxySetupParameters.seed);

        GalaxyGeneratorState.state = "generating items in marketplaces";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if (sector.sectorOwnerArea.owner != null) {
                        star.facilities.stream()
                                .filter(f -> f instanceof Factory)
                                .map(f -> (Factory) f)
                                .forEach(factory -> {

                                    // Basic
                                    for (int k = 0; k < star.wealth + new Random().nextInt(5 * star.wealth); k++) {
                                        try {
                                            factory.itemsToSell.add(basicUnits.get(random.nextInt(basicUnits.size())).newInstance());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    // Advanced
                                    if (star.wealth > 2 && !advancedUnits.isEmpty()) {
                                        for (int k = 0; k < 1 + new Random().nextInt(star.wealth); k++) {
                                            try {
                                                factory.itemsToSell.add(advancedUnits.get(random.nextInt(advancedUnits.size())).newInstance());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    // Rare
                                    if (star.wealth > 3 && !advancedUnits.isEmpty()) {
                                        for (int k = 0; k < new Random().nextInt(star.wealth) - 1; k++) {
                                            try {
                                                factory.itemsToSell.add(rareUnits.get(random.nextInt(rareUnits.size())).newInstance());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating items in marketplaces";
    }

}
