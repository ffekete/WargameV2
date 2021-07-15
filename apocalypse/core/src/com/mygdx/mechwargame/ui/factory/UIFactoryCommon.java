package com.mygdx.mechwargame.ui.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.*;

import java.util.function.Supplier;

public class UIFactoryCommon {

    private static final Color DEFAULT_FONT_COLOR = Color.valueOf("BCFFF9");
    public static BitmapFont fontSmall;
    public static BitmapFont fontMedium;
    public static BitmapFont fontLarge;

    static {
        fontSmall = getFont("font/font_small.fnt");
        fontMedium = getFont("font/font_med.fnt");
        fontLarge = getFont("font/font_huge.fnt");
    }

    public static DynamicProgressBar createProgressBar(int width,
                                                       int height,
                                                       Supplier<Float> currentValue,
                                                       Supplier<Float> maxValue,
                                                       Color bgColor,
                                                       Color knobColor) {
        Pixmap pixmap = new Pixmap(width / 4, height / 4, Pixmap.Format.RGBA8888);
        pixmap.setColor(bgColor);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();


        ProgressBar.ProgressBarStyle mpProgressBarStyle = new ProgressBar.ProgressBarStyle();
        mpProgressBarStyle.background = drawable;

        pixmap = new Pixmap(0, height / 4, Pixmap.Format.RGBA8888);
        pixmap.setColor(knobColor);
        pixmap.fill();

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        mpProgressBarStyle.knob = drawable;

        pixmap = new Pixmap(width / 4, height / 4, Pixmap.Format.RGBA8888);
        pixmap.setColor(knobColor);
        pixmap.fill();

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        mpProgressBarStyle.knobBefore = drawable;

        DynamicProgressBar dynamicProgressBar = new DynamicProgressBar(0,
                1,
                1,
                false,
                mpProgressBarStyle,
                currentValue,
                maxValue);
        dynamicProgressBar.setSize(width, height);

        return dynamicProgressBar;
    }

    public static Table getPowerGauge(int max,
                                      int actual) {
        return getPowerGauge("", max, actual);
    }

    public static Table getPowerGauge(String text,
                                      int max,
                                      int actual) {
        Table table = new Table();

        if (text != null && !text.isEmpty()) {
            table.add(UIFactoryCommon.getTextLabel(text, fontSmall)).padRight(5).left().width(400);
        }

        for (int i = 0; i < actual; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_FULL, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        for (int i = actual; i < max; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_EMPTY, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        table.add().expandX();

        return table;
    }

    public static Pair getPowerUpGauge(int max,
                                       int actual,
                                       int maxUpgrade,
                                       boolean canUpgrade) {
        Table table = new Table();

        for (int i = 0; i < actual; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_FULL, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        ImageButton powerUpImage = null;
        if (actual < maxUpgrade && canUpgrade) {

            powerUpImage = getPowerUpImageButton();

            table.add(powerUpImage)
                    .size(32)
                    .padRight(5)
                    .left();
            actual++;
        }

        for (int i = actual; i < maxUpgrade; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_CAN_UPGRADE, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        for (int i = maxUpgrade; i < max; i++) {
            table.add(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.POWER_ICON_EMPTY, 32, 32, true, 0.15f))).size(32).padRight(5).left();
        }

        table.add().expandX();

        return new Pair(powerUpImage, table);
    }

    public static ImageButton getPowerUpImageButton() {
        ImageButton powerUpImage;
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = new AnimatedDrawable(AssetManagerV2.POWER_ICON_PLUS, 32, 32, true, 0.15f);
        imageButtonStyle.down = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.POWER_ICON_PLUS_DOWN, Texture.class));

        powerUpImage = new AnimatedImageButton(imageButtonStyle);
        powerUpImage.getImage().setSize(32, 32);
        return powerUpImage;
    }

    public static class Pair {
        public ImageButton image;
        public Table table;

        public Pair(ImageButton image,
                    Table table) {
            this.image = image;
            this.table = table;
        }
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
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        return new Label(text, labelStyle);
    }

    public static Label getTextLabel(String text,
                                     BitmapFont bitmapFont,
                                     Color color,
                                     int align) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = color;
        Label label = new Label(text, labelStyle);
        label.setAlignment(align);
        return label;
    }

    public static Label getTextLabel(String text,
                                     BitmapFont bitmapFont,
                                     int align) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        Label label = new Label(text, labelStyle);
        label.setAlignment(align);
        return label;
    }

    public static Label getTextLabel(String text,
                                     int align) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontMedium;
        Label label = new Label(text, labelStyle);
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        label.setAlignment(align);
        return label;
    }

    public static Label getTextLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontMedium;
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        return new Label(text, labelStyle);
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontMedium;
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        return new DynamicTextLabel("", labelStyle, textSource);
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource,
                                            BitmapFont bitmapFont) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        return new DynamicTextLabel("", labelStyle, textSource);
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource,
                                            BitmapFont bitmapFont,
                                            int align) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = DEFAULT_FONT_COLOR;
        Label label = new DynamicTextLabel("", labelStyle, textSource);
        label.setAlignment(align);
        return label;
    }

    public static Label getDynamicTextLabel(Supplier<String> textSource,
                                            BitmapFont bitmapFont,
                                            Color color,
                                            int align) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = color;
        Label label = new DynamicTextLabel("", labelStyle, textSource);
        label.setAlignment(align);
        return label;
    }

    public static Container<TextField> getTextField(String message,
                                                    String text,
                                                    BitmapFont bitmapFont) {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = bitmapFont;
        textFieldStyle.cursor = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.TEXT_CURSOR, Texture.class));
        textFieldStyle.messageFont = bitmapFont;
        textFieldStyle.fontColor = DEFAULT_FONT_COLOR;
        textFieldStyle.messageFontColor = DEFAULT_FONT_COLOR;
        TextField textField = new TextField(text, textFieldStyle);
        textField.setMessageText(message);

        final Container<TextField> inputContainer = UIFactoryCommon.getInputContainer(textField);
        inputContainer.pad(2, 20, 2, 20);
        inputContainer.fill();

        return inputContainer;
    }

    public static <T extends Actor> Container<T> getInputContainer(T t) {
        Container<T> container = new Container<>(t);
        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.PANEL_FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);
        container.background(ninePatchDrawable);
        return container;
    }

    public static TextButton getTextButton(String text,
                                           BitmapFont bitmapFont) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = bitmapFont;
        textButtonStyle.fontColor = DEFAULT_FONT_COLOR;
        return new TextButton(text, textButtonStyle);
    }

    public static TextButton getTextButton(String text) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = fontMedium;
        textButtonStyle.fontColor = DEFAULT_FONT_COLOR;

        return new TextButton(text, textButtonStyle);
    }

    public static ImageButton getBackButton() {

        final ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();

        final ImageButton imageButton = new ImageButton(textButtonStyle);

        final TextureRegionDrawable buttonUp = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.BACK_BUTTON_UP, Texture.class));
        final TextureRegionDrawable buttonDown = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.BACK_BUTTON_DOWN, Texture.class));

        imageButton.getStyle().up = buttonUp;
        imageButton.getStyle().down = buttonDown;

        return imageButton;
    }

    public static ImageTextButton getMenuButton(final String text) {

        final ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = UIFactoryCommon.fontMedium;
        textButtonStyle.fontColor = DEFAULT_FONT_COLOR;

        final ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        NinePatch ninePatchSmall = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG_SMALL, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawableSmall = new NinePatchDrawable(ninePatchSmall);

        NinePatch ninePatchDisabled = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_DISABLED_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawableDisabled = new NinePatchDrawable(ninePatchDisabled);

        textButton.getStyle().up = ninePatchDrawable;
        textButton.getStyle().down = ninePatchDrawableSmall;
        textButton.getStyle().disabled = ninePatchDrawableDisabled;

        return textButton;
    }

    public static ImageButton getModeButton(Mode mode) {

        TextureRegionDrawable up = null;
        switch (mode) {

            case SingleShot:
                up = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.SINGLE_SHOT_ICON, Texture.class));
                break;
            case Burst:
                up = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.BURST_SHOT_ICON, Texture.class));
                break;
            case Area:
                up = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.AREA_ATTACK_ICON, Texture.class));
                break;
        }

        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = up;
        imageButtonStyle.down = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.MODE_BUTTON_DOWN, Texture.class));

        return new ImageButton(imageButtonStyle);
    }

    public static ImageTextButton getSmallRoundButton(final String text) {

        return getSmallRoundButton(text, UIFactoryCommon.fontMedium);
    }

    public static ImageTextButton getSmallRoundButton(final String text,
                                                      BitmapFont bitmapFont) {

        final ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = bitmapFont;
        textButtonStyle.fontColor = DEFAULT_FONT_COLOR;

        final ImageTextButton textButton = new ImageTextButton(text, textButtonStyle);

        textButton.getLabel().setAlignment(Align.center);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        NinePatch ninePatchSmall = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG_SMALL, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawableSmall = new NinePatchDrawable(ninePatchSmall);

        NinePatch ninePatchDisabled = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_DISABLED_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawableDisabled = new NinePatchDrawable(ninePatchDisabled);

        textButton.getStyle().up = ninePatchDrawable;
        textButton.getStyle().down = ninePatchDrawableSmall;
        textButton.getStyle().disabled = ninePatchDrawableDisabled;

        textButton.pack();

        return textButton;
    }

    public static ImageButton getDeleteButton() {

        final ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();

        final ImageButton imageButton = new ImageButton(imageButtonStyle);

        imageButton.getStyle().up = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.DELETE_ICON_UP, Texture.class));
        imageButton.getStyle().down = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.DELETE_ICON_DOWN, Texture.class));

        imageButton.pack();

        return imageButton;
    }

    public static ImageButton getAssignButton() {

        final ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();

        final ImageButton imageButton = new ImageButton(imageButtonStyle);

        imageButton.getStyle().up = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.ASSIGN_BUTTON_UP, Texture.class));
        imageButton.getStyle().down = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.ASSIGN_BUTTON_DOWN, Texture.class));

        imageButton.pack();

        return imageButton;
    }
}
