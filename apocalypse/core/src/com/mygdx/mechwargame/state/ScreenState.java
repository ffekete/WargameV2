package com.mygdx.mechwargame.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mechwargame.Config;

public class ScreenState {

    public static OrthographicCamera orthographicCamera;
    public static Viewport viewport;

    static {
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, orthographicCamera);
    }

}
