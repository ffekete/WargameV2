package com.mygdx.mechwargame.core.world.generator.items;

import com.mygdx.mechwargame.core.item.consumable.HydrogenCell;
import com.mygdx.mechwargame.core.facility.BlackMarket;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.generator.items.ModificationStockGenerator;
import com.mygdx.mechwargame.core.world.generator.items.WeaponStockGenerator;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;

import java.util.Random;

public class BlackMarketItemsGenerator {

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating items in black markets";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if(sector.sectorOwnerArea.owner != null) {
                        star.facilities.stream()
                                .filter(f -> f instanceof BlackMarket)
                                .map(f -> (BlackMarket)f)
                                .forEach(blackMarket -> {

                                    // hydrogen cell
                                    for(int k = 0; k < new Random().nextInt(star.wealth + 1); k++) {
                                        blackMarket.itemsToSell.add(new HydrogenCell());
                                    }

                                    // weapons
                                    WeaponStockGenerator.generate(star.wealth + 1, blackMarket.itemsToSell);
                                    ModificationStockGenerator.generate(star.wealth + 1, blackMarket.itemsToSell);
                                });
                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating items in black markets";
    }

}
