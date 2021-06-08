package com.mygdx.apocalypsegame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class AssetLoader {

    private static final Map<String, Texture> cache = new HashMap<>();

    public static Texture loadImage(String path) {
        if(Gdx.files.external(path).exists()) {
            Texture texture = new Texture(Gdx.files.external("mod/" + path));
            cache.put(path, texture);
            return texture;
        }

        Texture texture = new Texture(Gdx.files.internal(path));
        cache.put(path, texture);
        return texture;
    }

}
