package com.mygdx.mechwargame.core.world.generator.items;

import com.mygdx.mechwargame.core.item.ItemsRegistry;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class MarketItemsDemandGenerator {

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating items in marketplaces";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        Random random = new Random();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                if (sector.sectorOwnerArea.owner != null && !sector.stars.isEmpty()) {

                    Star star = sector.stars.get(0);

                    ItemsRegistry.items.forEach(item -> {

                        float basicValue;

                        int factor = random.nextInt(15);

                        if (factor <= star.wealth) {
                            // bigger demand
                            basicValue = 1 + random.nextInt(3) * 0.05f;
                        } else if (factor >= 10 + star.wealth) {
                            basicValue = 0.9f + random.nextInt(5) * 0.05f;
                        } else {
                            basicValue = 0.8f + random.nextInt(3) * 0.05f;
                        }

                        Float old = sector.sectorOwnerArea.owner.itemsDemand.get(item);
                        if (old == null) {
                            old = basicValue;
                        }

                        sector.sectorOwnerArea.owner.itemsDemand.put(item, (old + basicValue) / 2f);
                    });
                }
            }
        }

        GalaxyGeneratorState.state = "done generating items in marketplaces";
    }

}
