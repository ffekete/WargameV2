package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class StarImageGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating star images";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int x = i;
                int y = j;

                GameData.galaxy.sectors[i][j].stars.forEach(star -> {

                    int index = random.nextInt(3);

                    switch (index) {
                        case 0:
                            star.setStarAnimation(AssetManagerV2.STAR_01, 32, 32);
                            star.setBounds(x * SECTOR_SIZE, y * SECTOR_SIZE, 32, 32);
                            break;

                        case 1:
                            star.setStarAnimation(AssetManagerV2.STAR_02, 32, 32);
                            star.setBounds(x * SECTOR_SIZE, y * SECTOR_SIZE, 32, 32);
                            break;

                        case 2:
                            star.setStarAnimation(AssetManagerV2.STAR_03, 32, 32);
                            star.setBounds(x * SECTOR_SIZE, y * SECTOR_SIZE, 32, 32);
                            break;
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star images";
    }

}
