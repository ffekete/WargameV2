package com.mygdx.mechwargame.core.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.state.GameState;

import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public abstract class Item extends Image {

    public String name;
    public int price;

    public Item(String image) {
        super(GameState.assetManager.get(image, Texture.class));
        setSize(UNIT_SIZE, UNIT_SIZE);
    }
}
