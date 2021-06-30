package com.mygdx.mechwargame.core;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mechwargame.AssetManagerV2;

public class MovementPathEffect extends Actor {

    private int size = 128;

    private Texture startTexture = new Texture(AssetManagerV2.MOVEMENT_PATH_BEGIN);
    private Texture middleTexture = new Texture(AssetManagerV2.MOVEMENT_PATH_MID);
    private Texture endTexture = new Texture(AssetManagerV2.MOVEMENT_PATH_END);

    private Texture sprite;

    public MovementPathEffect(int length) {

        int parts = length / 32;

        Pixmap pixmap = new Pixmap(length + size / 2, size, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x00000000);
        pixmap.fill();

        pixmap.setBlending(Pixmap.Blending.SourceOver);

        startTexture.getTextureData().prepare();
        pixmap.drawPixmap(startTexture.getTextureData().consumePixmap(), 0, 0);

        int i;
        for (i = 1; i <= parts; i++) {
            middleTexture.getTextureData().prepare();
            pixmap.drawPixmap(middleTexture.getTextureData().consumePixmap(), i * size, 0);
        }

        int remainder = length - i * size;

        middleTexture.getTextureData().prepare();
        pixmap.drawPixmap(middleTexture.getTextureData().consumePixmap(), i * size, 0, 0, 0, remainder, size);

        sprite = new Texture(pixmap);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {

        batch.draw(sprite, getX() - size / 2f, getY() - size / 2f, size / 2f, size / 2f, sprite.getWidth(), sprite.getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, sprite.getWidth(), sprite.getHeight(), false, false);
    }
}
