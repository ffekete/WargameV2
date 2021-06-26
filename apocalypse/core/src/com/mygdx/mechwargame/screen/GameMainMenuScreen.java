package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.ship.StarShip;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.event.galaxyscreen.MapClickEvent;
import com.mygdx.mechwargame.screen.event.galaxyscreen.ScrollEvent;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.state.ScreenState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.awt.*;

public class GameMainMenuScreen extends GenericScreenAdapter {

    private Label starNameLabel;
    private Star selectedStar;

    @Override
    public void show() {
        super.show();

        starNameLabel = UIFactoryCommon.getDynamicTextLabel(() -> selectedStar == null ? "" : selectedStar.name, UIFactoryCommon.fontSmall);

        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                GameData.galaxy.sectors[i][j].stars.forEach(star -> {
                    stage.addActor(star);
                    star.addListener(new InputListener() {
                        @Override
                        public void enter(InputEvent event,
                                          float x,
                                          float y,
                                          int pointer,
                                          Actor fromActor) {
                            selectedStar = star;
                            starNameLabel.setPosition(star.getX() + 20, star.getY() - 20);
                        }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            GameData.starShip.addAction(MapClickEvent.check(x,y));
                            return true;
                        }

                        @Override
                        public void exit(InputEvent event,
                                          float x,
                                          float y,
                                          int pointer,
                                          Actor fromActor) {
                            selectedStar = null;
                        }
                    });
                });
            }
        }

        ScrollController scrollController = new ScrollController(stage);
        stage.addActor(scrollController);

        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameData.starShip.addAction(MapClickEvent.check(x,y));
                return true;
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                System.exit(1);
                return true;
            }

            @Override
            public boolean mouseMoved(InputEvent event,
                                      float x,
                                      float y) {
                return ScrollEvent.check(x,y,stage,scrollController);
            }
        });

        StarShip starShip = new StarShip();
        starShip.setSize(32, 32);
        starShip.setScale(2f);
        starShip.setPosition(100, 100);
        stage.addActor(starShip);
        GameData.starShip = starShip;

        stage.addActor(starNameLabel);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}
