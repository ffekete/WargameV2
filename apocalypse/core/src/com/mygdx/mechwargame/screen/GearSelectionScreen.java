package com.mygdx.mechwargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.mech.BlackBear;
import com.mygdx.mechwargame.core.mech.Hellfire;
import com.mygdx.mechwargame.core.mech.Interceptor;
import com.mygdx.mechwargame.core.mech.Mech;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.util.Arrays;
import java.util.List;

public class GearSelectionScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private int gearIndex = 0;

    private Character character;
    private Mech mech;

    private final List<Mech> availableMechs = Arrays.asList(new BlackBear(), new Interceptor(), new Hellfire());

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("create your character", UIFactoryCommon.fontMedium)).colspan(6).padBottom(50).row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("4. select gear", UIFactoryCommon.fontSmall)).colspan(6).padBottom(20).row();

        // select portrait
        screenContentTable.add(UIFactoryCommon.getTextLabel("choose one", UIFactoryCommon.fontSmall)).width(300);
        screenContentTable.add().size(128, 100);
        ImageTextButton scrollMechLeftButton = UIFactoryCommon.getSmallRoundButton("-", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollMechLeftButton).size(64).right();

        mech = availableMechs.get(gearIndex);

        final Cell<LayeredAnimatedImage> mechImgeCell = screenContentTable.add(mech.getMechImage()).size(128, 128).center();
        ImageTextButton scrollMechRightButton = UIFactoryCommon.getSmallRoundButton("+", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollMechRightButton).size(64).left().row();

        Table gearDescriptionTable = new Table();
        gearDescriptionTable.background(new AnimatedDrawable(AssetManagerV2.MECH_DESCRIPTION_FRAME, 800, 600, true, 0.1f));
        gearDescriptionTable.setSize(800, 600);

        Label mechDescriptionLabel = UIFactoryCommon.getDynamicTextLabel(() -> mech.getDescription(), UIFactoryCommon.fontSmall);

        mechDescriptionLabel.setAlignment(Align.topLeft);
        gearDescriptionTable.add(mechDescriptionLabel)
                .left()
                .top()
                .height(150)
                .pad(20).row();

        Cell<Table> hpCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("hp", 5, mech.hp))
                .width(800)
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> armorCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("armor", 5, mech.armor))
                .width(800)
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> movementCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("movement", 5, mech.movementPoints))
                .width(800)
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> initiativeCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge("initiative", 5, mech.initiative))
                .width(800)
                .left();
        gearDescriptionTable.padBottom(40).row();

        Cell<Table> primaryWeaponCell = gearDescriptionTable.add(UIFactoryCommon.getPowerGauge(mech.primaryWeapon.name, 5, mech.primaryWeapon.damage * mech.primaryWeapon.rateOfFire))
                .width(800)
                .left();
        gearDescriptionTable.padBottom(40).row();

        Table secondaryWeaponDescription = mech.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(mech.secondaryWeapon.name, 5, mech.secondaryWeapon.damage * mech.secondaryWeapon.rateOfFire) : new Table();
        Cell<Table> secondaryWeaponCell = gearDescriptionTable.add(secondaryWeaponDescription)
                .width(800)
                .height(32)
                .left();
        gearDescriptionTable.padBottom(40).row();

        screenContentTable.add(gearDescriptionTable)
                .width(800)
                .height(600)
                .colspan(5)
                .row();

        screenContentTable.add().size(128, 5).row();

        ImageTextButton finishButton = UIFactoryCommon.getMenuButton("finish >");

        screenContentTable.add(finishButton).colspan(5).right().size(450, 80);

        finishButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(new FlashingAction(0.1f, 8, finishButton.getLabel()));
                sequenceAction.addAction(new SetScreenAction(new GalaxyMapScreen()));
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
                    gearIndex = availableMechs.size() - 1;
                }

                mech = availableMechs.get(gearIndex);

                mechImgeCell.setActor(mech.getMechImage());
                armorCell.setActor(UIFactoryCommon.getPowerGauge("armor", 5, mech.armor));
                hpCell.setActor(UIFactoryCommon.getPowerGauge("hp", 5, mech.hp));
                movementCell.setActor(UIFactoryCommon.getPowerGauge("movement", 5, mech.movementPoints));
                initiativeCell.setActor(UIFactoryCommon.getPowerGauge("initiative", 5, mech.initiative));
                primaryWeaponCell.setActor(UIFactoryCommon.getPowerGauge(mech.primaryWeapon.name, 5, mech.primaryWeapon.damage * mech.primaryWeapon.rateOfFire));
                Table secondaryWeaponDescription = mech.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(mech.secondaryWeapon.name, 5, mech.secondaryWeapon.damage * mech.secondaryWeapon.rateOfFire) : new Table();
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
                if (gearIndex >= availableMechs.size()) {
                    gearIndex = 0;
                }

                mech = availableMechs.get(gearIndex);

                mechImgeCell.setActor(mech.getMechImage());
                hpCell.setActor(UIFactoryCommon.getPowerGauge("hp", 5, mech.hp));
                armorCell.setActor(UIFactoryCommon.getPowerGauge("armor", 5, mech.armor));
                movementCell.setActor(UIFactoryCommon.getPowerGauge("movement", 5, mech.movementPoints));
                initiativeCell.setActor(UIFactoryCommon.getPowerGauge("initiative", 5, mech.initiative));
                primaryWeaponCell.setActor(UIFactoryCommon.getPowerGauge(mech.primaryWeapon.name, 5, mech.primaryWeapon.damage * mech.primaryWeapon.rateOfFire));
                Table secondaryWeaponDescription = mech.secondaryWeapon != null ? UIFactoryCommon.getPowerGauge(mech.secondaryWeapon.name, 5, mech.secondaryWeapon.damage * mech.secondaryWeapon.rateOfFire) : new Table();
                secondaryWeaponCell.setActor(secondaryWeaponDescription);
                return true;
            }
        });

        finishButton.addListener(new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                Character character = new Character();

                //GameState.game.setScreen(???);
                return true;
            }

        });

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }


}
