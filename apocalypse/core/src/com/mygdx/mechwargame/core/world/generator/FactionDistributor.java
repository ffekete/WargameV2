package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactionDistributor {

    public static Random random;

    public static void distribute(GalaxySetupParameters galaxySetupParameters) {

        List<Vector2> startingPoints = new ArrayList<>();

        int galaxyWidth = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int galaxyHeight = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < galaxySetupParameters.defaultNumberOfFactions; i++) {
            int x, y;
            do {
                x = random.nextInt(galaxyWidth);
                y = random.nextInt(galaxyHeight);

            } while (startingPoints.contains(new Vector2(x,y)));

            startingPoints.add(new Vector2(x,y));
        }



    }

}
