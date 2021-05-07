package com.blacksoft.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blacksoft.DungeonBuilder;
import com.blacksoft.state.Config;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Config.SCREEN_WIDTH;
        config.height = Config.SCREEN_HEIGHT;
        new LwjglApplication(new DungeonBuilder(), config);
    }
}
