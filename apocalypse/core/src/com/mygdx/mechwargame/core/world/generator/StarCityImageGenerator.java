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
import com.mygdx.mechwargame.core.facility.Marketplace;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StarCityImageGenerator {

    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        List<AnimatedDrawable> buildings = Arrays.asList(
                new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_01, 16, 32, 0.25f),
                new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_02, 16, 32, 0.25f),
                new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_03, 16, 32, 0.25f),
                new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_BUILDING_04, 16, 32, 0.25f)
        );

        random = new Random();

        GalaxyGeneratorState.state = "generating facilities in star systems";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if (sector.sectorOwnerArea.owner != null) {

                        star.cityView.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_BG_02, Texture.class)));

                        for (int k = 0; k < 25; k++) {
                            for (int l = 0; l < 7; l++) {

                                star.cityView.actors[k][l] = new Image(buildings.get(random.nextInt(buildings.size())));
                                star.cityView.actors[k][l].setSize(64, 128);
                                star.cityView.actors[k][l].setTouchable(Touchable.disabled);
                                star.cityView.actors[k][l].setPosition(k * 64, l * 64);
                                star.cityView.actors[k][l].setColor(0.8f / (l / 2f), 0.8f / (l / 2f), 0.8f / (l / 2f), 1f);

                            }
                        }

                        if (star.facilities.stream().anyMatch(f -> f instanceof Marketplace)) {
                            Image marketImage = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_MARKET, 32, 32, 0.2f));
                            star.cityView.actors[5][5] = marketImage;
                            marketImage.setSize(128, 128);
                            marketImage.setPosition(5 * 64, 5 * 64);

                            marketImage.debug();

                            star.cityView.actors[5][6] = null;
                            star.cityView.actors[6][6] = null;
                            star.cityView.actors[6][5] = null;

                            star.cityView.actors[5][4] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_PARK, 16, 32, 0.25f));

                            star.cityView.actors[5][4].setSize(64, 128);
                            star.cityView.actors[5][4].setPosition(5 * 64, 4 * 64);

                            star.cityView.actors[6][4] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_PARK, 16, 32, 0.25f));
                            star.cityView.actors[6][4].setSize(64, 128);
                            star.cityView.actors[6][4].setPosition(6 * 64, 4 * 64);

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
                        }
                        star.cityView.layout();


                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating facilities in star systems";
    }

}
