package com.mygdx.mechwargame.screen.galaxy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Company;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.core.world.Nebulae;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.ScrollController;
import com.mygdx.mechwargame.screen.action.LockGameStageAction;
import com.mygdx.mechwargame.screen.action.MainAction;
import com.mygdx.mechwargame.screen.galaxy.inputevent.*;
import com.mygdx.mechwargame.screen.galaxy.inputevent.cargo.CargoClickEvent;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.state.KeyMapping;
import com.mygdx.mechwargame.state.SaveLoadManager;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.DynamicProgressBar;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mygdx.mechwargame.Config.UNIT_SIZE;
import static com.mygdx.mechwargame.util.ScreenUtils.repositionToScreenIfNotInFrustum;

public class GalaxyViewScreen extends GenericScreenAdapter {

    public boolean needsReloading = true;

    private Label starNameLabel;
    private Label pausedLabel;
    private Star selectedStar;
    private LayeredAnimatedImage targetMarker;

    private Stage uiStage;
    private Viewport uiViewPort;

    private boolean firstRun = true;

    @Override
    public void show() {
        super.show();

        if (needsReloading) {
            targetMarker = new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.TARGET_MARKER, 32, 32, false, 0.05f) {
                @Override
                public void draw(Batch batch,
                                 float x,
                                 float y,
                                 float width,
                                 float height) {
                    super.draw(batch, x, y, width, height);
                }
            });

            List<TextureRegionDrawable> bgs = Arrays.asList(
                    new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.GALAXY_TILE_BG_01, Texture.class)),
                    new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.GALAXY_TILE_BG_02, Texture.class)),
                    new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.GALAXY_TILE_BG_03, Texture.class)),
                    new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.GALAXY_TILE_BG_04, Texture.class)),
                    new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.GALAXY_TILE_BG_05, Texture.class))
            );

            GameData.bgImages = new Image[GameData.galaxy.width][GameData.galaxy.height];

            for (int i = 0; i < GameData.galaxy.width; i++) {
                for (int j = 0; j < GameData.galaxy.height; j++) {

                    Image image = new Image();

                    GameData.bgImages[i][j] = image;

                    image.setDrawable(bgs.get(new Random().nextInt(bgs.size())));
                    stage.addActor(image);
                    image.toBack();
                    image.setPosition(i * 128, j * 128);
                    image.setSize(128, 128);
                    image.setTouchable(Touchable.disabled);
                }
            }

            stage.addActor(targetMarker);
            targetMarker.setSize(128, 128);
            targetMarker.setVisible(true);

            uiViewPort = new FitViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
            uiStage = new Stage(uiViewPort);

            if (Config.SHOW_FPS) {
                Label fpsLabel = UIFactoryCommon.getDynamicTextLabel(() -> Float.toString(Gdx.graphics.getFramesPerSecond()));
                fpsLabel.setPosition(10, Config.SCREEN_HEIGHT - 50);
                uiStage.addActor(fpsLabel);
            }

            Table menuTable = new Table();
            uiStage.addActor(menuTable);

            Label moneyLabel = UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(Company.money));
            uiStage.addActor(moneyLabel);
            GameState.moneyLabel = moneyLabel;
            moneyLabel.setPosition(10, Config.SCREEN_HEIGHT - 50);

            menuTable.setPosition(10, 10);
            menuTable.setSize(6 * 64 + 5 * 10, 64);

            ImageTextButton mainMenuButton = UIFactoryCommon.getSmallRoundButton("m");
            mainMenuButton.setSize(64, 64);
            menuTable.add(mainMenuButton)
                    .size(64)
                    .padRight(10);

            ImageTextButton questsButton = UIFactoryCommon.getSmallRoundButton("q");
            questsButton.setSize(64, 64);
            menuTable.add(questsButton)
                    .size(64)
                    .padRight(10);

            ImageTextButton squadButton = UIFactoryCommon.getSmallRoundButton("s");
            squadButton.setSize(64, 64);
            menuTable.add(squadButton)
                    .size(64)
                    .padRight(10);

            ImageTextButton cargoButton = UIFactoryCommon.getSmallRoundButton("c");
            cargoButton.setSize(64, 64);
            menuTable.add(cargoButton)
                    .size(64)
                    .padRight(10);

            ImageTextButton hangarButton = UIFactoryCommon.getSmallRoundButton("h");
            hangarButton.setSize(64, 64);
            menuTable.add(hangarButton)
                    .size(64)
                    .padRight(10);

            ImageTextButton shipInfoButton = UIFactoryCommon.getSmallRoundButton("i");
            shipInfoButton.setSize(64, 64);

            shipInfoButton.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         int button) {
                    event.stop();
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);
                    event.stop();
                    showShipInfoLocalMenu();
                }
            });

            cargoButton.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         int button) {
                    event.stop();
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);
                    event.stop();
                    showCargoLocalMenu();
                }
            });

            hangarButton.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         int button) {
                    event.stop();
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);
                    event.stop();
                    showHangarLocalMenu();
                }
            });

            menuTable.add(shipInfoButton)
                    .size(64);

            uiStage.addActor(menuTable);

            pausedLabel = UIFactoryCommon.getDynamicTextLabel(() -> GameData.isPaused ? "paused" : "");
            stage.addActor(pausedLabel);

            starNameLabel = UIFactoryCommon.getDynamicTextLabel(() -> selectedStar == null ? "" : selectedStar.name, UIFactoryCommon.fontSmall);
            starNameLabel.setTouchable(Touchable.disabled);
            starNameLabel.setSize(300, 80);
            starNameLabel.setColor(Color.WHITE);

            List<String> nebulas = Arrays.asList(
                    AssetManagerV2.NEBULA_01,
                    AssetManagerV2.NEBULA_02,
                    AssetManagerV2.NEBULA_03
            );

            for (int i = 0; i < (GameData.galaxy.width - 3); i+=4) {
                for (int j = 0; j < (GameData.galaxy.height - 3); j+=4) {

                    if(new Random().nextInt(6) == 0) {
                        Nebulae nebulae = new Nebulae(nebulas.get(new Random().nextInt(nebulas.size())));

                        nebulae.setSize(128 * 4, 128 * 4);
                        nebulae.setPosition(i * UNIT_SIZE,
                                j * UNIT_SIZE
                        );

                        nebulae.setRotation(new Random().nextInt(4) * 90);

                        stage.addActor(nebulae);

                        nebulae.toBack();
                    }
                }
            }

            for (int i = 0; i < GameData.galaxy.width; i++) {
                for (int j = 0; j < GameData.galaxy.height; j++) {

                    Sector sector = GameData.galaxy.sectors[i][j];

                    GameData.galaxy.sectors[i][j].stars.forEach(star -> {
                        stage.addActor(star);
                        star.setSize(UNIT_SIZE, UNIT_SIZE);

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

                                if (GameData.lockGameStage) {
                                    super.touchDown(event, x, y, pointer, button);
                                    event.stop();
                                    return true;
                                }

                                MainAction sequenceAction = new MainAction();
                                GameData.starShip.addAction(sequenceAction);
                                Vector2 stageCoord = new Vector2(star.getX() + UNIT_SIZE / 2f, star.getY() + UNIT_SIZE / 2f);

                                showTargetMarker(new Vector2(star.getX(), star.getY()));

                                MapClickEvent.check(sequenceAction, stageCoord.x, stageCoord.y, stage);
                                StarClickEvent.handle(sequenceAction, star, sector, uiStage, stageCoord.x, stageCoord.y);
                                sequenceAction.addAction(new LockGameStageAction(true));

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

                    if (GameData.lockGameStage) {
                        super.touchDown(event, x, y, pointer, button);
                        event.stop();
                        return true;
                    }

                    MainAction sequenceAction = new MainAction();
                    MapClickEvent.check(sequenceAction, x, y, stage);
                    GameData.starShip.addAction(sequenceAction);
                    showTargetMarker(new Vector2(x - 64, y - 64));
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
                        if (!GameData.lockGameStage) {
                            GameData.isPaused = !GameData.isPaused;
                        }
                    }

                    if (KeyMapping.SAVE == keycode) {
                        SaveLoadManager.save();
                    }

                    if (KeyMapping.SHIP_INFO == keycode) {
                        showShipInfoLocalMenu();
                    }

                    if (KeyMapping.CARGO_MENU == keycode) {
                        showCargoLocalMenu();
                    }

                    if (KeyMapping.HANGAR_MENU == keycode) {
                        showHangarLocalMenu();
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

            BaseShip starShip = GameData.starShip;

            starShip.setSize(UNIT_SIZE, UNIT_SIZE);

            DynamicProgressBar fuelProgressBar = UIFactoryCommon.createProgressBar(128,
                    16,
                    () -> starShip.engine.fuel,
                    () -> starShip.engine.maxFuel,
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
                    () -> (float) starShip.hullArmor.armor,
                    () -> (float) starShip.hullArmor.maxArmor,
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
                        startingPoints.add(new Vector2(i * UNIT_SIZE, j * UNIT_SIZE));
                    }
                }
            }

            Vector2 startingPoint = startingPoints.get(new Random().nextInt(startingPoints.size()));
            starShip.setPosition(startingPoint.x, startingPoint.y);
            starShip.setTouchable(Touchable.disabled);

            stage.addActor(starShip);
            GameData.starShip = starShip;

            stage.addActor(starNameLabel);

            this.needsReloading = false;
        }

        this.firstRun = true;

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void showHangarLocalMenu() {
        if (GameData.hangarViewWindow == null) {

            hideAllMenus();

            GameData.isPaused = true;
            SequenceAction sequenceAction = new SequenceAction();
            MechBayClickEvent.handle(sequenceAction, uiStage);
            sequenceAction.addAction(new LockGameStageAction(true));
            uiStage.addAction(sequenceAction);
        } else {
            GameData.hangarViewWindow.hide(uiStage);
        }
    }

    private void showCargoLocalMenu() {
        if (GameData.cargoViewWindow == null) {

            hideAllMenus();

            GameData.isPaused = true;
            SequenceAction sequenceAction = new SequenceAction();
            CargoClickEvent.handle(sequenceAction, uiStage);
            sequenceAction.addAction(new LockGameStageAction(true));
            uiStage.addAction(sequenceAction);
        } else {
            GameData.cargoViewWindow.hide(uiStage);
        }
    }

    private void showShipInfoLocalMenu() {

        if (GameData.shipInfoView == null) {

            hideAllMenus();

            GameData.isPaused = true;
            SequenceAction sequenceAction = new SequenceAction();
            ShipClickEvent.handle(sequenceAction, uiStage);
            sequenceAction.addAction(new LockGameStageAction(true));
            uiStage.addAction(sequenceAction);
        } else {
            GameData.shipInfoView.hide(uiStage);
        }
    }

    private void hideAllMenus() {

        if (GameData.hangarViewWindow != null) {
            GameData.hangarViewWindow.hide(uiStage);
        }

        if (GameData.cargoViewWindow != null) {
            GameData.cargoViewWindow.hide(uiStage);
        }

        if (GameData.shipInfoView != null) {
            GameData.shipInfoView.hide(uiStage);
        }
    }

    private void showTargetMarker(Vector2 stageCoord) {
        targetMarker.setVisible(true);
        targetMarker.resetAnimations();
        targetMarker.setPosition(stageCoord.x, stageCoord.y);
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
                if (stage.getViewport().getCamera().frustum.pointInFrustum(i * UNIT_SIZE + UNIT_SIZE, j * UNIT_SIZE + UNIT_SIZE, 0) ||
                        stage.getViewport().getCamera().frustum.pointInFrustum(i * UNIT_SIZE, j * UNIT_SIZE, 0)) {
                    spriteBatch.draw(GameData.galaxy.sectors[i][j].background, i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }
            }
        }

        spriteBatch.setColor(Color.valueOf("ffffffff"));

        // ownership
        for (int i = 0; i < GameData.galaxy.width; i++) {
            for (int j = 0; j < GameData.galaxy.height; j++) {
                if (stage.getViewport().getCamera().frustum.pointInFrustum(i * UNIT_SIZE + UNIT_SIZE, j * UNIT_SIZE + UNIT_SIZE, 0) ||
                        stage.getViewport().getCamera().frustum.pointInFrustum(i * UNIT_SIZE, j * UNIT_SIZE, 0)) {

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
