package com.mygdx.mechwargame.screen.chargen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class CharacterCreationScreen extends GenericScreenAdapter {

    private static final int PORTRAIT_SIZE = 32;
    private static final float FRAME_ANIM_SPEED = 0.3f;
    private Table screenContentTable = new Table();
    private int logoIndex = 0;
    private int portraitIndex = 0;

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("1. appearance", UIFactoryCommon.fontSmall)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("first name", UIFactoryCommon.fontSmall))
                .left()
                .width(250);

        Container<TextField> firstNameTextField = UIFactoryCommon.getTextField("first name", "", UIFactoryCommon.fontSmall);
        screenContentTable.add(firstNameTextField)
                .width(600)
                .pad(10)
                .colspan(5)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("last name", UIFactoryCommon.fontSmall))
                .left()
                .width(250);

        Container<TextField> lastNameTextField = UIFactoryCommon.getTextField("last name", "", UIFactoryCommon.fontSmall);
        screenContentTable.add(lastNameTextField)
                .width(600)
                .pad(10)
                .colspan(5)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("nickname", UIFactoryCommon.fontSmall)).left()
                .width(250);

        Container<TextField> nickNameTextField = UIFactoryCommon.getTextField("enter nickname", "", UIFactoryCommon.fontSmall);

        screenContentTable.add(nickNameTextField)
                .width(600)
                .pad(10, 10, 50, 10)
                .colspan(5)
                .row();

        // select portrait
        screenContentTable.add(UIFactoryCommon.getTextLabel("choose portrait", UIFactoryCommon.fontSmall)).width(300);
        screenContentTable.add().size(128, 128);
        ImageTextButton scrollPortraitLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollPortraitLeftButton).size(64).right();

        final AnimatedDrawable portraitFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
        final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, false, FRAME_ANIM_SPEED);
        final Image portraitImage = new LayeredAnimatedImage(portraitDrawable, portraitFrameAnimation);

        final Cell<Image> portraitImgeCell = screenContentTable.add(portraitImage).size(128, 128).center();
        ImageTextButton scrollPortraitRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollPortraitRightButton).size(64).left().row();

        // separator
        screenContentTable.add().size(32).row();

        // select logo
        screenContentTable.add(UIFactoryCommon.getTextLabel("choose logo", UIFactoryCommon.fontSmall)).width(300);
        screenContentTable.add().size(128, 128);
        ImageTextButton scrollLogoLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollLogoLeftButton).size(64).right();

        final AnimatedDrawable logoFrameAnimation = new AnimatedDrawable(AssetManagerV2.PORTRAIT_FRAME, PORTRAIT_SIZE, PORTRAIT_SIZE, true, FRAME_ANIM_SPEED);
        final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, false, FRAME_ANIM_SPEED);
        final Image logoImage = new LayeredAnimatedImage(logoFrameAnimation, logoDrawable);
        final Cell<Image> logoImgeCell = screenContentTable.add(logoImage).size(128, 128).center();
        ImageTextButton scrollLogoRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollLogoRightButton).size(64).left().row();

        screenContentTable.add().size(128, 92).row();

        ImageTextButton attributesButton = UIFactoryCommon.getMenuButton("attributes");

        screenContentTable.add(attributesButton).colspan(6).center().size(450, 80);

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
                final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, false, FRAME_ANIM_SPEED);
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
                final AnimatedDrawable logoDrawable = new AnimatedDrawable(GameState.assetManager.logos.get(logoIndex), 32, 32, false, FRAME_ANIM_SPEED);
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
                final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, false, FRAME_ANIM_SPEED);
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
                final AnimatedDrawable portraitDrawable = new AnimatedDrawable(GameState.assetManager.portraits.get(portraitIndex), 32, 32, false, FRAME_ANIM_SPEED);
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
                character.portrait = (LayeredAnimatedImage)portraitImgeCell.getActor();

                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.delay(0.15f));
                sequenceAction.addAction(new SetScreenAction(new AttributesDistributionScreen(character)));
                stage.addAction(sequenceAction);
                //GameState.game.setScreen(new AttributesDistributionScreen(character));
                return true;
            }

        });

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }


}
