package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StarBackgroundImageGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        GalaxyGeneratorState.state = "generating star background images";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        Pixmap pixmap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x00000000);
        pixmap.fill();

        pixmap.setBlending(Pixmap.Blending.SourceOver);

        Map<String, Texture> textures = new HashMap<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {

                    // pick background
                    List<String> stars = star.twin ? GameState.assetManager.twinStars : GameState.assetManager.stars;

                    String starKey = stars.get(random.nextInt(stars.size()));

                    textures.computeIfAbsent(starKey, v -> GameState.assetManager.get(starKey, Texture.class));
                    Texture bg = textures.get(starKey);

                    bg.getTextureData().prepare();
                    pixmap.drawPixmap(bg.getTextureData().consumePixmap(), 0, 0);

                    // pick decoration
                    String decorationKey = GameState.assetManager.decorations.get(random.nextInt(GameState.assetManager.decorations.size()));
                    textures.computeIfAbsent(decorationKey, v -> GameState.assetManager.get(decorationKey, Texture.class));
                    Texture decoration = textures.get(decorationKey);

                    decoration.getTextureData().prepare();
                    pixmap.drawPixmap(decoration.getTextureData().consumePixmap(), 0, 0);

                    // pick planet
                    String planetKey = GameState.assetManager.planets.get(random.nextInt(GameState.assetManager.planets.size()));
                    textures.computeIfAbsent(planetKey, v -> GameState.assetManager.get(planetKey, Texture.class));
                    Texture planet = textures.get(planetKey);

                    planet.getTextureData().prepare();
                    pixmap.drawPixmap(planet.getTextureData().consumePixmap(), 0, 0);

                    if (sector.sectorOwnerArea.owner != null) {
                        // pick station
                        String stationKey;
                        if(sector.sectorOwnerArea.owner.isPirate) {
                            stationKey = AssetManagerV2.PIRATE_STATION_001;
                        } else {
                            if (star.capitol) {
                                stationKey = AssetManagerV2.CAPITOL_STATION_001;
                            } else {
                                stationKey = GameState.assetManager.stations.get(random.nextInt(GameState.assetManager.stations.size()));
                            }
                        }

                        textures.computeIfAbsent(stationKey, v -> GameState.assetManager.get(stationKey, Texture.class));
                        Texture station = textures.get(stationKey);
                        station.getTextureData().prepare();
                        pixmap.drawPixmap(station.getTextureData().consumePixmap(), 0, 0);
                    }

                    star.background = new Texture(pixmap);
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star background images";
    }
}
