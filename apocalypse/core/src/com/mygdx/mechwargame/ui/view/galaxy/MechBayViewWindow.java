package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.mech.Mech;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.Arrays;
import java.util.List;

public class MechBayViewWindow extends Table {

    private Stage stage;

    public MechBayViewWindow(Stage stage) {

        this.stage = stage;

        Table content = new Table();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        background(ninePatchDrawable);

        setSize(1000, 800);

        add(UIFactoryCommon.getTextLabel("mech bay", Align.center))
                .size(1000, 50)
                .padBottom(20)
                .center()
                .row();

        ItemsViewWindow itemsViewWindow = new ItemsViewWindow(content, stage);
        itemsViewWindow.setSize(900, 160);
        itemsViewWindow.setScrollingDisabled(false, true);

        add(itemsViewWindow)
                .size(900, 160)
                .center()
                .padBottom(20)
                .row();

        Table mechSetupTable = new Table();
        mechSetupTable.setSize(960, 530);

        mechSetupTable.background(ninePatchDrawable);
        add(mechSetupTable)
                .size(960, 510)
                .center();

        int max = GameData.starShip.mechBay.maxCapacity;

        NinePatch itemFrameNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable itemFrameNinePatchDrawable = new NinePatchDrawable(itemFrameNinePatch);

        for (int i = 0; i < max; i++) {

            Table table = new Table();
            table.background(itemFrameNinePatchDrawable);

            if (i < GameData.starShip.mechBay.capacity) {
                Mech mech = GameData.starShip.mechBay.getMechs().get(i);
                table.add(mech)
                        .size(128);
            } else {
                table.add()
                        .size(128);
            }

            content.add(table)
                    .size(128)
                    .padRight(10);
        }

        List<Mech> mechs = GameData.starShip.mechBay.getMechs();

        if (!mechs.isEmpty()) {
            setupMechSetupTable(mechSetupTable, mechs.get(0));


        }
    }

    private void setupMechSetupTable(Table mechSetupTable,
                                     Mech mech) {

        mechSetupTable.clear();
        mechSetupTable.add(new Image(mech.copyIdleDrawable()))
                .size(128, 128);
        mechSetupTable.add(UIFactoryCommon.getTextLabel(mech.name, Align.center))
                .size(832, 128)
                .row();

        SelectBox<String> selectBox = getSelectBox();

        // todo next: filter cargo items and add compatible weapons

        selectBox.setItems("asd", "def");


        mechSetupTable.add(selectBox)
                .size(600, 80);
    }

    private <T> SelectBox<T> getSelectBox() {

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        NinePatch knobNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.SCROLL_PANE_KNOB, Texture.class), 8, 8, 8, 8);
        NinePatchDrawable knobNinePatchDrawable = new NinePatchDrawable(knobNinePatch);

        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();

        com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle listStyle = new com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle();
        listStyle.selection = ninePatchDrawable;
        listStyle.font = UIFactoryCommon.fontMedium;

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.background = ninePatchDrawable;
        scrollPaneStyle.vScrollKnob = knobNinePatchDrawable;

        selectBoxStyle.background = ninePatchDrawable;
        selectBoxStyle.font = UIFactoryCommon.fontMedium;
        selectBoxStyle.fontColor = Color.WHITE;
        selectBoxStyle.listStyle = listStyle;
        selectBoxStyle.scrollStyle = scrollPaneStyle;

        return new SelectBox<>(selectBoxStyle);
    }

    public void hide(Stage stage) {
        stage.getActors().removeValue(this, true);
        stage.setKeyboardFocus(null);
        GameData.mechBayViewWindow = null;
        GameData.lockGameStage = false;
    }
}
