package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;

import java.util.function.Supplier;

public class UIFactoryCommon {

    public static BitmapFont fontSmall;
    public static BitmapFont fontMedium;
    public static BitmapFont fontLarge;

    static {

        fontSmall = getFont("font/font_small.fnt");
        fontMedium = getFont("font/font_med.fnt");
        fontLarge = getFont("font/font_huge.fnt");
    }

    public static Table getPowerGauge(String text,
                                      int max,
                                      int actual) {
        Table table = new Table();

        table.add(UIFactoryCommon.getTextLabel(text, fontSmall)).padRight(5).left().width(400);

        for (int i = 0; i < actual; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_FULL, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        for (int i = actual; i < max; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_EMPTY, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        table.add().width(260 - max * (32 + 5));

        return table;
    }

    private static BitmapFont getFont(String fontPath) {

        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal(fontPath));

        return bitmapFont;
    }

    public static Label getTextLabel(String text,
                                     BitmapFont bitmapFont,
                                     Color color) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = color;
        return new Label(text, labelStyle);
    }

    public static Label getTextLabel(String text,
                                     BitmapFont bitmapFont) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        return new Label(text, labelStyle);
    }

    public static Label getTextLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontMedium;
        return new Label(text, labelStyle);
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontMedium;
        return new DynamicTextLabel("", labelStyle, textSource);
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource,
                                            BitmapFont bitmapFont) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        return new DynamicTextLabel("", labelStyle, textSource);
    }

    public static Container<TextField> getTextField(String message,
                                                    String text,
                                                    BitmapFont bitmapFont) {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = bitmapFont;
        textFieldStyle.cursor = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.TEXT_CURSOR, Texture.class));
        textFieldStyle.messageFont = bitmapFont;
        textFieldStyle.fontColor = Color.GREEN;
        textFieldStyle.messageFontColor = Color.GREEN;
        TextField textField = new TextField(text, textFieldStyle);
        textField.setMessageText(message);

        final Container<TextField> inputContainer = UIFactoryCommon.getInputContainer(textField);
        inputContainer.pad(2, 20, 2, 20);
        inputContainer.fill();

        return inputContainer;
    }

    public static <T extends Actor> Container<T> getInputContainer(T t) {
        Container<T> container = new Container<>(t);
        container.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.TEXT_FIELD_BG, Texture.class)));
        return container;
    }

    public static TextButton getTextButton(String text,
                                           BitmapFont bitmapFont) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = bitmapFont;

        return new TextButton(text, textButtonStyle);
    }

    public static TextButton getTextButton(String text) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = fontMedium;

        return new TextButton(text, textButtonStyle);
    }

    public static ImageTextButton getMenuButton(final String text) {

        final ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = UIFactoryCommon.fontMedium;

        final ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        final TextureRegionDrawable buttonUp = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class));
        final TextureRegionDrawable buttonDown = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.MAIN_MENU_BUTTON_BG_DOWN_FRAME, Texture.class));

        textButton.getStyle().up = buttonUp;
        textButton.getStyle().down = buttonDown;

        return textButton;
    }

    public static ImageTextButton getSmallRoundButton(final String text) {

        return getSmallRoundButton(text, UIFactoryCommon.fontMedium);
    }

    public static ImageTextButton getSmallRoundButton(final String text,
                                                      BitmapFont bitmapFont) {

        final ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = bitmapFont;

        final ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        final TextureRegionDrawable buttonUp = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.ROUND_SMALL_BUTTON_UP, Texture.class));
        final TextureRegionDrawable buttonDown = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.ROUND_SMALL_BUTTON_DOWN, Texture.class));

        textButton.getStyle().up = buttonUp;

        textButton.getStyle().up = buttonUp;
        textButton.getStyle().down = buttonDown;

        return textButton;
    }
}
