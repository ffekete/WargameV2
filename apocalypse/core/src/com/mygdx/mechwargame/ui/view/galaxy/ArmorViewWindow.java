package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.armor.Armor;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.ArrayList;
import java.util.List;

public class ArmorViewWindow extends Table {

    public static Drawable ITEM_BG = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));
    private static final TextureRegionDrawable SELECTED_BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));

    private Table currentCell;
    private Armor currentArmor;

    public ArmorViewWindow(Stage stage,
                           HangarViewWindow hangarViewWindow,
                           List<Armor> armors,
                           BaseUnit targetUnit) {


        // create layout
        setTouchable(Touchable.enabled);

        armors.forEach(w -> {
            w.getListeners().forEach(l -> {
                if(l instanceof Tooltip) {
                    ((Tooltip)l).getManager().enabled = false;
                }
            });
        });

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        this.background(ninePatchDrawable);

        ArmorViewWindow modificationsViewWindow = this;

        setSize(1500, 980);
        setPosition(stage.getCamera().position.x - getWidth() / 2f, stage.getCamera().position.y - getHeight() / 2f);

        add(UIFactoryCommon.getTextLabel("select armor", Align.center))
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

        Table armorDetailsTable = new Table();
        armorDetailsTable.background(ninePatchDrawable);

        rightPanel.add(armorDetailsTable)
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
        for (int i = 0; i < armors.size(); i++) {
            Armor armor = armors.get(i);

            if (armor != null) {
                Table cell = new Table();

                cell.background(ITEM_BG);

                cell.add(armor)
                        .size(128);

                content.add(cell)
                        .size(128);

                List<EventListener> toClear = new ArrayList<>();
                armor.getListeners().forEach(l -> {
                    if (l instanceof ClickListener) {
                        toClear.add(l);
                    }
                });

                toClear.forEach(l -> armor.getListeners().removeValue(l, true));

                armor.addListener(new ClickListener() {

                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);
                        currentArmor = armor;
                        refreshDetails(armorDetailsTable);
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

        for (int i = armors.size(); i < 60; i++) {
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

                armors.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = true;
                        }
                    });
                });

                hangarViewWindow.refresh();
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


                targetUnit.armor += currentArmor.protection;

                GameData.starShip.cargoBay.removeItem(currentArmor);

                armors.forEach(w -> {
                    w.getListeners().forEach(l -> {
                        if(l instanceof Tooltip) {
                            ((Tooltip)l).getManager().enabled = true;
                        }
                    });
                });

                hangarViewWindow.refresh();
                stage.getActors().removeValue(modificationsViewWindow, true);
            }
        });

        // details panel
        if (currentArmor != null) {
            refreshDetails(armorDetailsTable);
        }
    }

    private void refreshDetails(Table modificationsDetailsTable) {

        modificationsDetailsTable.clear();

        modificationsDetailsTable.add(UIFactoryCommon.getTextLabel(currentArmor.name))
                .size(800, 60)
                .left()
                .padLeft(40)
                .padBottom(20)
                .row();

        modificationsDetailsTable.add(UIFactoryCommon.getTextLabel(currentArmor.shortDescription, Align.topLeft))
                .size(800, 660)
                .left()
                .top()
                .padLeft(40)
                .row();

    }
}
