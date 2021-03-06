package com.mygdx.mechwargame.screen.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.text.DecimalFormat;

public class ShipInfoView extends Container<Table> {

    public ImageTextButton engineInfoButton;
    public ImageTextButton mechBayInfoButton;
    public ImageTextButton armorInfoButton;
    public ImageTextButton energyGridInfoButton;
    public ImageTextButton componentsInfoButton;
    public ImageTextButton cargoInfoButton;
    public TextField nameTextField;

    public ShipInfoView(Stage stage) {

        BaseShip ship = GameData.starShip;
        Camera camera = stage.getCamera();

        NinePatch panelNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.PANEL_FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable panelNinePatchDrawable = new NinePatchDrawable(panelNinePatch);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        this.background(panelNinePatchDrawable);
        this.setSize(1500, 700);
        this.setPosition(camera.position.x - 750f, camera.position.y - 350);

        this.setVisible(false);
        addEmptyClickListener(this);
        stage.addActor(this);

        Table mainTable = new Table();
        mainTable.setSize(1450, 500);

        Container<TextField> nameTextFieldContainer = UIFactoryCommon.getTextField(ship.name, "", UIFactoryCommon.fontSmall);
        mainTable.add(nameTextFieldContainer)
                .size(400, 80)
                .padRight(10)
                .padLeft(20)
                .center();

        this.nameTextField = nameTextFieldContainer.getActor();
        nameTextField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {

                if (keycode == Input.Keys.ENTER) {
                    stage.setKeyboardFocus(null);
                }
                return true;
            }
        });

        Label modelNameLabel = UIFactoryCommon.getTextLabel(ship.modelName, UIFactoryCommon.fontSmall, Align.center);
        mainTable.add(modelNameLabel)
                .size(1000, 80)
                .center()
                .row();

        this.setActor(mainTable);

        Table buttonsTable = new Table();
        Table descriptionTable = new Table();

        buttonsTable.setSize(450, 500);
        descriptionTable.setSize(920, 580);

        descriptionTable.background(ninePatchDrawable);

        mainTable.add(buttonsTable)
                .padRight(30)
                .padLeft(20)
                .center()
                .size(450, 500);

        mainTable.add(descriptionTable)
                .center()
                .padLeft(40)
                .size(920, 580);

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

        mechBayInfoButton = UIFactoryCommon.getMenuButton("mech bay");
        buttonsTable.add(mechBayInfoButton)
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

        mechBayInfoButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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

                addLabel(table, ship.hangar.name, 700, "");
                addLabel(table, "capacity", 700, ship.hangar.capacity);
                addLabel(table, "max capacity", 700, ship.hangar.maxCapacity);
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");

                descriptionTable.add(table).size(980, 500);

            }
        });

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

                DecimalFormat decimalFormat = new DecimalFormat("#.0");

                addLabel(table, ship.engine.name, 700, "");
                addLabel(table, "fuel level", 700, decimalFormat.format(ship.engine.fuel));
                addLabel(table, "max fuel level", 700, decimalFormat.format(ship.engine.maxFuel));
                addLabel(table, "fuel consumption", 700, decimalFormat.format(ship.engine.fuelConsumption));
                addLabel(table, "ship speed", 700, decimalFormat.format(ship.engine.getSpeed()));
                addLabel(table, "energy consumption", 700, decimalFormat.format(ship.engine.getEnergyConsumption()));
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");

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
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");

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
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");

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
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");
                addLabel(table, "", 700, "");

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

        nameTextFieldContainer.addListener(new InputListener() {
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

        nameTextFieldContainer.getActor().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event,
                                Actor actor) {
                ship.name = nameTextFieldContainer.getActor().getText();
            }
        });

    }

    public void hide(Stage stage) {
        stage.getActors().removeValue(this, true);
        stage.setKeyboardFocus(null);
        GameData.shipInfoView = null;
        GameData.lockGameStage = false;
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
        addLabels(table, ship.hangar.name, 600, "lv", Integer.toString(ship.hangar.level), Integer.toString(10));
        addLabel(table, "", 600, "");
        addLabel(table, "", 600, "");
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
