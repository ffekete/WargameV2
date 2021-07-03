package com.mygdx.mechwargame.screen.chargen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Attributes;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

public class AttributesDistributionScreen extends GenericScreenAdapter {

    private static final int LAST_CELL_WIDTH = 50;
    private Table screenContentTable = new Table();

    private final Character character;

    private int pointsToDistribute = 6;

    public AttributesDistributionScreen(Character character) {
        this.character = character;
    }

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("2. attributes", UIFactoryCommon.fontSmall)).colspan(6).padBottom(50).row();

        Table characterInfoTable = new Table();
        characterInfoTable.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_INFO_FRAME, 1500, 128, true, 0.15f));
        characterInfoTable.pad(10);
        Table attributesTable = new Table();
        attributesTable.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.15f));

        Table innerTable = new Table();

        screenContentTable.add(characterInfoTable).width(1500).padBottom(50).row();
        screenContentTable.add(attributesTable).width(1500).center().row();

        characterInfoTable.add(character.portrait).size(128).padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(character.firstName, UIFactoryCommon.fontSmall)).left().padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(character.lastName, UIFactoryCommon.fontSmall)).left().padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(String.format("[%s]", character.nickName), UIFactoryCommon.fontSmall)).left();
        characterInfoTable.add(innerTable).left().width(1300);

        attributesTable.padTop(50);

        attributesTable.add(UIFactoryCommon.getTextLabel("Points remaining")).padBottom(20).padRight(60).colspan(2).left();
        attributesTable.add().size(64).padBottom(20);
        attributesTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(pointsToDistribute))).pad(5, 20, 20, 5).width(LAST_CELL_WIDTH).center();
        attributesTable.row();

        ImageTextButton endurancePlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton enduranceMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        attributesTable.add(UIFactoryCommon.getTextLabel(Attributes.Endurance.displayName)).left().padRight(20);
        attributesTable.add(enduranceMinusButton).size(64).padRight(20).padBottom(5);
        attributesTable.add(endurancePlusButton).size(64).padRight(20);
        attributesTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.attributeValues.get(Attributes.Endurance)))).center().width(LAST_CELL_WIDTH);
        attributesTable.row();

        ImageTextButton perceptionPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton perceptionMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        attributesTable.add(UIFactoryCommon.getTextLabel(Attributes.Perception.displayName)).left().padRight(20);
        attributesTable.add(perceptionMinusButton).size(64).padRight(20).padBottom(5);
        attributesTable.add(perceptionPlusButton).size(64).padRight(20);
        attributesTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.attributeValues.get(Attributes.Perception)))).center().width(LAST_CELL_WIDTH);
        attributesTable.row();

        ImageTextButton reflexesPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton reflexesMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        attributesTable.add(UIFactoryCommon.getTextLabel(Attributes.Reflexes.displayName)).left().padRight(20);
        attributesTable.add(reflexesMinusButton).size(64).padRight(20).padBottom(5);
        attributesTable.add(reflexesPlusButton).size(64).padRight(20);
        attributesTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.attributeValues.get(Attributes.Reflexes)))).center().width(LAST_CELL_WIDTH);
        attributesTable.row();

        ImageTextButton hecPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton hecMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        attributesTable.add(UIFactoryCommon.getTextLabel(Attributes.HandEyeCoordination.displayName)).left().padRight(20);
        attributesTable.add(hecMinusButton).size(64).padRight(20).padBottom(5);
        attributesTable.add(hecPlusButton).size(64).padRight(20);
        attributesTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.attributeValues.get(Attributes.HandEyeCoordination)))).center().width(LAST_CELL_WIDTH);
        attributesTable.row();

        attributesTable.add().size(98);

        ImageTextButton skillsButton = UIFactoryCommon.getMenuButton("skills");
        screenContentTable.add(skillsButton).colspan(6).center().size(450, 80).padTop(5);

        // add click action
        enduranceMinusButton.addListener(addDecreaseListener(Attributes.Endurance));
        endurancePlusButton.addListener(addIncreaseListener(Attributes.Endurance));

        perceptionMinusButton.addListener(addDecreaseListener(Attributes.Perception));
        perceptionPlusButton.addListener(addIncreaseListener(Attributes.Perception));

        reflexesMinusButton.addListener(addDecreaseListener(Attributes.Reflexes));
        reflexesPlusButton.addListener(addIncreaseListener(Attributes.Reflexes));

        hecMinusButton.addListener(addDecreaseListener(Attributes.HandEyeCoordination));
        hecPlusButton.addListener(addIncreaseListener(Attributes.HandEyeCoordination));

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);

        skillsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.delay(0.15f));
                sequenceAction.addAction(new SetScreenAction(new SkillsDistributionScreen(character)));
                stage.addAction(sequenceAction);
                return true;
            }
        });
    }

    private InputListener addIncreaseListener(Attributes attribute) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (pointsToDistribute > 0) {
                    int oldValue = character.attributeValues.get(attribute);
                    character.attributeValues.put(attribute, oldValue + 1);
                    pointsToDistribute--;
                }
                return true;
            }
        };
    }


    private InputListener addDecreaseListener(Attributes attribute) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                int oldValue = character.attributeValues.get(attribute);

                if (oldValue > 0) {
                    character.attributeValues.put(attribute, oldValue - 1);
                    pointsToDistribute++;
                }

                return true;
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
