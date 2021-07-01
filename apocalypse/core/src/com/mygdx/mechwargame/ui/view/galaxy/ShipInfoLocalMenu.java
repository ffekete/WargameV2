package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.text.DecimalFormat;

public class ShipInfoLocalMenu extends Container<Table> {

    public ImageTextButton engineInfoButton;
    public ImageTextButton armorInfoButton;
    public ImageTextButton energyGridInfoButton;
    public ImageTextButton componentsInfoButton;
    public ImageTextButton cargoInfoButton;

    public ShipInfoLocalMenu(Stage stage) {

        BaseShip ship = GameData.starShip;
        Camera camera = stage.getCamera();

        this.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.1f));
        this.setSize(1500, 600);
        this.setPosition(camera.position.x - 750f, camera.position.y - 300);

        this.setVisible(false);
        addEmptyClickListener(this);
        stage.addActor(this);

        Table shipNameTable = new Table();
        ImageButton backButton = UIFactoryCommon.getBackButton();

        shipNameTable.add(backButton)
                .size(80);

        Container<TextField> nameTextField = UIFactoryCommon.getTextField(ship.name, "", UIFactoryCommon.fontSmall);
        shipNameTable.add(nameTextField)
                .size(400, 80)
                .padRight(10)
                .center();

        Label modelNameLabel = UIFactoryCommon.getTextLabel(ship.modelName, UIFactoryCommon.fontSmall);
        modelNameLabel.setAlignment(Align.center);
        shipNameTable.add(modelNameLabel)
                .size(1000, 80)
                .padRight(10)
                .center();

        shipNameTable.setSize(1500, 80);

        Table mainTable = new Table();
        mainTable.setSize(1500, 500);

        this.setActor(mainTable);

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

        addEmptyClickListener(mainTable);
        addEmptyClickListener(buttonsTable);
        addEmptyClickListener(descriptionTable);

        componentsInfoButton = UIFactoryCommon.getMenuButton("components");
        buttonsTable.add(componentsInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        showComponentsInfo(descriptionTable, ship);

        engineInfoButton = UIFactoryCommon.getMenuButton("engine");
        buttonsTable.add(engineInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        armorInfoButton = UIFactoryCommon.getMenuButton("armor");
        buttonsTable.add(armorInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        energyGridInfoButton = UIFactoryCommon.getMenuButton("energy");
        buttonsTable.add(energyGridInfoButton)
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

        engineInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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
                addEmptyClickListener(table);
                table.setSize(980, 500);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                addLabel(table, ship.engine.name, 700, "");
                addLabel(table, "fuel level", 700, decimalFormat.format(ship.engine.fuel));
                addLabel(table, "max fuel level", 700, decimalFormat.format(ship.engine.maxFuel));
                addLabel(table, "fuel consumption", 700, decimalFormat.format(ship.engine.fuelConsumption));
                addLabel(table, "ship speed", 700, decimalFormat.format(ship.engine.getSpeed()));
                addLabel(table, "energy consumption", 700, decimalFormat.format(ship.engine.getEnergyConsumption()));

                descriptionTable.add(table).size(980, 500);

            }
        });

        armorInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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
                addEmptyClickListener(table);
                table.setSize(980, 500);

                addLabel(table, ship.hullArmor.name, 700, "");
                addLabel(table, "armor value", 700, Integer.toString(ship.hullArmor.armor));
                addLabel(table, "max armor value", 700, Integer.toString(ship.hullArmor.maxArmor));
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");

                descriptionTable.add(table).size(980, 500);

            }
        });

        energyGridInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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
                addEmptyClickListener(table);
                table.setSize(980, 500);

                addLabel(table, ship.energyGrid.name, 700, "");
                addLabel(table, "energy level", 700, Integer.toString(ship.energyGrid.energyOutput));
                addLabel(table, "max energy level", 700, Integer.toString(ship.energyGrid.maxEnergyOutput));
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");

                descriptionTable.add(table).size(980, 500);

            }
        });

        cargoInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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
                addEmptyClickListener(table);
                table.setSize(980, 500);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                addLabel(table, ship.cargoBay.name, 700, "");
                addLabel(table, "capacity", 700, decimalFormat.format(ship.cargoBay.capacity));
                addLabel(table, "max capacity", 700, decimalFormat.format(ship.cargoBay.maxCapacity));
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");
                addLabel(table, "", 600, "");

                descriptionTable.add(table).size(980, 500);

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

                showComponentsInfo(descriptionTable, ship);

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
                GameData.shipInfoLocalMenu = null;
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

    private void addEmptyClickListener(Actor actor) {
        actor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                System.out.println("table " + actor);
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
            }
        });
    }

    private void showComponentsInfo(Table descriptionTable,
                           BaseShip ship) {
        descriptionTable.clear();

        Table table = new Table();
        addEmptyClickListener(table);
        table.setSize(960, 500);

        addLabels(table, ship.engine.name, 600, "lv", Integer.toString(ship.engine.level), Integer.toString(10));
        addLabels(table, ship.hullArmor.name, 600, "lv", Integer.toString(ship.hullArmor.level), Integer.toString(10));
        addLabels(table, ship.energyGrid.name, 600, "lv", Integer.toString(ship.energyGrid.level), Integer.toString(10));
        addLabels(table, ship.cargoBay.name, 600, "lv", Integer.toString(ship.cargoBay.level), Integer.toString(10));
        addLabel(table, "", 600, "");
        addLabel(table, "", 600, "");

        descriptionTable.add(table).size(960, 500);
    }

    private void addLabel(Table table,
                          String text,
                          int textSize,
                          String value) {

        Label hpLabel = UIFactoryCommon.getTextLabel(text);
        table.add(hpLabel).size(textSize, 60);

        Label hullLabel = UIFactoryCommon.getTextLabel(value);
        hullLabel.setAlignment(Align.center);
        table.add(hullLabel)
                .size(150, 60)
                .padRight(10)
                .colspan(3)
                .row();
    }

    private void addLabels(Table table,
                           String text,
                           int textSize,
                           String baseValue,
                           String maxValue) {
        addLabels(table, text, textSize, "", baseValue, maxValue);
    }

    private void addLabels(Table table,
                           String text,
                           int textSize,
                           String prefix,
                           String baseValue,
                           String maxValue) {

        Label hpLabel = UIFactoryCommon.getTextLabel(text);
        table.add(hpLabel).size(textSize, 60);

        if (prefix != null && !prefix.isEmpty()) {
            Label prefixLabel = UIFactoryCommon.getTextLabel(prefix);
            prefixLabel.setAlignment(Align.center);
            table.add(prefixLabel)
                    .size(prefix.length() * 10, 60)
                    .padRight(10)
                    .center();
        }

        int lengthTrim = textSize - 510;

        Label hullLabel = UIFactoryCommon.getTextLabel(baseValue);
        hullLabel.setAlignment(Align.right);
        table.add(hullLabel)
                .size(125 - lengthTrim / 2f, 60)
                .padRight(10)
                .right();

        Label delimiterLabel = UIFactoryCommon.getTextLabel("/");
        delimiterLabel.setAlignment(Align.center);
        table.add(delimiterLabel)
                .size(40, 60)
                .padRight(10)
                .center();

        Label maxHullLabel = UIFactoryCommon.getTextLabel(maxValue);
        maxHullLabel.setAlignment(Align.left);
        table.add(maxHullLabel)
                .size(125 - lengthTrim / 2f, 60)
                .left()
                .row();
    }

    private void addLabel(Table table,
                          String text,
                          int textSize,
                          int baseValue) {
        addLabel(table, text, textSize, Integer.toString(baseValue));
    }

}
