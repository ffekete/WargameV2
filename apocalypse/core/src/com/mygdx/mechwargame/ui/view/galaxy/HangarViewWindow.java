package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.core.weapon.Weapon;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HangarViewWindow extends Table {

    private Stage stage;
    private BaseUnit selectedUnit;

    public HangarViewWindow(Stage stage) {

        this.stage = stage;

        Table content = new Table();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        background(ninePatchDrawable);

        setSize(1500, 900);

        add(UIFactoryCommon.getTextLabel("hangar", Align.center))
                .size(1500, 50)
                .padBottom(20)
                .center()
                .row();

        ItemsViewWindow itemsViewWindow = new ItemsViewWindow(content, stage);
        itemsViewWindow.setSize(1460, 160);
        itemsViewWindow.setScrollingDisabled(false, true);

        add(itemsViewWindow)
                .size(1460, 160)
                .center()
                .padBottom(20)
                .row();

        Table mechSetupTable = new Table();
        mechSetupTable.setSize(1460, 610);

        mechSetupTable.background(ninePatchDrawable);
        add(mechSetupTable)
                .size(1460, 610)
                .center();

        int max = GameData.starShip.hangar.maxCapacity;

        NinePatch itemFrameNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable itemFrameNinePatchDrawable = new NinePatchDrawable(itemFrameNinePatch);

        for (int i = 0; i < max; i++) {

            Table table = new Table();
            table.background(itemFrameNinePatchDrawable);

            if (i < GameData.starShip.hangar.capacity) {
                BaseUnit baseUnit = GameData.starShip.hangar.getMechs().get(i);

                List<EventListener> toClear = new ArrayList<>();
                baseUnit.getListeners()
                        .forEach(l -> {
                            if (l instanceof ClickListener) {
                                toClear.add(l);
                            }
                        });

                for (EventListener eventListener : toClear) {
                    baseUnit.getListeners().removeValue(eventListener, true);
                }

                baseUnit.addListener(new ClickListener() {
                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);
                        event.stop();
                        selectedUnit = baseUnit;
                        setupMechSetupTable(mechSetupTable, baseUnit);
                    }
                });

                table.add(baseUnit)
                        .size(128);
            } else {
                table.add()
                        .size(128);
            }

            content.add(table)
                    .size(128)
                    .padRight(10);
        }

        List<BaseUnit> baseUnits = GameData.starShip.hangar.getMechs();

        if (!baseUnits.isEmpty()) {
            selectedUnit = baseUnits.get(0);
            setupMechSetupTable(mechSetupTable, selectedUnit);
        }
    }

    private void setupMechSetupTable(Table mechSetupTable,
                                     BaseUnit baseUnit) {

        mechSetupTable.clear();

        Table imageTable = new Table();
        imageTable.add(new Image(baseUnit.copyIdleDrawable()))
                .size(128, 128)
                .center();

        mechSetupTable.add(imageTable)
                .size(128)
                .padRight(222);

        Table modelTable = new Table();
        mechSetupTable.add(modelTable)
                .size(1050, 138)
                .left();

        modelTable.add(UIFactoryCommon.getTextLabel("model", UIFactoryCommon.fontSmall, Align.left))
                .size(200, 64);

        modelTable.add(UIFactoryCommon.getTextLabel(baseUnit.name, UIFactoryCommon.fontSmall, Align.left))
                .size(850, 64)
                .left()
                .row();

        modelTable.add(UIFactoryCommon.getTextLabel("type", UIFactoryCommon.fontSmall, Align.left))
                .size(200, 64);
        modelTable.add(UIFactoryCommon.getTextLabel(baseUnit.unitType.name, UIFactoryCommon.fontSmall, Align.left))
                .size(850, 64)
                .left();

        mechSetupTable
                .row();

        Table mechDetailTable = new Table();
        mechSetupTable.add(mechDetailTable)
                .size(1400, 120)
                .colspan(2)
                .center()
                .row();
        // armor
        mechDetailTable.add(UIFactoryCommon.getTextLabel("armor", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(5, baseUnit.armor))
                .size(350, 60)
                .left();

        // hp
        mechDetailTable.add(UIFactoryCommon.getTextLabel("hp", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(5, baseUnit.hp))
                .size(350, 60)
                .left()
                .row();

        // init
        mechDetailTable.add(UIFactoryCommon.getTextLabel("initiative", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(5, baseUnit.initiative))
                .size(350, 60)
                .left();

        // movement
        mechDetailTable.add(UIFactoryCommon.getTextLabel("movement", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);

        mechDetailTable.add(UIFactoryCommon.getPowerGauge(5, baseUnit.movementPoints))
                .size(350, 60)
                .left()
                .row();

        mechSetupTable.add()
                .height(30)
                .row();

        List<Weapon> primaryWeapons = new ArrayList<>();
        primaryWeapons.add(baseUnit.primaryWeapon);

        primaryWeapons.addAll(GameData.starShip.cargoBay.getItems()
                .stream()
                .filter(i -> i instanceof Weapon)
                .map(w -> (Weapon) w)
                .filter(w -> w.socket == baseUnit.primaryWeaponSocket)
                .collect(Collectors.toList())
        );

        addWeaponSelectionBox(mechSetupTable, "primary weapon slot selection", primaryWeapons, true);

        mechSetupTable
                .padBottom(10)
                .row();

        if (baseUnit.secondaryWeapon != null) {

            List<Weapon> secondaryWeapons = new ArrayList<>();
            secondaryWeapons.add(baseUnit.secondaryWeapon);

            secondaryWeapons.addAll(GameData.starShip.cargoBay.getItems()
                    .stream()
                    .filter(i -> i instanceof Weapon)
                    .map(w -> (Weapon) w)
                    .filter(w -> w.socket == baseUnit.secondaryWeaponSocket)
                    .collect(Collectors.toList())
            );

            addWeaponSelectionBox(mechSetupTable, "secondary weapon slot selection", secondaryWeapons, false);

        } else {
            mechSetupTable.add()
                    .size(1400, 150)
                    .colspan(2)
                    .row();
        }
    }

    private void addWeaponSelectionBox(Table mechSetupTable,
                                       String message,
                                       List<Weapon> items,
                                       boolean primary) {
        SelectBox<Weapon> primaryWeaponsSelectBox = getSelectBox();
        primaryWeaponsSelectBox.setSize(1100, 80);

        Array<Weapon> itemArray = new Array<>();
        for (Weapon item : items) {
            itemArray.add(item);
        }

        primaryWeaponsSelectBox.setItems(itemArray);

        primaryWeaponsSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event,
                                Actor actor) {
                if (primary) {
                    Weapon weaponToAdd = (Weapon) ((SelectBox) actor).getSelected();
                    GameData.starShip.cargoBay.removeItem(weaponToAdd);
                    GameData.starShip.cargoBay.addItem(selectedUnit.primaryWeapon);

                    selectedUnit.primaryWeapon = weaponToAdd;
                } else {
                    Weapon weaponToAdd = (Weapon) ((SelectBox) actor).getSelected();
                    GameData.starShip.cargoBay.removeItem(weaponToAdd);
                    GameData.starShip.cargoBay.addItem(selectedUnit.secondaryWeapon);

                    selectedUnit.secondaryWeapon = weaponToAdd;
                }
            }
        });

        mechSetupTable.add(UIFactoryCommon.getTextLabel(message, UIFactoryCommon.fontSmall, Align.left))
                .size(1100, 60)
                .left()
                .colspan(2)
                .row();

        mechSetupTable.add(primaryWeaponsSelectBox)
                .size(1100, 80)
                .padLeft(50)
                .colspan(2)
                .padBottom(10)
                .left();
    }

    private <T> SelectBox<T> getSelectBox() {

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        NinePatch knobNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.SCROLL_PANE_KNOB, Texture.class), 8, 8, 8, 8);
        NinePatchDrawable knobNinePatchDrawable = new NinePatchDrawable(knobNinePatch);

        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();

        com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle listStyle = new com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle();
        listStyle.selection = ninePatchDrawable;
        listStyle.font = UIFactoryCommon.fontSmall;

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.background = ninePatchDrawable;
        scrollPaneStyle.vScrollKnob = knobNinePatchDrawable;

        selectBoxStyle.background = ninePatchDrawable;
        selectBoxStyle.font = UIFactoryCommon.fontSmall;
        selectBoxStyle.fontColor = Color.WHITE;
        selectBoxStyle.listStyle = listStyle;
        selectBoxStyle.scrollStyle = scrollPaneStyle;

        return new SelectBox<>(selectBoxStyle);
    }

    public void hide(Stage stage) {
        stage.getActors().removeValue(this, true);
        stage.setKeyboardFocus(null);
        GameData.hangarViewWindow = null;
        GameData.lockGameStage = false;
    }
}
