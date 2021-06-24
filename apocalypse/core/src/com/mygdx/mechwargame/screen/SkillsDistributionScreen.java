package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.character.Skills;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class SkillsDistributionScreen extends GenericScreenAdapter {

    private static final int LAST_CELL_WIDTH = 50;
    private Table screenContentTable = new Table();

    private final Character character;

    private int pointsToDistribute = 6;

    public SkillsDistributionScreen(Character character) {
        this.character = character;
    }

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("3. skills", UIFactoryCommon.fontSmall)).colspan(6).padBottom(50).row();

        Table characterInfoTable = new Table();
        characterInfoTable.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_INFO_FRAME, 1500, 128, true, 0.15f));
        characterInfoTable.pad(10);
        Table skillsTable = new Table();
        skillsTable.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.15f));

        Table innerTable = new Table();

        screenContentTable.add(characterInfoTable).width(1500).padBottom(50).row();
        screenContentTable.add(skillsTable).width(1500).center().row();

        characterInfoTable.add(character.portrait).size(128).padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(character.firstName, UIFactoryCommon.fontSmall)).left().padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(character.lastName, UIFactoryCommon.fontSmall)).left().padRight(20);
        innerTable.add(UIFactoryCommon.getTextLabel(String.format("[%s]", character.nickName), UIFactoryCommon.fontSmall)).left();
        characterInfoTable.add(innerTable).left().width(1300);

        skillsTable.padTop(0);

        skillsTable.add(UIFactoryCommon.getTextLabel("Points remaining")).padBottom(5).padRight(20).colspan(2).left();
        skillsTable.add().size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(pointsToDistribute))).pad(5, 20, 20, 5).width(LAST_CELL_WIDTH).center();
        skillsTable.row();

        ImageTextButton lasersPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton lasersMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Lasers.displayName)).left().padRight(20);
        skillsTable.add(lasersMinusButton).size(64).pad(5);
        skillsTable.add(lasersPlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Lasers)))).center().padLeft(20).width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton missilesPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton missilesMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Missiles.displayName)).left().padRight(20);
        skillsTable.add(missilesMinusButton).size(64).pad(5);
        skillsTable.add(missilesPlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Missiles)))).padLeft(20).center().width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton gunsPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton gunsMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Guns.displayName)).left().padRight(20);
        skillsTable.add(gunsMinusButton).size(64).pad(5);
        skillsTable.add(gunsPlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Guns)))).center().padLeft(20).width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton meleePlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton meleeMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Melee.displayName)).left().padRight(20);
        skillsTable.add(meleeMinusButton).size(64).pad(5);
        skillsTable.add(meleePlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Melee)))).padLeft(20).center().width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton pilotingPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton pilotingMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Piloting.displayName)).left().padRight(20);
        skillsTable.add(pilotingMinusButton).size(64).pad(5);
        skillsTable.add(pilotingPlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Piloting)))).padLeft(20).center().width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton evasionPlusButton = UIFactoryCommon.getSmallRoundButton("+");
        ImageTextButton evasionMinusButton = UIFactoryCommon.getSmallRoundButton("-");
        skillsTable.add(UIFactoryCommon.getTextLabel(Skills.Evasion.displayName)).left().padRight(20);
        skillsTable.add(evasionMinusButton).size(64).pad(5);
        skillsTable.add(evasionPlusButton).size(64).pad(5);
        skillsTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(character.skillValues.get(Skills.Evasion)))).padLeft(20).center().width(LAST_CELL_WIDTH);
        skillsTable.row();

        ImageTextButton gearButton = UIFactoryCommon.getMenuButton("gear >");
        screenContentTable.add(gearButton).colspan(6).right().size(450, 80).padTop(5);

        // add click action
        gearButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.delay(0.15f));
                sequenceAction.addAction(new SetScreenAction(new GearSelectionScreen()));
                stage.addAction(sequenceAction);
                return true;
            }
        });

        lasersMinusButton.addListener(addDecreaseListener(Skills.Lasers));
        lasersPlusButton.addListener(addIncreaseListener(Skills.Lasers));

        missilesMinusButton.addListener(addDecreaseListener(Skills.Missiles));
        missilesPlusButton.addListener(addIncreaseListener(Skills.Missiles));

        gunsMinusButton.addListener(addDecreaseListener(Skills.Guns));
        gunsPlusButton.addListener(addIncreaseListener(Skills.Guns));

        meleeMinusButton.addListener(addDecreaseListener(Skills.Melee));
        meleePlusButton.addListener(addIncreaseListener(Skills.Melee));

        pilotingMinusButton.addListener(addDecreaseListener(Skills.Piloting));
        pilotingPlusButton.addListener(addIncreaseListener(Skills.Piloting));

        evasionMinusButton.addListener(addDecreaseListener(Skills.Evasion));
        evasionPlusButton.addListener(addIncreaseListener(Skills.Evasion));

        stage.addActor(screenContentTable);


        Gdx.input.setInputProcessor(stage);
    }

    private InputListener addIncreaseListener(Skills skill) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                if (pointsToDistribute > 0) {
                    int oldValue = character.skillValues.get(skill);
                    character.skillValues.put(skill, oldValue + 1);
                    pointsToDistribute--;
                }
                return true;
            }
        };
    }


    private InputListener addDecreaseListener(Skills skill) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                int oldValue = character.skillValues.get(skill);

                if (oldValue > 1) {
                    character.skillValues.put(skill, oldValue - 1);
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
