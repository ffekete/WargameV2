package com.mygdx.apocalypsegame;

import com.badlogic.gdx.Game;
import com.mygdx.apocalypsegame.screen.MainMenuScreen;

public class ApocalypseGame extends Game {

    @Override
    public void create() {
        setScreen(MainMenuScreen.SCREEN);
    }
}
