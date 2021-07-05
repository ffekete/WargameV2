package com.mygdx.mechwargame.screen.chargen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class CharacterCreationScreen extends GenericScreenAdapter {

    private static final int PORTRAIT_SIZE = 32;
    private static final float FRAME_ANIM_SPEED = 0.3f;
    private Table screenContentTable = new Table();
    private int logoIndex = 0;
    private int portraitIndex = 0;

    @Override
    public void show() {
        super.show();

        screenContentTable.setColor(1, 1, 1, 1);

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium))
                .height(70)
                .colspan(6)
                .padBottom(50)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("1. appearance", UIFactoryCommon.fontSmall))
                .height(50)
                .colspan(6)
                .padBottom(50)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("first name", UIFactoryCommon.fontSmall))
                .size(250, 50)
                .left();

        Container<TextField> firstNameTextField = UIFactoryCommon.getTextField("first name", "", UIFactoryCommon.fontSmall);
        screenContentTable.add(firstNameTextField)
                .size(600, 50)
                .pad(10)
                .colspan(5)
                .row();

        // h: 120

        screenContentTable.add(UIFactoryCommon.getTextLabel("last name", UIFactoryCommon.fontSmall))
                .size(250, 50)
                .left();

        Container<TextField> lastNameTextField = UIFactoryCommon.getTextField("last name", "", UIFactoryCommon.fontSmall);
        screenContentTable.add(lastNameTextField)
                .size(600, 50)
                .pad(10)
                .colspan(5)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("nickname", UIFactoryCommon.fontSmall)).left()
                .size(250, 50);

        Container<TextField> nickNameTextField = UIFactoryCommon.getTextField("enter nickname", "", UIFactoryCommon.fontSmall);

        screenContentTable.add(nickNameTextField)
                .size(600, 50)
                .pad(10, 10, 20, 10)
                .colspan(5)
                .row();

        // h: 190

        // select portrait
        screenContentTable.add(UIFactoryCommon.getTextLabel("portrait", UIFactoryCommon.fontSmall))
                .size(400, 128);

        Table portraitTable = new Table();
        portraitTable.setSize(276, 128);

        ImageTextButton scrollPortraitLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);
        portraitTable.add(scrollPortraitLeftButton)
                .size(64)
                .padRight(10)
                .right();

        final AnimatedDrawable portraitFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
        final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, true, FRAME_ANIM_SPEED);
        final Image portraitImage = new LayeredAnimatedImage(portraitDrawable, portraitFrameAnimation);

        final Cell<Image> portraitImgeCell = portraitTable.add(portraitImage)
                .size(128)
                .padRight(10)
                .center();
        ImageTextButton scrollPortraitRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        portraitTable.add(scrollPortraitRightButton)
                .size(64)
                .left()
                .row();

        // h: 328

        screenContentTable.add()
                .fillX();

        screenContentTable.add(portraitTable)
                .size(276, 128)
                .right()
                .row();

        // h: 328

        // select logo
        Table logoTable = new Table();
        logoTable.setSize(276, 128);

        screenContentTable.add(UIFactoryCommon.getTextLabel("logo", UIFactoryCommon.fontSmall))
                .size(400, 128);

        ImageTextButton scrollLogoLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);

        logoTable.add(scrollLogoLeftButton)
                .size(64)
                .padRight(10)
                .right();

        final AnimatedDrawable logoFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
        final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, true, FRAME_ANIM_SPEED);
        final Image logoImage = new LayeredAnimatedImage(logoFrameAnimation, logoDrawable);
        final Cell<Image> logoImgeCell = logoTable.add(logoImage)
                .size(128, 128)
                .padRight(10)
                .center();

        ImageTextButton scrollLogoRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        logoTable.add(scrollLogoRightButton)
                .size(64)
                .left();

        screenContentTable.add()
                .width(600 - 276);

        screenContentTable.add(logoTable)
                .size(276, 128)
                .right()
                .row();

        // h: 456

        screenContentTable.add()
                .size(128, 92)
                .row();

        // h: 548

        ImageTextButton attributesButton = UIFactoryCommon.getMenuButton("attributes");

        screenContentTable.add(attributesButton)
                .colspan(6)
                .center()
                .padTop(212)
                .size(450, 80)
                .row();

        // h: 628 + 272

        scrollLogoLeftButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                logoIndex--;
                if (logoIndex < 0) {
                    logoIndex = GameState.assetManager.logos.size() - 1;
                }

                final AnimatedDrawable logoFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
                final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, true, FRAME_ANIM_SPEED);
                final Image logoImage = new LayeredAnimatedImage(logoFrameAnimation, logoDrawable);
                logoImgeCell.setActor(logoImage);

                return true;
            }
        });


        scrollLogoRightButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                logoIndex++;
                if (logoIndex >= GameState.assetManager.logos.size()) {
                    logoIndex = 0;
                }

                final AnimatedDrawable logoFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
                final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, true, FRAME_ANIM_SPEED);
                final Image logoImage = new LayeredAnimatedImage(logoFrameAnimation, logoDrawable);
                logoImgeCell.setActor(logoImage);

                return true;
            }
        });

        scrollPortraitLeftButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                portraitIndex--;
                if (portraitIndex < 0) {
                    portraitIndex = GameState.assetManager.portraits.size() - 1;
                }

                final AnimatedDrawable portraitFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
                final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, true, FRAME_ANIM_SPEED);
                final Image portraitImage = new LayeredAnimatedImage(portraitDrawable, portraitFrameAnimation);

                portraitImgeCell.setActor(portraitImage);

                return true;
            }
        });

        scrollPortraitRightButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                portraitIndex++;
                if (portraitIndex >= GameState.assetManager.portraits.size()) {
                    portraitIndex = 0;
                }

                final AnimatedDrawable portraitFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
                final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, true, FRAME_ANIM_SPEED);
                final Image portraitImage = new LayeredAnimatedImage(portraitDrawable, portraitFrameAnimation);

                portraitImgeCell.setActor(portraitImage);

                return true;
            }
        });

        attributesButton.addListener(new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                Character character = new Character();

                character.firstName = firstNameTextField.getActor().getText();
                character.lastName = lastNameTextField.getActor().getText();
                character.nickName = nickNameTextField.getActor().getText();
                character.portrait = (LayeredAnimatedImage) portraitImgeCell.getActor();

                SequenceAction sequenceAction = new SequenceAction();
                AlphaAction alphaAction = new AlphaAction();
                sequenceAction.addAction(alphaAction);
                alphaAction.setAlpha(0);
                alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                alphaAction.setActor(screenContentTable);

                sequenceAction.addAction(new SetScreenAction(new AttributesDistributionScreen(character)));

                stage.addAction(sequenceAction);
                return true;
            }

        });

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }


}
