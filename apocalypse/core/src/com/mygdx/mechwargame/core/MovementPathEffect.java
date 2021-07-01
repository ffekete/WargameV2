package com.mygdx.mechwargame.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mechwargame.AssetManagerV2;

public class MovementPathEffect extends Actor {

    private int size = 128;

    private Texture middleTexture = new Texture(AssetManagerV2.MOVEMENT_PATH_MID);
    private Texture endTexture = new Texture(AssetManagerV2.MOVEMENT_PATH_END);

    private Texture sprite;

    public int fullLength;

    public int srcX = 0;
    public int srcY = 0;

    public MovementPathEffect(int length) {

        int parts = length / size;

        fullLength = length + size;

        Pixmap pixmap = new Pixmap(fullLength, size, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x00000000);
        pixmap.fill();

        pixmap.setBlending(Pixmap.Blending.SourceOver);

        endTexture.getTextureData().prepare();
        pixmap.drawPixmap(endTexture.getTextureData().consumePixmap(), length, 0, 0, 0, size, size);

        int remainder = length - parts * size;

        int i;
        for (i = 0; i < parts; i++) {
            middleTexture.getTextureData().prepare();
            pixmap.drawPixmap(middleTexture.getTextureData().consumePixmap(), remainder + i * size, 0);
        }

        sprite = new Texture(pixmap);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {

        Pixmap pixmap = new Pixmap(fullLength, size, Pixmap.Format.RGBA8888);
        pixmap.drawPixmap(sprite.getTextureData().consumePixmap(), srcX, 0, srcX, 0, sprite.getWidth(), sprite.getHeight());

        Texture texture = new Texture(pixmap);

        batch.setColor(Color.valueOf("00ff00AA"));
        batch.draw(texture, getX() - size / 2f, getY() - size / 2f, size / 2f, size / 2f, sprite.getWidth(), sprite.getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, sprite.getWidth(), sprite.getHeight(), false, false);
        batch.setColor(Color.WHITE);
    }
}
