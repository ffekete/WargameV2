package com.mygdx.mechwargame.screen.view.star;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Company;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.input.ToolTipManager;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.view.common.ItemsViewWindow;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mygdx.mechwargame.Config.MAX_UNIT_STAT_LEVEL;
import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;
import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;

public class FactoryView extends Table {

    private Stage stage;
    private BaseUnit selectedUnit;
    private Table selectedContainer;
    private Table mechSetupTable;

    public FactoryView(Stage stage,
                       List<BaseUnit> unitsToSell) {

        TextureRegionDrawable background = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));

        this.stage = stage;

        Table content = new Table();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        Table headerTable = new Table();

        headerTable.add(UIFactoryCommon.getTextLabel("hangar capacity", Align.left))
                .padRight(30);

        Label capacityLabel = UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(GameData.starShip.hangar.maxCapacity - GameData.starShip.hangar.capacity), UIFactoryCommon.fontMedium, Align.right);

        capacityLabel.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                int cap = GameData.starShip.hangar.maxCapacity - GameData.starShip.hangar.capacity;

                if (selectedUnit == null) {
                    capacityLabel.setColor(Color.WHITE);
                } else if (cap - selectedUnit.unitType.size >= 0) {
                    capacityLabel.setColor(Color.GREEN);
                } else {
                    capacityLabel.setColor(Color.RED);
                }
                return false;
            }
        });

        headerTable.add(capacityLabel)
                .width(55)
                .padRight(80);

        headerTable.add(UIFactoryCommon.getTextLabel("money", Align.left))
                .padRight(10);
        headerTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(Company.money), UIFactoryCommon.fontMedium, Align.right))
                .width(200)
                .padRight(80);

        Label priceLabel = UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(selectedUnit == null ? 0 : selectedUnit.getPrice()), UIFactoryCommon.fontMedium, Align.right);

        priceLabel.addAction(new Action() {
            @Override
            public boolean act(float delta) {

                if (selectedUnit == null) {
                    priceLabel.setColor(Color.WHITE);
                } else if (Company.money >= selectedUnit.getPrice()) {
                    priceLabel.setColor(Color.GREEN);
                } else {
                    priceLabel.setColor(Color.RED);
                }
                return false;
            }
        });

        headerTable.add(UIFactoryCommon.getTextLabel("cost"))
                .padRight(10);

        headerTable.add(priceLabel)
                .width(200);

        headerTable.add()
                .expand();

        add(headerTable)
                .size(1460, 50)
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

        this.mechSetupTable = mechSetupTable;

        mechSetupTable.setSize(1460, 690);

        mechSetupTable.background(ninePatchDrawable);
        add(mechSetupTable)
                .size(1460, 690)
                .center();

        int max = unitsToSell.size();

        Drawable selectedItemBg = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));

        refreshContent(unitsToSell, background, content, mechSetupTable, max, selectedItemBg);

        row();
        add().size(20).row();

        Table buttonRow = new Table();
        ImageTextButton backButton = UIFactoryCommon.getSmallRoundButton("back", UIFactoryCommon.fontSmall);

        buttonRow.add().expand();

        buttonRow.add(backButton)
                .size(350, 70)
                .padRight(20);

        ImageTextButton buyButton = UIFactoryCommon.getSmallRoundButton("buy", UIFactoryCommon.fontSmall);
        buttonRow.add(buyButton)
                .size(350, 70);

        add(buttonRow)
                .size(1460, 80);

        buyButton.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (selectedUnit == null ||
                        !GameData.starShip.hangar.canAdd(selectedUnit)
                        || Company.money < selectedUnit.getPrice()) {
                    buyButton.setDisabled(true);
                } else {
                    buyButton.setDisabled(false);
                }
                return false;
            }
        });

        buyButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                if (GameData.starShip.hangar.addUnit(selectedUnit)) {

                    IntAction intAction = new IntAction() {
                        @Override
                        public boolean act(float delta) {
                            boolean result = super.act(delta);
                            Company.money = getValue();
                            return result;
                        }
                    };

                    intAction.setStart(Company.money);
                    intAction.setEnd(Company.money - selectedUnit.getPrice());
                    intAction.setDuration(0.5f);

                    stage.addAction(intAction);

                    stage.addAction(intAction);

                    unitsToSell.remove(selectedUnit);

                    if (!unitsToSell.isEmpty()) {
                        selectedUnit = unitsToSell.get(0);
                        setupMechSetupTable(mechSetupTable, selectedUnit);
                    }

                    refreshContent(unitsToSell, background, content, mechSetupTable, max, selectedItemBg);
                }
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                SequenceAction sequenceAction = new SequenceAction();
                AlphaAction alphaAction = new AlphaAction();
                sequenceAction.addAction(alphaAction);
                alphaAction.setAlpha(0);
                alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                alphaAction.setActor(mechSetupTable);

                sequenceAction.addAction(new SetScreenAction(GameState.previousScreen));

                stage.addAction(sequenceAction);
            }
        });

    }

    private void refreshContent(List<BaseUnit> unitsToSell,
                                TextureRegionDrawable background,
                                Table content,
                                Table mechSetupTable,
                                int max,
                                Drawable selectedItemBg) {
        content.clear();

        if (unitsToSell.isEmpty()) {
            return;
        }

        for (int i = 0; i < max; i++) {

            Table table = new Table();
            table.background(background);

            if (i == 0) {
                selectedContainer = table;
                selectedContainer.background(selectedItemBg);

                selectedUnit = unitsToSell.get(0);
                setupMechSetupTable(mechSetupTable, selectedUnit);
            }

            if (i < unitsToSell.size()) {
                BaseUnit baseUnit = unitsToSell.get(i);

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
                        if (selectedContainer != null) {
                            selectedContainer.setBackground(background);
                        }
                        selectedContainer = table;
                        selectedContainer.background(selectedItemBg);
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
                .padBottom(20)
                .left();

        modelTable.add(UIFactoryCommon.getTextLabel("name", UIFactoryCommon.fontSmall, Align.left))
                .size(200, 64);

        Container<TextField> textField = UIFactoryCommon.getTextField(baseUnit.name, "", UIFactoryCommon.fontSmall);
        modelTable.add(textField)
                .size(400, 64)
                .fillX()
                .left()
                .colspan(2)
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
                .height(64)
                .padRight(30)
                .left();

        modelTable.add(UIFactoryCommon.getTextLabel("(size", UIFactoryCommon.fontSmall, Align.left))
                .height(64)
                .padRight(30);

        Label sizeLabel = UIFactoryCommon.getTextLabel(Integer.toString(baseUnit.unitType.size), UIFactoryCommon.fontSmall, Align.left);

        sizeLabel.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                int cap = GameData.starShip.hangar.maxCapacity - GameData.starShip.hangar.capacity;

                if (selectedUnit == null) {
                    sizeLabel.setColor(Color.WHITE);
                } else if (cap - selectedUnit.unitType.size >= 0) {
                    sizeLabel.setColor(Color.GREEN);
                } else {
                    sizeLabel.setColor(Color.RED);
                }
                return false;
            }
        });

        modelTable.add(sizeLabel)
                .height(64)
                .padRight(5)
                .left();

        modelTable.add(UIFactoryCommon.getTextLabel(")", UIFactoryCommon.fontSmall));

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

        UIFactoryCommon.Pair powerUpGauge = UIFactoryCommon.getPowerUpGauge(MAX_UNIT_STAT_LEVEL, baseUnit.armor, baseUnit.maxArmor, false);

        mechDetailTable.add(powerUpGauge.table)
                .size(320, 60)
                .padRight(60)
                .left();

        // hp
        mechDetailTable.add(UIFactoryCommon.getTextLabel("hp", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.hp))
                .size(320, 60)
                .left()
                .row();

        // init
        mechDetailTable.add(UIFactoryCommon.getTextLabel("initiative", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);
        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.initiative))
                .size(320, 60)
                .padRight(60)
                .left();

        // movement
        mechDetailTable.add(UIFactoryCommon.getTextLabel("movement", UIFactoryCommon.fontSmall, Align.left))
                .size(350, 60);

        mechDetailTable.add(UIFactoryCommon.getPowerGauge(MAX_UNIT_STAT_LEVEL, baseUnit.movementPoints))
                .size(320, 60)
                .left()
                .row();

        mechSetupTable.add()
                .height(40)
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
                .padBottom(30)
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

        Array<Weapon> itemArray = new Array<>();
        for (Weapon item : items) {
            itemArray.add(item);
        }

        Table weaponNameTable = new Table();

        weaponNameTable.add(UIFactoryCommon.getTextLabel(message, UIFactoryCommon.fontSmall, Align.left))
                .size(480, 60)
                .padBottom(10)
                .left()
                .row();

        weaponNameTable.add(UIFactoryCommon.getTextLabel(primary ? selectedUnit.primaryWeapon.socket.name : selectedUnit.secondaryWeapon.socket.name, UIFactoryCommon.fontSmall, Align.left))
                .size(480, 60)
                .padBottom(10)
                .left();

        weaponSelectionTable.add(weaponNameTable)
                .size(480, 140)
                .padBottom(5)
                .left();

        Table weaponDetailsWindow = new Table();

        Weapon weapon = primary ? selectedUnit.primaryWeapon : selectedUnit.secondaryWeapon;

        Table cell = new Table();
        cell.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class)));
        cell.add(weapon)
                .size(128);
        weaponDetailsWindow.add(cell)
                .size(128)
                .padRight(20);

        addModIcon(weaponDetailsWindow, weapon.modification);
        addModIcon(weaponDetailsWindow, weapon.secondModification);
        addModIcon(weaponDetailsWindow, weapon.thirdModification);

        weaponDetailsWindow.add()
                .size(128, 128);

        weaponDetailsWindow.add()
                .expandX();

        weaponSelectionTable.add(weaponDetailsWindow)
                .size(900, 128)
                .expandX()
                .colspan(2)
                .padBottom(20)
                .left();
    }

    private void addModIcon(Table weaponDetailsWindow,
                            Modification modification) {

        Table mod1cell = new Table();
        if (modification != null) {
            mod1cell.add(modification)
                    .size(128);
            weaponDetailsWindow.add(mod1cell)
                    .size(128)
                    .padRight(20);
        } else {

        }

    }

    public Tooltip<Table> addToolTip() {
        TooltipManager tooltipManager = ToolTipManager.getTooltipManager();
        tooltipManager.instant();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class), 16, 16, 16, 16);

        Table table = new Table();
        table.pad(30);
        Sprite sprite = new Sprite(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class));
        sprite.setColor(TOOLTIP_COLOR);

        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);
        table.setBackground(ninePatchDrawable);
        Tooltip<Table> tooltip = new Tooltip<>(table, tooltipManager);

        String text = "Select armor upgrade. Armor can be upgraded by adding armor component that you already own. This armor cannot be removed once fixed.";
        Label toolTipLabel = UIFactoryCommon.getTextLabel(text, UIFactoryCommon.fontSmall, Align.left);
        toolTipLabel.setWrap(true);
        table.add(toolTipLabel)
                .left()
                .colspan(2)
                .width(900)
                .row();


        return tooltip;
    }

}
