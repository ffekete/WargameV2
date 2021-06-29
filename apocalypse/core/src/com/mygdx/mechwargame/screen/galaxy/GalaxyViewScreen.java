package com.mygdx.mechwargame.screen.galaxy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.core.ship.StarShip;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.ScrollController;
import com.mygdx.mechwargame.screen.action.MainAction;
import com.mygdx.mechwargame.screen.event.galaxyscreen.MapClickEvent;
import com.mygdx.mechwargame.screen.event.galaxyscreen.ScrollEvent;
import com.mygdx.mechwargame.screen.event.galaxyscreen.StarClickEvent;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.KeyMapping;
import com.mygdx.mechwargame.ui.DynamicProgressBar;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;
import static com.mygdx.mechwargame.util.ScreenUtils.repositionToScreenIfNotInFrustum;

public class GalaxyViewScreen extends GenericScreenAdapter {

    private Label starNameLabel;
    private Label pausedLabel;
    private Star selectedStar;

    private Stage uiStage;
    private Viewport uiViewPort;

    private boolean firstRun = true;

    @Override
    public void show() {
        super.show();

        uiViewPort = new FitViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        uiStage = new Stage(uiViewPort);

        pausedLabel = UIFactoryCommon.getDynamicTextLabel(() -> GameData.isPaused ? "paused" : "");
        stage.addActor(pausedLabel);

        starNameLabel = UIFactoryCommon.getDynamicTextLabel(() -> selectedStar == null ? "" : selectedStar.name, UIFactoryCommon.fontSmall);
        starNameLabel.setTouchable(Touchable.disabled);
        starNameLabel.setSize(300, 80);
        starNameLabel.setColor(Color.WHITE);

        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                GameData.galaxy.sectors[i][j].stars.forEach(star -> {
                    stage.addActor(star);
                    star.setSize(SECTOR_SIZE, SECTOR_SIZE);

                    star.addListener(new InputListener() {
                        @Override
                        public void enter(InputEvent event,
                                          float x,
                                          float y,
                                          int pointer,
                                          Actor fromActor) {
                            selectedStar = star;
                            starNameLabel.setPosition(star.getX() + 20, star.getY() - 20);
                            repositionToScreenIfNotInFrustum(stage.getCamera(), starNameLabel);
                        }

                        @Override
                        public boolean touchDown(InputEvent event,
                                                 float x,
                                                 float y,
                                                 int pointer,
                                                 int button) {

                            clearStarLocalTable();

                            MainAction sequenceAction = new MainAction();
                            GameData.starShip.addAction(sequenceAction);

                            //Vector2 stageCoord = star.localToStageCoordinates(new Vector2(star.getX(), star.getY()));
                            Vector2 stageCoord = new Vector2(star.getX() + 64, star.getY() + 64);
                            MapClickEvent.check(sequenceAction, stageCoord.x, stageCoord.y);
                            StarClickEvent.handle(sequenceAction, star, stage);

                            event.stop();
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
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                clearStarLocalTable();
                MainAction sequenceAction = new MainAction();
                MapClickEvent.check(sequenceAction, x, y);
                GameData.starShip.addAction(sequenceAction);
                event.stop();
                return true;
            }

            @Override
            public boolean keyDown(InputEvent event,
                                   int keycode) {
                if (Input.Keys.ESCAPE == keycode) {
                    System.exit(1);
                }

                if (KeyMapping.UNPAUSE == keycode) {
                    GameData.isPaused = !GameData.isPaused;
                }

                return true;
            }

            @Override
            public boolean mouseMoved(InputEvent event,
                                      float x,
                                      float y) {
                return ScrollEvent.check(x, y, stage, scrollController);
            }
        });

        BaseShip starShip = new StarShip();
        starShip.setSize(SECTOR_SIZE, SECTOR_SIZE);

        DynamicProgressBar fuelProgressBar = UIFactoryCommon.createProgressBar(128,
                16,
                () -> starShip.fuel,
                () -> starShip.maxFuel,
                Color.DARK_GRAY,
                Color.LIGHT_GRAY);

        stage.addActor(fuelProgressBar);
        fuelProgressBar.setSize(128, 16);
        fuelProgressBar.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                fuelProgressBar.setPosition(starShip.getX(), starShip.getY() + 136);
                return false;
            }
        });

        DynamicProgressBar hullProgressBar = UIFactoryCommon.createProgressBar(128,
                16,
                () -> (float) starShip.hull,
                () -> (float) starShip.maxHull,
                Color.SCARLET,
                Color.CHARTREUSE);

        stage.addActor(hullProgressBar);
        hullProgressBar.setSize(128, 16);
        hullProgressBar.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                hullProgressBar.setPosition(starShip.getX(), starShip.getY() + 128);
                return false;
            }
        });

        List<Vector2> startingPoints = new ArrayList<>();

        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                if (GameData.galaxy.sectors[i][j].sectorOwnerArea.owner != null && !GameData.galaxy.sectors[i][j].stars.isEmpty()) {
                    startingPoints.add(new Vector2(i * SECTOR_SIZE, j * SECTOR_SIZE));
                }
            }
        }

        Vector2 startingPoint = startingPoints.get(new Random().nextInt(startingPoints.size()));
        starShip.setPosition(startingPoint.x, startingPoint.y);
        starShip.setTouchable(Touchable.disabled);

        stage.addActor(starShip);
        GameData.starShip = starShip;

        stage.addActor(starNameLabel);
        Gdx.input.setInputProcessor(stage);
    }

    private void clearStarLocalTable() {
        if (GameData.starLocalMenu != null) {
            stage.getActors().removeValue(GameData.starLocalMenu, true);
            GameData.starLocalMenu = null;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // center the camera once
        if (firstRun) {
            stage.getViewport().getCamera().position.x = GameData.starShip.getX();
            stage.getViewport().getCamera().position.y = GameData.starShip.getY();
            firstRun = false;
        }

        Batch spriteBatch = stage.getBatch();

        spriteBatch.begin();
        // sector boundaries
        spriteBatch.setColor(Color.valueOf("ffffff0A"));

        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                if (stage.getViewport().getCamera().frustum.pointInFrustum(i * SECTOR_SIZE + SECTOR_SIZE, j * SECTOR_SIZE + SECTOR_SIZE, 0) ||
                        stage.getViewport().getCamera().frustum.pointInFrustum(i * SECTOR_SIZE, j * SECTOR_SIZE, 0)) {
                    spriteBatch.draw(GameData.galaxy.sectors[i][j].background, i * SECTOR_SIZE, j * SECTOR_SIZE, SECTOR_SIZE, SECTOR_SIZE);
                }
            }
        }

        spriteBatch.setColor(Color.valueOf("ffffffff"));

        // ownership
        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                if (stage.getViewport().getCamera().frustum.pointInFrustum(i * SECTOR_SIZE + SECTOR_SIZE, j * SECTOR_SIZE + SECTOR_SIZE, 0) ||
                        stage.getViewport().getCamera().frustum.pointInFrustum(i * SECTOR_SIZE, j * SECTOR_SIZE, 0)) {

                    // change color alpha
                    if (GameData.galaxy.sectors[i][j].sectorOwnerArea.owner != null) {
                        Color color = GameData.galaxy.sectors[i][j].sectorOwnerArea.owner.color;
                        color.a = 0.1f;
                        spriteBatch.setColor(color);
                    }

                    GameData.galaxy.sectors[i][j].sectorOwnerArea.draw((SpriteBatch) spriteBatch);

                    // restore color alpha
                    if (GameData.galaxy.sectors[i][j].sectorOwnerArea.owner != null) {
                        Color color = GameData.galaxy.sectors[i][j].sectorOwnerArea.owner.color;
                        color.a = 1f;
                        spriteBatch.setColor(color);
                    }
                }
            }
        }

        spriteBatch.end();
        spriteBatch.setColor(Color.valueOf("FFFFFFFF"));

        pausedLabel.setPosition(stage.getCamera().position.x, stage.getCamera().position.y);

        stage.act();
        stage.draw();

        uiStage.getViewport().apply(true);
        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void resize(int width,
                       int height) {
        super.resize(width, height);
        uiStage.getViewport().update(width, height, true);
    }
}