package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;

public class MainMenuUIFactory {

    public static Label getSmallTextLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = UIFactoryCommon.fontSmall;
        return new Label(text, labelStyle);
    }

    public static ImageTextButton getMenuButton(final String text) {

        final ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = UIFactoryCommon.fontLarge;

        final ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        final TextureRegionDrawable buttonUp = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class));

        textButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                textButton.getStyle().up = buttonUp;
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                textButton.getStyle().up = null;
            }
        });

        return textButton;
    }

}
