package com.mygdx.mechwargame;

import com.badlogic.gdx.Game;
import com.mygdx.mechwargame.screen.MainMenuScreen;

public class MechWarGame extends Game {

    @Override
    public void create() {
        setScreen(MainMenuScreen.SCREEN);
    }
}
