package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.character.Company;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.mygdx.mechwargame.Config.MAX_UNIT_STAT_LEVEL;
import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;

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

        modelTable.add(UIFactoryCommon.getTextLabel("name", UIFactoryCommon.fontSmall, Align.left))
                .size(200, 64);

        Container<TextField> textField = UIFactoryCommon.getTextField(baseUnit.name, "", UIFactoryCommon.fontSmall);
        modelTable.add(textField)
                .size(400, 64)
                .fillX()
                .left()
                .row();

        textField.addListener(new InputListener() {

            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {

                if (keycode == Input.Keys.ENTER) {
                    stage.setKeyboardFocus(null);
                }
                return true;
            }
        });

        textField.getActor().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event,
                                Actor actor) {
                baseUnit.name = textField.getActor().getText();
            }
        });

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

        boolean canUpgrade = Company.money >= (baseUnit.armor) * 500;

        UIFactoryCommon.Pair powerUpGauge = UIFactoryCommon.getPowerUpGauge(MAX_UNIT_STAT_LEVEL, baseUnit.armor, baseUnit.maxArmor, canUpgrade);

        if (powerUpGauge.image != null) {

            powerUpGauge.image.addListener(addToolTip(baseUnit.armor * 500));

            powerUpGauge.image.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);

                    IntAction intAction = new IntAction() {
                        @Override
                        public boolean act(float delta) {
                            boolean result = super.act(delta);
                            Company.money = getValue();
                            return result;
                        }
                    };

                    intAction.setStart(Company.money);
                    intAction.setEnd(Company.money - (baseUnit.armor) * 500);
                    intAction.setDuration(0.5f);

                    stage.addAction(intAction);

                    baseUnit.armor++;

                    setupMechSetupTable(mechSetupTable, baseUnit);
                }
            });
        }

        mechDetailTable.add(powerUpGauge.table)
                .size(350, 60)
                .left();

        // hp
        mechDetailTable.add(UIFactoryCommon.getTextLabel("hp", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.hp))
                .size(350, 60)
                .left()
                .row();

        // init
        mechDetailTable.add(UIFactoryCommon.getTextLabel("initiative", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.initiative))
                .size(350, 60)
                .left();

        // movement
        mechDetailTable.add(UIFactoryCommon.getTextLabel("movement", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);

        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.movementPoints))
                .size(350, 60)
                .left()
                .row();

        mechSetupTable.add()
                .height(10)
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

        addWeaponSelectionBox(mechSetupTable, "primary weapon", primaryWeapons, true);

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

            addWeaponSelectionBox(mechSetupTable, "secondary weapon", secondaryWeapons, false);

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

        Table weaponSelectionTable = new Table();
        mechSetupTable.add(weaponSelectionTable)
                .size(1400, 150)
                .colspan(2);

        ImageTextButton assignButton = UIFactoryCommon.getMenuButton("assign");

        Array<Weapon> itemArray = new Array<>();
        for (Weapon item : items) {
            itemArray.add(item);
        }

        weaponSelectionTable.add(UIFactoryCommon.getTextLabel(message, UIFactoryCommon.fontSmall, Align.left))
                .size(480, 60)
                .padBottom(5)
                .left();

        Supplier<String> weaponName;

        if (primary) {
            weaponName = () -> selectedUnit.primaryWeapon.name + " (" + (selectedUnit.primaryWeapon.modification != null ? selectedUnit.primaryWeapon.modification.name : "unmodified")  + ")";
        } else {
            weaponName = () -> selectedUnit.secondaryWeapon.name + " (" + (selectedUnit.secondaryWeapon.modification != null ? selectedUnit.secondaryWeapon.modification.name : "unmodified")  + ")";
        }

        assignButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                stage.addActor(new WeaponViewWindow(stage, items, selectedUnit, primary ? selectedUnit.primaryWeapon : selectedUnit.secondaryWeapon, primary));
            }
        });

        weaponSelectionTable.add(UIFactoryCommon.getDynamicTextLabel(weaponName, UIFactoryCommon.fontSmall, Color.GREEN, Align.left))
                .size(920, 60)
                .left()
                .row();

        weaponSelectionTable.add(assignButton)
                .size(400, 80)
                .colspan(2)
                .padBottom(10)
                .left();
    }

    public void hide(Stage stage) {
        stage.getActors().removeValue(this, true);
        stage.setKeyboardFocus(null);
        GameData.hangarViewWindow = null;
        GameData.lockGameStage = false;
    }

    public Tooltip<Table> addToolTip(int cost) {
        TooltipManager tooltipManager = new TooltipManager();
        tooltipManager.resetTime = 0.05f;
        tooltipManager.initialTime = 0.05f;
        tooltipManager.subsequentTime = 0.05f;
        tooltipManager.instant();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class), 16, 16, 16, 16);

        Table table = new Table();
        table.pad(30);
        Sprite sprite = new Sprite(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class));
        sprite.setColor(TOOLTIP_COLOR);

        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);
        table.setBackground(ninePatchDrawable);
        Tooltip<Table> tooltip = new Tooltip<>(table, tooltipManager);

        table.add(UIFactoryCommon.getTextLabel("upgrade armor", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .colspan(2)
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("new level", UIFactoryCommon.fontSmall, Align.left))
                .width(300)
                .left();

        table.add(UIFactoryCommon.getTextLabel(Integer.toString(selectedUnit.armor + 1), UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("your money", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(300)
                .padRight(30);
        table.add(UIFactoryCommon.getTextLabel(Integer.toString(Company.money), UIFactoryCommon.fontSmall, Color.GREEN, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("cost", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(300)
                .padRight(30);
        table.add(UIFactoryCommon.getTextLabel(Integer.toString(cost), UIFactoryCommon.fontSmall, Color.RED, Align.left))
                .left()
                .expandX()
                .row();


        return tooltip;
    }

}
