package com.mygdx.mechwargame.state;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;

public class GameState {

    public static final String VERSION = "1.0.0";

    public static ScreenAdapter galaxyViewScreen;
    public static Game game;
    public static AssetManagerV2 assetManager = new AssetManagerV2();

    public static GenericScreenAdapter previousScreen;


}
