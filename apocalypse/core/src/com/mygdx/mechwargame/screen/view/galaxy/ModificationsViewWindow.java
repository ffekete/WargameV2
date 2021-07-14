package com.mygdx.mechwargame.screen.view.galaxy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.screen.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;

public class ModificationsViewWindow extends Table {

    public static Drawable ITEM_BG = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));
    private static final TextureRegionDrawable SELECTED_BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));

    private Modification currentModification;
    private Table currentCell;

    public ModificationsViewWindow(Stage stage,
                                   WeaponViewWindow weaponViewWindow,
                                   List<Modification> modifications,
                                   Modification initiallySelectedModification,
                                   Weapon targetWeapon,
                                   int slot) {

        currentModification = initiallySelectedModification;

        // create layout
        setTouchable(Touchable.enabled);

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        modifications.forEach(w -> {
            w.getListeners().forEach(l -> {
                if(l instanceof Tooltip) {
                    ((Tooltip)l).getManager().enabled = false;
                }
            });
        });

        this.background(ninePatchDrawable);

        ModificationsViewWindow modificationsViewWindow = this;

        setSize(1500, 980);
        setPosition(stage.getCamera().position.x - getWidth() / 2f, stage.getCamera().position.y - getHeight() / 2f);

        add(UIFactoryCommon.getTextLabel("select modification", Align.center))
                .size(1500, 60)
                .padBottom(20)
                .center()
                .colspan(2)
                .row();

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

        Table modificationsDetailsTable = new Table();
        modificationsDetailsTable.background(ninePatchDrawable);

        rightPanel.add(modificationsDetailsTable)
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
        for (int i = 0; i < modifications.size(); i++) {
            Modification modification = modifications.get(i);

            if (modification != null) {
                Table cell = new Table();

                if (modification == initiallySelectedModification) {
                    currentCell = cell;
                    cell.background(SELECTED_BACKGROUND);
                } else {
                    cell.background(ITEM_BG);
                }

                cell.add(modification)
                        .size(128);

                content.add(cell)
                        .size(128);

                List<EventListener> toClear = new ArrayList<>();
                modification.getListeners().forEach(l -> {
                    if (l instanceof ClickListener) {
                        toClear.add(l);
                    }
                });

                toClear.forEach(l -> modification.getListeners().removeValue(l, true));

                modification.addListener(new ClickListener() {

                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);
                        currentModification = modification;
                        refreshDetails(modificationsDetailsTable);
                        if (currentCell != null) {
                            currentCell.background(ITEM_BG); // reset
                        }
                        cell.background(SELECTED_BACKGROUND);
                        currentCell = cell;
                    }
                });
            }

            if (i % 4 == 3) {
                content.row();
            }
        }

        for (int i = modifications.size(); i < 60; i++) {
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

                modifications.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = true;
                        }
                    });
                });

                weaponViewWindow.refreshAll();
                stage.getActors().removeValue(modificationsViewWindow, true);
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

                Modification oldModification;
                oldModification = initiallySelectedModification;

                Modification newModification = currentModification;

                if (oldModification == newModification) {
                    stage.getActors().removeValue(modificationsViewWindow, true);
                    return;
                }

                if (oldModification != null) {
                    GameData.starShip.cargoBay.addItem(oldModification);
                    oldModification.remove(targetWeapon);
                }

                switch (slot) {
                    case 1:
                        targetWeapon.modification = currentModification;
                        targetWeapon.modification.apply(targetWeapon);
                        break;
                    case 2:
                        targetWeapon.secondModification = currentModification;
                        targetWeapon.secondModification.apply(targetWeapon);
                        break;
                    case 3:
                        targetWeapon.thirdModification = currentModification;
                        targetWeapon.thirdModification.apply(targetWeapon);
                        break;
                }

                GameData.starShip.cargoBay.removeItem(newModification);

                currentModification = newModification;

                modifications.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = true;
                        }
                    });
                });

                weaponViewWindow.refreshAll();
                stage.getActors().removeValue(modificationsViewWindow, true);
            }
        });

        // details panel
        if (currentModification != null) {
            refreshDetails(modificationsDetailsTable);
        }
    }

    private void refreshDetails(Table modificationsDetailsTable) {

        modificationsDetailsTable.clear();

        modificationsDetailsTable.add(UIFactoryCommon.getTextLabel(currentModification.name))
                .size(800, 60)
                .left()
                .padLeft(40)
                .padBottom(20)
                .row();

        Label modificationDescription = UIFactoryCommon.getTextLabel(currentModification.description, Align.topLeft);
        modificationDescription.setWrap(true);

        modificationsDetailsTable.add(modificationDescription)
                .size(800, 660)
                .left()
                .top()
                .padLeft(40)
                .row();

    }
}
