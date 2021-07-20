package com.mygdx.mechwargame.core.world.generator.star;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.generator.star.image.FactoryImageGenerator;
import com.mygdx.mechwargame.core.world.generator.star.image.MarketImageGenerator;
import com.mygdx.mechwargame.core.world.generator.util.CellAlgorithm;
import com.mygdx.mechwargame.screen.view.star.CityView;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class StarCityImageGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        random = new Random();

        List<Supplier<AnimatedDrawable>> buildings = Arrays.asList(
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_01, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_02, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_03, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_04, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_05, 16, 32, true, 1f, 60f + random.nextFloat() * 60)
        );

        List<Supplier<AnimatedDrawable>> decoration = getDecorationSuppliers();


        List<Supplier<TextureRegionDrawable>> backGrounds = Arrays.asList(
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_01, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_02, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_03, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_04, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_05, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_06, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_07, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_08, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_09, Texture.class)),
                () -> new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_10, Texture.class))
        );

        GalaxyGeneratorState.state = "generating city views in star systems";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if (sector.sectorOwnerArea.owner != null && !sector.sectorOwnerArea.owner.isPirate) {

                        star.cityView = new CityView();

                        star.cityView.background(backGrounds.get(random.nextInt(backGrounds.size())).get());

                        int[][] mapTemplate = new CellAlgorithm(random).create(6 - star.wealth, Config.CITY_WIDTH, Config.CITY_HEIGHT);

                        for (int k = 0; k < Config.CITY_WIDTH; k++) {
                            for (int l = 0; l < Config.CITY_HEIGHT; l++) {


                                if (mapTemplate[k][l] == 1) {
                                    star.cityView.actors[k][l] = new Image(buildings.get(random.nextInt(buildings.size())).get());
                                    star.cityView.actors[k][l].setSize(64, 128);
                                    star.cityView.actors[k][l].setTouchable(Touchable.disabled);
                                    star.cityView.actors[k][l].setPosition(k * 64, l * 64);
                                    //star.cityView.actors[k][l].setColor(0.8f / (l / 2f), 0.8f / (l / 2f), 0.8f / (l / 2f), 1f);
                                } else {
                                    star.cityView.actors[k][l] = new Image(decoration.get(random.nextInt(decoration.size())).get());
                                    star.cityView.actors[k][l].setSize(64, 128);
                                    star.cityView.actors[k][l].setTouchable(Touchable.disabled);
                                    star.cityView.actors[k][l].setPosition(k * 64, l * 64);
                                    //star.cityView.actors[k][l].setColor(0.8f / (l / 2f), 0.8f / (l / 2f), 0.8f / (l / 2f), 1f);
                                }
                            }
                        }

                        MarketImageGenerator.generateImage(star);
                        FactoryImageGenerator.generateImage(star);
                        star.cityView.layout();


                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating city views in star systems";
    }

    public static List<Supplier<AnimatedDrawable>> getDecorationSuppliers() {
        List<Supplier<AnimatedDrawable>> decoration = Arrays.asList(
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_01, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_03, 16, 32, 0.25f)
        );
        return decoration;
    }

}
