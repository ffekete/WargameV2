package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class StarImageGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        List<Color> colors = Arrays.asList(Color.YELLOW,
                Color.CYAN,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.CORAL,
                Color.GOLD,
                Color.GOLD,
                Color.RED);

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

                        case 3:
                            star.setStarAnimation(AssetManagerV2.STAR_04, 32, 32);
                            star.setBounds(x * SECTOR_SIZE, y * SECTOR_SIZE, 32, 32);
                            break;
                    }

                    star.layeredAnimatedImage.setColor(colors.get(random.nextInt(colors.size())));
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star images";
    }

}
