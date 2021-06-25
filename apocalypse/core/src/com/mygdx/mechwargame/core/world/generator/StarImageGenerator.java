package com.mygdx.mechwargame.core.world.generator;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StarImageGenerator {

    private static final List<String> starImages = Arrays.asList(
            AssetManagerV2.STAR_SMALL,
            AssetManagerV2.STAR_MEDIUM,
            AssetManagerV2.STAR_LARGE
    );

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating star images";

        for (int i = 0; i < galaxySetupParameters.height; i++) {
            for (int j = 0; j < galaxySetupParameters.width; j++) {
                GameData.galaxy.sectors[i][j].stars.forEach(star -> {

                    int index = new Random().nextInt(3);

                    switch (index) {
                        case 0:
                            star.setStarAnimation(AssetManagerV2.STAR_SMALL, 4, 4);
                            break;
                        case 1:
                            star.setStarAnimation(AssetManagerV2.STAR_MEDIUM, 20, 20);
                            break;

                        case 2:
                            star.setStarAnimation(AssetManagerV2.STAR_LARGE, 28, 28);
                            break;
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star images";
    }

}
