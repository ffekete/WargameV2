package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class GalaxyStarDistributor {

    public static void distributeStars(GalaxySetupParameters galaxySetupParameters) {
        GalaxyGeneratorState.state = "creating stars";

        int nrOfStarsDistributed = 0;
        int neededNrOfStars = galaxySetupParameters.numberOfStars * galaxySetupParameters.numberOfStarsMultiplier;

        while (nrOfStarsDistributed < neededNrOfStars) {
            for (int i = 0; i < galaxySetupParameters.height; i++) {
                for (int j = 0; j < galaxySetupParameters.width; j++) {
                    Star star = new Star();
                    GameData.galaxy.sectors[i][j].stars.add(star);
                    nrOfStarsDistributed++;
                }
            }
        }

        while (nrOfStarsDistributed > neededNrOfStars) {
            // remove from random sectors
            int x = new Random().nextInt(galaxySetupParameters.width);
            int y = new Random().nextInt(galaxySetupParameters.height);

            while (GameData.galaxy.sectors[y][x].stars.isEmpty()) {
                y = new Random().nextInt(galaxySetupParameters.height);
                x = new Random().nextInt(galaxySetupParameters.width);
            }

            int index = GameData.galaxy.sectors[y][x].stars.size();

            GameData.galaxy.sectors[y][x].stars.remove(new Random().nextInt(index));

            nrOfStarsDistributed--;
        }

        GalaxyGeneratorState.state = "done creating stars";
    }

}
