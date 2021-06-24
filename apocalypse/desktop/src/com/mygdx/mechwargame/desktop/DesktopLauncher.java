package com.mygdx.mechwargame.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.mechwargame.MechWarGame;
import com.mygdx.mechwargame.state.GameState;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		//config.fullscreen = true;
		GameState.game = new MechWarGame();

		new LwjglApplication(GameState.game, config);
	}
}
