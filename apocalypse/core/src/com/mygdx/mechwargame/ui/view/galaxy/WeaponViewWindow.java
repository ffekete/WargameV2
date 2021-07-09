package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;

public class WeaponViewWindow extends Table {

    public static Drawable ITEM_BG = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));
    private static final TextureRegionDrawable SELECTED_BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));


    private Weapon currentWeapon;
    private Table currentCell;

    public WeaponViewWindow(Stage stage,
                            List<Weapon> weaponsToShow,
                            BaseUnit selectedUnit,
                            Weapon initiallySelectedWeapon,
                            boolean primary) {

        currentWeapon = initiallySelectedWeapon;

        // create layout
        setTouchable(Touchable.enabled);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        this.background(ninePatchDrawable);

        setSize(1500, 900);
        setPosition(stage.getCamera().position.x - 750, stage.getCamera().position.y - 450);

        Table content = new Table();
        ItemsViewWindow itemsViewWindow = new ItemsViewWindow(content, stage);
        itemsViewWindow.setSize(600, 860);

        add(itemsViewWindow)
                .size(600, 860)
                .padLeft(20)
                .padRight(20);

        Table rightPanel = new Table();
        rightPanel.setSize(840, 860);
        add(rightPanel)
                .size(840, 860)
                .padRight(20);

        Table weaponDetailsTable = new Table();
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
                        refreshDetails(weaponDetailsTable);
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

        Table weaponViewWindow = this;

        // back button
        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
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

                if(oldWeapon == newWeapon) {
                    stage.getActors().removeValue(weaponViewWindow, true);
                    return;
                }

                selectedUnit.primaryWeapon = currentWeapon;

                GameData.starShip.cargoBay.removeItem(newWeapon);
                GameData.starShip.cargoBay.addItem(oldWeapon);
                stage.getActors().removeValue(weaponViewWindow, true);
            }
        });

        // details panel
        refreshDetails(weaponDetailsTable);
    }

    private void refreshDetails(Table weaponDetailsTable) {

        weaponDetailsTable.clear();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(currentWeapon.longName))
                .fillX()
                .left()
                .padLeft(40)
                .colspan(2)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("damage"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.damage))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("rate of fire"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.rateOfFire))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("accuracy"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.accuracy))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("range"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getPowerGauge(Config.MAX_WEAPON_STAT_LEVEL, currentWeapon.range))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("ammo"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(Integer.toString(currentWeapon.ammo)))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("socket"))
                .size(450, 70)
                .padLeft(40);

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel(currentWeapon.socket.name))
                .size(350, 70)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("modification"))
                .size(450, 70)
                .padLeft(40);

        ImageTextButton modButton = UIFactoryCommon.getSmallRoundButton(currentWeapon.modification != null ? currentWeapon.modification.shortName : "none");
        modButton.setSize(300, 60);

        weaponDetailsTable.add(modButton)
                .size(300, 60)
                .padRight(50)
                .row();

        weaponDetailsTable.add(UIFactoryCommon.getTextLabel("modes"))
                .size(450, 70)
                .padLeft(40);

        Table modesTable = new Table();

        if (currentWeapon.modes.contains(Mode.SingleShot)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.SINGLE_SHOT_ICON, Texture.class))))
                    .size(32)
                    .padRight(20);
        }

        if (currentWeapon.modes.contains(Mode.Burst)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.BURST_SHOT_ICON, Texture.class))))
                    .size(32)
                    .padRight(20);
        }

        if (currentWeapon.modes.contains(Mode.Area)) {
            modesTable.add(new Image(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.AREA_ATTACK_ICON, Texture.class))))
                    .size(32)
                    .padRight(20);
        }
        modesTable.add().expandX();

        weaponDetailsTable.add(modesTable)
                .size(350, 70)
                .row();
    }
}
