package com.mygdx.mechwargame.screen.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.screen.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponViewWindow extends Table {

    public static Drawable ITEM_BG = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));
    private static final TextureRegionDrawable SELECTED_BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));


    private Weapon currentWeapon;
    private Table currentCell;
    private Table weaponDetailsTable = new Table();
    private HangarViewWindow hangarViewWindow;

    public WeaponViewWindow(Stage stage,
                            HangarViewWindow hangarViewWindow,
                            List<Weapon> weaponsToShow,
                            BaseUnit selectedUnit,
                            Weapon initiallySelectedWeapon,
                            boolean primary) {

        currentWeapon = initiallySelectedWeapon;

        this.hangarViewWindow = hangarViewWindow;

        // create layout
        setTouchable(Touchable.enabled);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        // disable tooltips
        weaponsToShow.forEach(w -> {
            w.getListeners().forEach(l -> {
                if(l instanceof Tooltip) {
                    ((Tooltip)l).getManager().enabled = false;
                }
            });
        });

        this.background(ninePatchDrawable);

        setSize(1500, 980);

        setPosition(stage.getCamera().position.x - getWidth() / 2f, stage.getCamera().position.y - getHeight() / 2f);

        add(UIFactoryCommon.getTextLabel("select weapon", Align.center))
                .size(1500, 60)
                .padBottom(20)
                .center()
                .colspan(2)
                .row();

        Table content = new Table();
        ItemsViewWindow itemsViewWindow = new ItemsViewWindow(content, stage);
        itemsViewWindow.setSize(600, 820);

        add(itemsViewWindow)
                .size(600, 860)
                .padLeft(20)
                .padRight(20);

        Table rightPanel = new Table();
        rightPanel.setSize(840, 860);
        add(rightPanel)
                .size(840, 860)
                .padRight(20);


        weaponDetailsTable.background(ninePatchDrawable);

        rightPanel.add(weaponDetailsTable)
                .size(840, 760)
                .colspan(2)
                .padBottom(20);

        rightPanel.row();

        ImageTextButton backButton = UIFactoryCommon.getMenuButton("back");
        rightPanel.add(backButton)
                .size(400, 80)
                .padRight(40)
                .left();

        ImageTextButton selectButton = UIFactoryCommon.getMenuButton("select");
        rightPanel.add(selectButton)
                .size(400, 80)
                .right();


        WeaponViewWindow weaponViewWindow = this;

        // fill content
        for (int i = 0; i < weaponsToShow.size(); i++) {
            Weapon weapon = weaponsToShow.get(i);

            if (weapon != null) {
                Table cell = new Table();

                if (weapon == initiallySelectedWeapon) {
                    currentCell = cell;
                    cell.background(SELECTED_BACKGROUND);
                } else {
                    cell.background(ITEM_BG);
                }

                cell.add(weapon)
                        .size(128);

                content.add(cell)
                        .size(128);

                List<EventListener> toClear = new ArrayList<>();
                weapon.getListeners().forEach(l -> {
                    if (l instanceof ClickListener) {
                        toClear.add(l);
                    }
                });

                toClear.forEach(l -> weapon.getListeners().removeValue(l, true));

                weapon.addListener(new ClickListener() {

                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);
                        currentWeapon = weapon;
                        refreshDetails(weaponDetailsTable, weaponViewWindow);
                        currentCell.background(ITEM_BG);
                        cell.background(SELECTED_BACKGROUND);
                        currentCell = cell;
                    }
                });
            }

            if (i % 4 == 3) {
                content.row();
            }
        }

        for (int i = weaponsToShow.size(); i < 60; i++) {
            Table cell = new Table();

            cell.background(ITEM_BG);

            cell.add()
                    .size(128);

            content.add(cell)
                    .size(128);

            if (i % 4 == 3) {
                content.row();
            }
        }

        // back button
        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                weaponsToShow.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = true;
                        }
                    });
                });
                hangarViewWindow.refresh();
                stage.getActors().removeValue(weaponViewWindow, true);
            }
        });

        selectButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);

                Weapon oldWeapon;
                if (primary) {
                    oldWeapon = selectedUnit.primaryWeapon;
                } else {
                    oldWeapon = selectedUnit.secondaryWeapon;
                }

                Weapon newWeapon = currentWeapon;

                if (oldWeapon == newWeapon) {
                    stage.getActors().removeValue(weaponViewWindow, true);
                    hangarViewWindow.refresh();
                    return;
                }

                if (primary) {
                    selectedUnit.primaryWeapon = currentWeapon;
                } else {
                    selectedUnit.secondaryWeapon = currentWeapon;
                }

                GameData.starShip.cargoBay.removeItem(newWeapon);
                GameData.starShip.cargoBay.addItem(oldWeapon);
                currentWeapon = newWeapon;

                weaponsToShow.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = false;
                        }
                    });
                });

                hangarViewWindow.refresh();
                stage.getActors().removeValue(weaponViewWindow, true);
            }
        });

        // details panel
        refreshDetails(weaponDetailsTable, this);
    }

    public void refreshAll() {
        refreshDetails(weaponDetailsTable, this);
    }

    private void refreshDetails(Table weaponDetailsTable,
                                WeaponViewWindow weaponViewWindow) {

        weaponDetailsTable.clear();

        Container<TextField> textField = UIFactoryCommon.getTextField(currentWeapon.longName, "", UIFactoryCommon.fontSmall);

        weaponDetailsTable.add(textField)
                .width(760)
                .colspan(3)
                .left()
                .padLeft(40)
                .row();

        textField.addListener(new InputListener() {

            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {

                if (keycode == Input.Keys.ENTER) {
                    getStage().setKeyboardFocus(null);
                }
                return true;
            }
        });

        textField.getActor().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event,
                                Actor actor) {
                currentWeapon.name = textField.getActor().getText();
                currentWeapon.longName = textField.getActor().getText();
                currentWeapon.shortName = textField.getActor().getText();
            }
        });

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("damage"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.getDamage()))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("rate of fire"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.getRateOfFire()))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("accuracy"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.getAccuracy()))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("range"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.getRange()))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("ammo"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(Integer.toString(currentWeapon.ammo)))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("socket"))
                .size(450, 65)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(currentWeapon.socket.name))
                .size(350, 65)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("modes"))
                .size(450, 60)
                .padBottom(5)
                .padLeft(40);

        Table modesTable = new Table();

        if (currentWeapon.modes.contains(Mode.SingleShot)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.SINGLE_SHOT_ICON, Texture.class))))
                    .size(50)
                    .padRight(20);
        }

        if (currentWeapon.modes.contains(Mode.Burst)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.BURST_SHOT_ICON, Texture.class))))
                    .size(50)
                    .padRight(20);
        }

        if (currentWeapon.modes.contains(Mode.Area)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.AREA_ATTACK_ICON, Texture.class))))
                    .size(50)
                    .padRight(20);
        }
        modesTable.add().expandX();

        weaponDetailsTable.add(modesTable)
                .size(350, 50)
                .padBottom(5)
                .colspan(2)
                .row();

        addModificationsSection(weaponDetailsTable, weaponViewWindow, "mod - slot 1", currentWeapon.modification, 1);
        addModificationsSection(weaponDetailsTable, weaponViewWindow, "mod - slot 2", currentWeapon.secondModification, 2);
        addModificationsSection(weaponDetailsTable, weaponViewWindow, "mod - slot 3", currentWeapon.thirdModification, 3);
    }

    private void addModificationsSection(Table weaponDetailsTable,
                                         WeaponViewWindow weaponViewWindow,
                                         String text,
                                         Modification modification,
                                         int slot) {

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(text))
                .size(450, 70)
                .padLeft(30);

        ImageTextButton modButton = UIFactoryCommon.getSmallRoundButton(modification != null ? modification.shortName : "add");
        modButton.setSize(240, 60);

        weaponDetailsTable.add(modButton)
                .size(240, 60)
                .left();

        if (modification != null) {

            ImageButton deleteButton = UIFactoryCommon.getDeleteButton();

            deleteButton.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);
                    switch (slot) {

                        case 1:
                            GameData.starShip.cargoBay.addItem(currentWeapon.modification);
                            currentWeapon.modification.remove(currentWeapon);
                            currentWeapon.modification = null;
                            refreshAll();
                            break;
                        case 2:
                            GameData.starShip.cargoBay.addItem(currentWeapon.secondModification);
                            currentWeapon.secondModification.remove(currentWeapon);
                            currentWeapon.secondModification = null;
                            refreshAll();
                            break;
                        case 3:
                            GameData.starShip.cargoBay.addItem(currentWeapon.thirdModification);
                            currentWeapon.thirdModification.remove(currentWeapon);
                            currentWeapon.thirdModification = null;
                            refreshAll();
                            break;
                    }
                }
            });

            weaponDetailsTable.add(deleteButton)
                    .size(60)
                    .left()
                    .padRight(10)
                    .row();
        } else {
            weaponDetailsTable.add()
                    .size(60)
                    .padRight(10)
                    .left()
                    .row();
        }

        modButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);

                List<Modification> mods = GameData.starShip.cargoBay.getItems()
                        .stream()
                        .filter(i -> i instanceof Modification)
                        .map(i -> (Modification) i)
                        .filter(m -> m.canBeAppliedTo(currentWeapon.socket))
                        .collect(Collectors.toList());

                getStage().addActor(new ModificationsViewWindow(getStage(), weaponViewWindow, mods, modification, currentWeapon, slot));
            }
        });
    }
}
