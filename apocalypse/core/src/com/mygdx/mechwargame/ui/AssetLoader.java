package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.Gdx;

public class AssetLoader {

    public static String resolve(String path) {
        if(Gdx.files.external("mod/" + path).exists()) {
            return "mod/" + path;
        }

        return path;
    }

}
