package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.facility.BlackMarket;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

import java.util.Random;

public class StarAsteroidImageGenerator {

    private static final int BLACK_MARKET_X = 8;
    private static final int BLACK_MARKET_Y = 3;
    public static Random random;

    public static void generate(GalaxySetupParameters galaxySetupParameters) {

        random = new Random();

        GalaxyGeneratorState.state = "generating asteroid views in star systems";
        int width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Sector sector = GameData.galaxy.sectors[i][j];

                sector.stars.forEach(star -> {
                    if (sector.sectorOwnerArea.owner != null && sector.sectorOwnerArea.owner.isPirate) {

                        star.cityView.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_ASTEROID_BG_01, Texture.class)));

                        star.facilities.stream().filter(f -> f instanceof BlackMarket).forEach(f -> {

                            Image marketImage = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_ASTEROID_BLACK_MARKET, 32, 32, 0.2f));
                            star.cityView.actors[BLACK_MARKET_X][BLACK_MARKET_Y] = marketImage;
                            marketImage.setSize(128, 128);
                            marketImage.setPosition(BLACK_MARKET_X * 64, BLACK_MARKET_Y * 64);

                            f.actor = marketImage;

                            // market
                            clearAroundBuilding(star, BLACK_MARKET_X, BLACK_MARKET_Y);

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
                                    parallelAction.addAction(Actions.moveTo(BLACK_MARKET_X * 64 - 4, BLACK_MARKET_Y * 64 - 4, 0.1f));
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
                                    parallelAction.addAction(Actions.moveTo(BLACK_MARKET_X * 64, BLACK_MARKET_Y * 64, 0.1f));
                                    marketImage.addAction(parallelAction);
                                }
                            });
                        });
                        star.cityView.layout();


                    }
                });
            }
        }

        GalaxyGeneratorState.state = "done generating asteroid views in star systems";
    }

    private static void clearAroundBuilding(com.mygdx.mechwargame.core.world.Star star,
                                            int x,
                                            int y) {
        star.cityView.actors[x][y + 1] = null;
        star.cityView.actors[x + 1][y + 1] = null;
        star.cityView.actors[x + 1][y] = null;

    }

}
