package com.mygdx.apocalypsegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.apocalypsegame.ApocalypseGame;
import com.mygdx.apocalypsegame.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.fullscreen = true;

		new LwjglApplication(new ApocalypseGame(), config);
	}
}
