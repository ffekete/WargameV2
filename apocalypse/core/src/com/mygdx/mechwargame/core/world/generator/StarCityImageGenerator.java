package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.facility.Marketplace;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.generator.util.CellAlgorithm;
import com.mygdx.mechwargame.screen.view.CityView;
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

        List<Supplier<AnimatedDrawable>> decoration = Arrays.asList(
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_01, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f),
                () -> new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_03, 16, 32, 0.25f)
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

                        star.cityView.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_02, Texture.class)));

                        int[][] mapTemplate = new CellAlgorithm(random).create(6 - star.wealth, Config.CITY_WIDTH, Config.CITY_HEIGHT);

                        for (int k = 0; k < Config.CITY_WIDTH; k++) {
                            for (int l = 0; l < Config.CITY_HEIGHT; l++) {


                                if(mapTemplate[k][l] == 1) {
                                    star.cityView.actors[k][l] = new Image(buildings.get(random.nextInt(buildings.size())).get());
                                    star.cityView.actors[k][l].setSize(64, 128);
                                    star.cityView.actors[k][l].setTouchable(Touchable.disabled);
                                    star.cityView.actors[k][l].setPosition(k * 64, l * 64);
                                    star.cityView.actors[k][l].setColor(0.8f / (l / 2f), 0.8f / (l / 2f), 0.8f / (l / 2f), 1f);
                                } else {
                                    star.cityView.actors[k][l] = new Image(decoration.get(random.nextInt(decoration.size())).get());
                                    star.cityView.actors[k][l].setSize(64, 128);
                                    star.cityView.actors[k][l].setTouchable(Touchable.disabled);
                                    star.cityView.actors[k][l].setPosition(k * 64, l * 64);
                                    star.cityView.actors[k][l].setColor(0.8f / (l / 2f), 0.8f / (l / 2f), 0.8f / (l / 2f), 1f);
                                }
                            }
                        }

                        star.facilities.stream().filter(f -> f instanceof Marketplace).forEach(f -> {

                            Image marketImage = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_MARKET, 32, 32, 0.2f));
                            star.cityView.actors[5][5] = marketImage;
                            marketImage.setSize(128, 128);
                            marketImage.setPosition(5 * 64, 5 * 64);

                            f.actor = marketImage;

                            // market
                            addBuilding(star, 5, 5);

                            marketImage.addListener(new InputListener() {

                                @Override
                                public void enter(InputEvent event,
                                                  float x,
                                                  float y,
                                                  int pointer,
                                                  Actor fromActor) {
                                    super.enter(event, x, y, pointer, fromActor);
                                    ParallelAction parallelAction = new ParallelAction();
                                    parallelAction.addAction(Actions.sizeTo(136, 136, 0.1f));
                                    parallelAction.addAction(Actions.moveTo(5 * 64 - 4, 5 * 64 - 4, 0.1f));
                                    marketImage.addAction(parallelAction);
                                }

                                @Override
                                public void exit(InputEvent event,
                                                 float x,
                                                 float y,
                                                 int pointer,
                                                 Actor toActor) {
                                    super.exit(event, x, y, pointer, toActor);
                                    ParallelAction parallelAction = new ParallelAction();
                                    parallelAction.addAction(Actions.sizeTo(128, 128, 0.1f));
                                    parallelAction.addAction(Actions.moveTo(5 * 64, 5 * 64, 0.1f));
                                    marketImage.addAction(parallelAction);
                                }
                            });
                        });
                        star.cityView.layout();


                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating city views in star systems";
    }

    private static void addBuilding(com.mygdx.mechwargame.core.world.Star star,
                                    int x,
                                    int y) {
        star.cityView.actors[x][y + 1] = null;
        star.cityView.actors[x + 1][y + 1] = null;
        star.cityView.actors[x + 1][y] = null;

        star.cityView.actors[x][y - 1] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f));

        star.cityView.actors[x][y - 1].setSize(64, 128);
        star.cityView.actors[x][y - 1].setPosition((x) * 64, (y - 1) * 64);

        star.cityView.actors[x + 1][y - 1] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f));
        star.cityView.actors[x + 1][y - 1].setSize(64, 128);
        star.cityView.actors[x + 1][y - 1].setPosition((x + 1) * 64, (y - 1) * 64);

        star.cityView.actors[x + 1][y - 1].setTouchable(Touchable.disabled);
        star.cityView.actors[x][y - 1].setTouchable(Touchable.disabled);

        star.cityView.actors[x + 1][y - 1].setColor(0.8f / (y / 2f), 0.8f / (y / 2f), 0.8f / (y / 2f), 1f);
        star.cityView.actors[x][y - 1].setColor(0.8f / (y / 2f), 0.8f / (y / 2f), 0.8f / (y / 2f), 1f);
    }

}
