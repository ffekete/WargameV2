package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class ShipInfoLocalMenu extends Table {

    public ImageTextButton statusInfoButton;
    public ImageTextButton componentsInfoButton;
    public ImageTextButton cargoInfoButton;

    public ShipInfoLocalMenu(Stage stage) {

        BaseShip ship = GameData.starShip;
        Camera camera = stage.getCamera();

        this.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.1f));
        this.setSize(1500, 600);
        this.setPosition(camera.position.x - 750f, camera.position.y - 300);

        this.setVisible(false);

        stage.addActor(this);

        Table shipNameTable = new Table();
        ImageButton backButton = UIFactoryCommon.getBackButton();

        shipNameTable.add(backButton)
                .size(80);

        Container<TextField> nameTextField = UIFactoryCommon.getTextField(ship.name, "", UIFactoryCommon.fontSmall);
        shipNameTable.add(nameTextField)
                .size(700, 80)
                .padRight(10)
                .center();

        Label modelNameLabel = UIFactoryCommon.getTextLabel(ship.modelName, UIFactoryCommon.fontSmall);
        modelNameLabel.setAlignment(Align.center);
        shipNameTable.add(modelNameLabel)
                .size(700, 80)
                .padRight(10)
                .center();

        shipNameTable.setSize(1500, 80);

        Table mainTable = new Table();
        mainTable.setSize(1500, 500);

        this.add(mainTable);

        mainTable.add(shipNameTable)
                .width(1500)
                .center()
                .colspan(2)
                .row();

        Table buttonsTable = new Table();
        Table descriptionTable = new Table();

        buttonsTable.setSize(450, 500);
        descriptionTable.setSize(1000, 500);
        descriptionTable.background(new AnimatedDrawable(AssetManagerV2.SHIP_INFO_DESCRIPTION_PANEL, 1000, 500, true, 0.1f));

        mainTable.add(buttonsTable)
                .padRight(50)
                .center()
                .size(450, 500);
        mainTable.add(descriptionTable)
                .center()
                .size(1000, 500);

        statusInfoButton = UIFactoryCommon.getMenuButton("status");
        buttonsTable.add(statusInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        componentsInfoButton = UIFactoryCommon.getMenuButton("component");
        buttonsTable.add(componentsInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        cargoInfoButton = UIFactoryCommon.getMenuButton("cargo");
        buttonsTable.add(cargoInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        statusInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();

                descriptionTable.clear();

                Table table = new Table();
                table.setSize(960, 500);

                addLabels(table, "hull armor", ship.hullArmor.armor, ship.hullArmor.maxArmor);
                addLabels(table, "energy output", ship.energyGrid.energyOutput, ship.energyGrid.maxEnergyOutput);
                addLabels(table, "cargo capacity", ship.cargoBay.capacity, ship.cargoBay.maxCapacity);
                addLabels(table, "fuel level", (int) ship.engine.fuel, (int) ship.engine.maxFuel);
                addLabel(table, "fuel consumption", (int) ship.engine.fuelConsumption);
                addLabel(table, "ship speed", (int) ship.engine.getSpeed());

                descriptionTable.add(table).size(960, 500);

            }
        });

        componentsInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();

                descriptionTable.clear();

                Table table = new Table();
                table.setSize(960, 500);

                addLabels(table, ship.engine.name, ship.engine.level, 10);
                addLabels(table, ship.hullArmor.name, ship.hullArmor.level, 10);
                addLabels(table, ship.energyGrid.name, ship.energyGrid.level, 10);
                addLabels(table, ship.cargoBay.name, ship.cargoBay.level, 10);

                descriptionTable.add(table).size(960, 500);

            }
        });

        backButton.addListener(new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();
                setVisible(false);
                stage.getActors().removeValue(mainTable, true);
            }
        });

        nameTextField.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }
        });

        nameTextField.getActor().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event,
                                Actor actor) {
                ship.name = nameTextField.getActor().getText();
            }
        });

    }

    private void addLabel(Table table,
                           String text,
                           String value) {
        Image helpIcon = new Image(new AnimatedDrawable(AssetManagerV2.HELP_ICON, 48, 48, 0.1f));
        table.add(helpIcon)
                .size(48)
                .padRight(10);

        Label hpLabel = UIFactoryCommon.getTextLabel(text);
        table.add(hpLabel).size(500, 60);

        Label hullLabel = UIFactoryCommon.getTextLabel(value);
        hullLabel.setAlignment(Align.right);
        table.add(hullLabel)
                .size(150, 60)
                .padRight(10)
                .right()
                .row();
    }

    private void addLabels(Table table,
                           String text,
                           int baseValue,
                           int maxValue) {

        Image helpIcon = new Image(new AnimatedDrawable(AssetManagerV2.HELP_ICON, 48, 48, 0.1f));
        table.add(helpIcon)
                .size(48)
                .padRight(10);

        Label hpLabel = UIFactoryCommon.getTextLabel(text);
        table.add(hpLabel).size(500, 60);

        Label hullLabel = UIFactoryCommon.getTextLabel(Integer.toString(baseValue));
        hullLabel.setAlignment(Align.right);
        table.add(hullLabel)
                .size(125, 60)
                .padRight(10)
                .right();

        Label delimiterLabel = UIFactoryCommon.getTextLabel("/");
        delimiterLabel.setAlignment(Align.center);
        table.add(delimiterLabel)
                .size(40, 60)
                .padRight(10)
                .center();

        Label maxHullLabel = UIFactoryCommon.getTextLabel(Integer.toString(maxValue));
        maxHullLabel.setAlignment(Align.left);
        table.add(maxHullLabel)
                .size(125, 60)
                .left()
                .row();
    }

    private void addLabel(Table table,
                          String text,
                          int baseValue) {
        addLabel(table, text, Integer.toString(baseValue));
    }

}
