package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;

public class UIFactoryCommon {

    public static BitmapFont fontSmall;
    public static BitmapFont fontMedium;
    public static BitmapFont fontLarge;

    static {

        fontSmall = getFont("font/font_small.fnt");
        fontMedium = getFont("font/font_med.fnt");
        fontLarge = getFont("font/font_huge.fnt");
    }

    private static BitmapFont getFont(String fontPath) {

        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal(fontPath));

        return bitmapFont;
    }

    public static Label getTextLabel(String text, BitmapFont bitmapFont, Color color) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = color;
        return new Label(text, labelStyle);
    }

    public static Label getTextLabel(String text, BitmapFont bitmapFont) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        return new Label(text, labelStyle);
    }

    public static Container<TextField> getTextField(String message, String text, BitmapFont bitmapFont) {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = bitmapFont;
        textFieldStyle.cursor = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.TEXT_CURSOR, Texture.class));
        textFieldStyle.messageFont = bitmapFont;
        textFieldStyle.fontColor = Color.GREEN;
        textFieldStyle.messageFontColor = Color.GREEN;
        TextField textField = new TextField(text, textFieldStyle);
        textField.setMessageText(message);

        final Container<TextField> inputContainer = UIFactoryCommon.getInputContainer(textField);
        inputContainer.pad(2,20, 2, 20);
        inputContainer.fill();

        return inputContainer;
    }

    public static <T extends Actor> Container<T> getInputContainer(T t) {
        Container<T> container = new Container<>(t);
        container.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class)));
        return container;
    }

}
