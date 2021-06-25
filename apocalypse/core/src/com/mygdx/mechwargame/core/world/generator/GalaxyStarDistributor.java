package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class GalaxyStarDistributor {

    public static Random random;

    public static void distributeStars(GalaxySetupParameters galaxySetupParameters) {
        GalaxyGeneratorState.state = "creating stars";

        int nrOfStarsDistributed = 0;
        int neededNrOfStars = galaxySetupParameters.numberOfStars * galaxySetupParameters.numberOfStarsMultiplier;
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        while (nrOfStarsDistributed < neededNrOfStars) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Star star = new Star();
                    star.name = "Star-" + nrOfStarsDistributed;
                    GameData.galaxy.sectors[i][j].stars.add(star);
                    nrOfStarsDistributed++;
                }
            }
        }

        while (nrOfStarsDistributed > neededNrOfStars) {
            // remove from random sectors
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            while (GameData.galaxy.sectors[x][y].stars.isEmpty()) {
                y = random.nextInt(height);
                x = random.nextInt(width);
            }

            int index = GameData.galaxy.sectors[x][y].stars.size();

            GameData.galaxy.sectors[x][y].stars.remove(random.nextInt(index));

            nrOfStarsDistributed--;
        }

        GalaxyGeneratorState.state = "done creating stars";
    }

}
