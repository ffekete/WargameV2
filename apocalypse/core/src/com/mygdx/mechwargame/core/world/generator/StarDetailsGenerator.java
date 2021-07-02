package com.mygdx.mechwargame.core.world.generator;

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
                        star.population = random.nextInt(100000000) + 2500;
                        star.wealth = random.nextInt(4) + 1;
                        star.nrOfPlanets = random.nextInt(8) + 4;
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
