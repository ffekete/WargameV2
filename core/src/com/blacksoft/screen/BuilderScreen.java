package com.blacksoft.screen;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.blacksoft.NewGameInitializer;
import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.creature.action.CreatureSalaryAction;
import com.blacksoft.screen.action.MoveLightToMouseAction;
import com.blacksoft.screen.input.MapClickHandler;
import com.blacksoft.screen.input.MapMouseMovedHandler;
import com.blacksoft.screen.render.OrthogonalTiledMapRenderer;
import com.blacksoft.state.GameState;
import com.blacksoft.ui.TileMarker;

import static com.blacksoft.state.Config.SCREEN_HEIGHT;
import static com.blacksoft.state.Config.SCREEN_WIDTH;

public class BuilderScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;
    private RayHandler rayHandler;
    private World world;

    public BuilderScreen(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void show() {
        // CAMERA, VIEWPORT
        GameState.orthographicCamera = new OrthographicCamera();
        GameState.orthographicUICamera = new OrthographicCamera();
        GameState.viewport = new ExtendViewport(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, GameState.orthographicCamera);
        GameState.uiViewport = new FitViewport(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, GameState.orthographicCamera);
        GameState.viewport.apply(true);

        // LIGHT
        world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlur(true);
        RayHandler.useDiffuseLight(true);
        rayHandler.setBlurNum(3);
        rayHandler.setShadows(true);
        GameState.rayHandler = rayHandler;
        GameState.mouseLightSource = new PointLight(rayHandler, 15, new Color(1, 1f, 1f, 1f), 128, 0, 0);

        // MISC
        GameState.tileMarker = new TileMarker();
        GameState.tileMarker.setVisible(false);
        GameState.userAction = UserAction.Clean;

        // STAGE
        GameState.stage = new Stage(GameState.viewport);
        GameState.uiStage = new Stage(GameState.uiViewport);
        GameState.stage.addActor(GameState.tileMarker);

        // INPUT
        GameState.stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                return MapClickHandler.touchDown((int) x, (int) y);
            }

            @Override
            public boolean mouseMoved(InputEvent event,
                                      float x,
                                      float y) {
                return MapMouseMovedHandler.mouseMoved(event, x, y);
            }

            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {
                if (Input.Keys.ESCAPE == keycode) {
                    System.exit(0);
                }
                return true;
            }
        });

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(GameState.uiStage);
        inputMultiplexer.addProcessor(GameState.stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

        GameState.orthographicCamera.translate(-20, -20);

        NewGameInitializer.init();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(GameState.dungeon.tiledMap, spriteBatch);

        CleanIndicatorsAction.cleanAll(GameState.dungeon);
        CleanIndicatorUpdater.update(GameState.dungeon);

        // UI
        GameState.uiStage.addActor(UIFactory.I.getStatusBar());
        GameState.uiStage.addActor(UIFactory.I.getFpsIndicator());
        GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BUILD PHASE"));
        GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BUILD PHASE"));
        GameState.uiStage.addActor(UIFactory.I.getActionsGroup());

        GameState.stage.addAction(new MoveLightToMouseAction());
        GameState.stage.addAction(new CreatureSalaryAction());
    }

    @Override
    public void render(float delta) {
        GameState.stage.act();

        super.render(delta);
        tiledMapRenderer.setView(GameState.orthographicCamera);
        tiledMapRenderer.render();

        GameState.stage.draw();

        rayHandler.setCombinedMatrix(GameState.orthographicCamera);
        rayHandler.updateAndRender();

        GameState.uiStage.act();
        GameState.uiStage.draw();
    }

    @Override
    public void resize(int width,
                       int height) {

        GameState.viewport.update(width, height, false);
        GameState.uiViewport.update(width, height, false);
        GameState.viewport.apply(false);
    }

    @Override
    public void dispose() {
        super.dispose();
        rayHandler.dispose();
        spriteBatch.dispose();
    }
}
