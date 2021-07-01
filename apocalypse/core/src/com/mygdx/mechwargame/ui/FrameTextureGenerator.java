package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class FrameTextureGenerator {

    public static Texture generate(int width,
                                   int height,
                                   Color color) {

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x00000000);
        pixmap.fill();

        pixmap.setBlending(Pixmap.Blending.SourceOver);

        addPixel(pixmap, 0, 8, 4, color);
        addPixel(pixmap, 4, 4, 4, color);

        addPixel(pixmap, 0, height - 12, 4, color);
        addPixel(pixmap, 4, height - 8, 4, color);

        for (int i = 2; i < width / 4 - 2; i++) {
            addPixel(pixmap, i * 4, 0, 4, color);
            addPixel(pixmap, i * 4, height - 4, 4, color);
        }

        for (int i = 2; i < height / 4 -2; i++) {
            addPixel(pixmap, 0, i * 4, 4, color);
            addPixel(pixmap, width - 4, i * 4, 4, color);
        }

        addPixel(pixmap, width - 8, 4, 4, color);
        addPixel(pixmap, width - 4, 8, 4, color);

        addPixel(pixmap, width - 8, height - 8, 4, color);
        addPixel(pixmap, width - 4, height - 12, 4, color);

        return new Texture(pixmap);
    }

    private static void addPixel(Pixmap pixmap,
                                 int x,
                                 int y,
                                 int size,
                                 Color color) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pixmap.drawPixel(x + i, y + j, color.toIntBits());
            }
        }
    }

}
