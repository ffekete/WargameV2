package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.core.starsystem.BlackMarket;
import com.mygdx.mechwargame.core.starsystem.Marketplace;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class StarFacilitiesGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating facilities in star systems";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if(sector.sectorOwnerArea.owner != null) {

                        if(sector.sectorOwnerArea.owner.isPirate) {
                            star.facilities.add(new BlackMarket(Math.max(random.nextInt(4) + 1, star.wealth)));
                        } else {
                            if(star.capitol) {

                            } else {

                            }

                            star.facilities.add(new Marketplace(Math.max(random.nextInt(4) + 1, star.wealth)));
                        }
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating facilities in star systems";
    }

}
