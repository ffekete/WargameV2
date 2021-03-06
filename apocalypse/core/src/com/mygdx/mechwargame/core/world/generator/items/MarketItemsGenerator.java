package com.mygdx.mechwargame.core.world.generator.items;

import com.mygdx.mechwargame.core.item.consumable.HydrogenCell;
import com.mygdx.mechwargame.core.facility.Marketplace;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.generator.items.ModificationStockGenerator;
import com.mygdx.mechwargame.core.world.generator.items.WeaponStockGenerator;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class MarketItemsGenerator {

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating items in marketplaces";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if(sector.sectorOwnerArea.owner != null) {
                        star.facilities.stream()
                                .filter(f -> f instanceof Marketplace)
                                .map(f -> (Marketplace)f)
                                .forEach(marketplace -> {

                                    for(int k = 0; k < star.wealth + new Random().nextInt(5 * star.wealth); k++) {
                                        marketplace.itemsToSell.add(new HydrogenCell());
                                    }

                                    // weapons
                                    WeaponStockGenerator.generate(star.wealth, marketplace.itemsToSell);
                                    ModificationStockGenerator.generate(star.wealth, marketplace.itemsToSell);
                                });
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating items in marketplaces";
    }

}
