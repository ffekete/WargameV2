package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;

import java.util.HashMap;
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

                int x = i;
                int y = j;

                GameData.galaxy.sectors[i][j].stars.forEach(star -> {

                    // pick background
                    String starKey = GameState.assetManager.stars.get(random.nextInt(GameState.assetManager.stars.size()));

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

                    // pick planet
                    String stationKey = GameState.assetManager.stations.get(random.nextInt(GameState.assetManager.stations.size()));
                    textures.computeIfAbsent(stationKey, v -> GameState.assetManager.get(stationKey, Texture.class));
                    Texture station = textures.get(stationKey);

                    station.getTextureData().prepare();
                    pixmap.drawPixmap(station.getTextureData().consumePixmap(), 0, 0);

                    star.background = new Texture(pixmap);
                });
            }
        }

        GalaxyGeneratorState.state = "done generating star background images";
    }
}
