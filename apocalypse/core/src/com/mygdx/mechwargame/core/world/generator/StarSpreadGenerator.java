package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class StarSpreadGenerator {

    public static Random random;

    public static void spread(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "spreading stars across sectors";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                GameData.galaxy.sectors[i][j].stars.forEach(star -> {
                    float x = star.getX();
                    float y = star.getY();

                    star.setPosition(x + 10 - random.nextInt(20), y + 10 - random.nextInt(20));
                });
            }
        }

        GalaxyGeneratorState.state = "done spreading stars across sectors";
    }

}
