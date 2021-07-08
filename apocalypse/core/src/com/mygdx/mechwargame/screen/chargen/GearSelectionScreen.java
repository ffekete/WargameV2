package com.mygdx.mechwargame.screen.chargen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.unit.BlackBear;
import com.mygdx.mechwargame.core.unit.Hellfire;
import com.mygdx.mechwargame.core.unit.Interceptor;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.galaxy.GalaxySetupScreen;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.util.Arrays;
import java.util.List;

import static com.mygdx.mechwargame.Config.MAX_UNIT_STAT_LEVEL;
import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class GearSelectionScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private int gearIndex = 0;

    private Character character;
    private BaseUnit baseUnit;

    private final List<BaseUnit> availableBaseUnits = Arrays.asList(new BlackBear(), new Interceptor(), new Hellfire());

    @Override
    public void show() {
        super.show();

        screenContentTable.setColor(1, 1, 1, 1);

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("4. select gear", UIFactoryCommon.fontSmall)).colspan(6).padBottom(20).row();

        // select portrait
        screenContentTable.add(UIFactoryCommon.getTextLabel("choose one", UIFactoryCommon.fontSmall)).width(300);
        screenContentTable.add().size(128, 100);
        ImageTextButton scrollMechLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollMechLeftButton).size(64).right();

        baseUnit = availableBaseUnits.get(gearIndex);

        final Cell<BaseUnit> mechImgeCell = screenContentTable.add(baseUnit).size(128, 128).center();
        ImageTextButton scrollMechRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollMechRightButton).size(64).left().row();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16 ,16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        Table gearDescriptionTable = new Table();
        gearDescriptionTable.background(ninePatchDrawable);
        gearDescriptionTable.setSize(800, 600);

        Label mechDescriptionLabel = UIFactoryCommon.getDynamicTextLabel(() -> baseUnit.getDescription(), UIFactoryCommon.fontSmall);

        mechDescriptionLabel.setAlignment(Align.topLeft);
        gearDescriptionTable.add(mechDescriptionLabel)
                .left()
                .top()
                .height(150)
                .pad(20, 0, 20, 0).row();

        Cell<Table> hpCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("hp", MAX_UNIT_STAT_LEVEL, baseUnit.hp))
                .center()
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> armorCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("armor", MAX_UNIT_STAT_LEVEL, baseUnit.armor))
                .center()
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> movementCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("movement", MAX_UNIT_STAT_LEVEL, baseUnit.movementPoints))
                .center()
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> initiativeCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("initiative", MAX_UNIT_STAT_LEVEL, baseUnit.initiative))
                .center()
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> primaryWeaponCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge(baseUnit.primaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.primaryWeapon.damage * baseUnit.primaryWeapon.rateOfFire))
                .center()
                .left();
        gearDescriptionTable.padBottom(40).row();

        Table secondaryWeaponDescription = baseUnit.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(baseUnit.secondaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.secondaryWeapon.damage * baseUnit.secondaryWeapon.rateOfFire) : new Table();
        Cell<Table> secondaryWeaponCell = gearDescriptionTable.add(secondaryWeaponDescription)
                .center()
                .height(32)
                .left();
        gearDescriptionTable.padBottom(40).row();

        screenContentTable.add(gearDescriptionTable)
                .width(800)
                .height(600)
                .colspan(5)
                .row();

        screenContentTable.add().size(128, 5).row();

        ImageTextButton galaxySetupButton = UIFactoryCommon.getMenuButton("galaxy setup");

        screenContentTable.add(galaxySetupButton).colspan(5).center().size(450, 80);

        galaxySetupButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                GameData.mainCharacterBaseUnit = baseUnit;
                SequenceAction sequenceAction = new SequenceAction();
                AlphaAction alphaAction = new AlphaAction();
                sequenceAction.addAction(alphaAction);
                alphaAction.setAlpha(0);
                alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                alphaAction.setActor(screenContentTable);

                sequenceAction.addAction(new SetScreenAction(new GalaxySetupScreen()));

                stage.addAction(sequenceAction);

                return true;
            }
        });

        scrollMechLeftButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                gearIndex--;
                if (gearIndex < 0) {
                    gearIndex = availableBaseUnits.size() - 1;
                }

                baseUnit = availableBaseUnits.get(gearIndex);

                mechImgeCell.setActor(baseUnit);
                armorCell.setActor(UIFactoryCommon.getPowerGauge("armor", MAX_UNIT_STAT_LEVEL, baseUnit.armor));
                hpCell.setActor(UIFactoryCommon.getPowerGauge("hp", MAX_UNIT_STAT_LEVEL, baseUnit.hp));
                movementCell.setActor(UIFactoryCommon.getPowerGauge("movement", MAX_UNIT_STAT_LEVEL, baseUnit.movementPoints));
                initiativeCell.setActor(UIFactoryCommon.getPowerGauge("initiative", MAX_UNIT_STAT_LEVEL, baseUnit.initiative));
                primaryWeaponCell.setActor(UIFactoryCommon.getPowerGauge(baseUnit.primaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.primaryWeapon.damage * baseUnit.primaryWeapon.rateOfFire));
                Table secondaryWeaponDescription = baseUnit.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(baseUnit.secondaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.secondaryWeapon.damage * baseUnit.secondaryWeapon.rateOfFire) : new Table();
                secondaryWeaponCell.setActor(secondaryWeaponDescription);

                return true;
            }
        });

        scrollMechRightButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                gearIndex++;
                if (gearIndex >= availableBaseUnits.size()) {
                    gearIndex = 0;
                }

                baseUnit = availableBaseUnits.get(gearIndex);

                mechImgeCell.setActor(baseUnit);
                hpCell.setActor(UIFactoryCommon.getPowerGauge("hp", MAX_UNIT_STAT_LEVEL, baseUnit.hp));
                armorCell.setActor(UIFactoryCommon.getPowerGauge("armor", MAX_UNIT_STAT_LEVEL, baseUnit.armor));
                movementCell.setActor(UIFactoryCommon.getPowerGauge("movement", MAX_UNIT_STAT_LEVEL, baseUnit.movementPoints));
                initiativeCell.setActor(UIFactoryCommon.getPowerGauge("initiative", MAX_UNIT_STAT_LEVEL, baseUnit.initiative));
                primaryWeaponCell.setActor(UIFactoryCommon.getPowerGauge(baseUnit.primaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.primaryWeapon.damage * baseUnit.primaryWeapon.rateOfFire));
                Table secondaryWeaponDescription = baseUnit.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(baseUnit.secondaryWeapon.name, MAX_UNIT_STAT_LEVEL, baseUnit.secondaryWeapon.damage * baseUnit.secondaryWeapon.rateOfFire) : new Table();
                secondaryWeaponCell.setActor(secondaryWeaponDescription);
                return true;
            }
        });

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }


}
