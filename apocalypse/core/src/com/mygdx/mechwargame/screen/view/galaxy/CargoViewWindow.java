package com.mygdx.mechwargame.screen.view.galaxy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.screen.view.common.ItemsViewWindow;

public class CargoViewWindow extends Table {

    public ItemsViewWindow itemsViewWindow;

    public CargoViewWindow(Table content,
                           Stage stage) {

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.PANEL_FRAME_BG, Texture.class), 16, 16, 16, 16);
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);

        background(ninePatchDrawable);

        this.itemsViewWindow = new ItemsViewWindow(content, stage);

        setSize(640, 730);

        add(UIFactoryCommon.getTextLabel("cargo", Align.center))
                .size(600, 50)
                .padBottom(20)
                .center()
                .row();

        add(itemsViewWindow)
                .size(600, 620)
                .center();
    }

    public void hide(Stage stage) {
        stage.getActors().removeValue(this, true);
        stage.setKeyboardFocus(null);
        GameData.cargoViewWindow = null;
        GameData.lockGameStage = false;
    }
}
