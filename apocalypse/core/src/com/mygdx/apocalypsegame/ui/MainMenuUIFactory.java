package com.mygdx.apocalypsegame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuUIFactory {

    public static ImageTextButton getMenuButton(String text) {

        ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = UIFactoryCommon.fontLarge;

        ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        textButton.setBackground(new TextureRegionDrawable(AssetLoader.loadImage("mainmenu/MainMenuButtonBg.png")));

        return textButton;
    }

}
