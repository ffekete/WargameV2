package com.mygdx.mechwargame.core.world.generator.star;

import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class StarDetailsGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating star system details";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if(sector.sectorOwnerArea.owner != null) {

                        if(sector.sectorOwnerArea.owner.isPirate) {
                            star.population = random.nextInt(300) + 100;
                            star.wealth = random.nextInt(2) + 1;
                            star.nrOfPlanets = random.nextInt(2) + 1;
                        } else {
                            if(star.capitol) {
                                star.population = random.nextInt(100000000) + 25000000;
                                star.wealth = random.nextInt(3) + 2;
                            } else {
                                star.population = random.nextInt(1000000) + 2500;
                                star.wealth = random.nextInt(4) + 1;
                            }
                            star.nrOfPlanets = random.nextInt(8) + 4;
                        }
                    } else {
                        star.nrOfPlanets = random.nextInt(14);
                    }
                    star.twin = random.nextInt(20) == 0;

                });
            }
        }

        GalaxyGeneratorState.state = "done generating star system details";
    }

}
