package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class AnimatedImageButton extends ImageButton {

    Drawable up;
    Drawable down;

    public AnimatedImageButton(ImageButtonStyle imageButtonStyle) {
        super(imageButtonStyle);
        up = imageButtonStyle.up;
        down = imageButtonStyle.down;

        up.setMinWidth(32);
        up.setMinHeight(32);

        down.setMinWidth(32);
        down.setMinHeight(32);
    }

    @Override
    protected void updateImage() {
        super.updateImage();
        getImage().setSize(32, 32);
        getImage().layout();
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
