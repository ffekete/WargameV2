package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class AttributesDistributionScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();

    private final Character character;

    public AttributesDistributionScreen(Character character) {
        this.character = character;
    }

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("name: ", UIFactoryCommon.fontSmall)).row();
        screenContentTable.add(character.portrait).row();

        stage.addActor(screenContentTable);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
