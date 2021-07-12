package com.mygdx.mechwargame.screen.view.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;

public class ItemsViewWindow extends ScrollPane {

    public Table container;
    public Stage stage;

    public ItemsViewWindow(Table table,
                           Stage stage) {
        super(table);
        this.stage = stage;

        this.container = table;

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        NinePatch knobNinePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.SCROLL_PANE_KNOB, Texture.class), 8, 8, 8, 8);
        NinePatchDrawable knobNinePatchDrawable = new NinePatchDrawable(knobNinePatch);

        ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
        scrollPaneStyle.background = ninePatchDrawable;
        scrollPaneStyle.vScrollKnob = knobNinePatchDrawable;
        this.setStyle(scrollPaneStyle);

        container.setSize(800, 620);

        container.pad(20, 10, 20, 10);

        ScrollPane s = this;

        addListener(new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                stage.setScrollFocus(s);
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                stage.setScrollFocus(null);
            }
        });
    }
}
