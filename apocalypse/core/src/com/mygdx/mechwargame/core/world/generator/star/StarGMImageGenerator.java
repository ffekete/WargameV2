package com.mygdx.mechwargame.core.world.generator.star;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public class StarGMImageGenerator {

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

                    int index = random.nextInt(4);

                    switch (index) {
                        case 0:
                            star.setStarAnimation(AssetManagerV2.GM_STAR_01, 32, 32);
                            star.setBounds(x * UNIT_SIZE, y * UNIT_SIZE, 32, 32);
                            break;

                        case 1:
                            star.setStarAnimation(AssetManagerV2.GM_STAR_02, 32, 32);
                            star.setBounds(x * UNIT_SIZE, y * UNIT_SIZE, 32, 32);
                            break;

                        case 2:
                            if(star.twin) {
                                star.setStarAnimation(AssetManagerV2.GM_STAR_03, 32, 32);
                            } else {
                                star.setStarAnimation(AssetManagerV2.GM_STAR_02, 32, 32);
                            }
                            star.setBounds(x * UNIT_SIZE, y * UNIT_SIZE, 32, 32);
                            break;

                        case 3:
                            star.setStarAnimation(AssetManagerV2.GM_STAR_04, 32, 32);
                            star.setBounds(x * UNIT_SIZE, y * UNIT_SIZE, 32, 32);
                            break;
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star images";
    }

}
