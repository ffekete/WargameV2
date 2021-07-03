package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Company;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.ship.component.Component;
import com.mygdx.mechwargame.core.starsystem.Marketplace;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.market.BarterWindow;

import java.util.Collections;
import java.util.List;

public class MarketViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    public MarketViewScreen(Star star,
                            Sector sector) {
        this.star = star;
        this.sector = sector;
    }

    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        screenContentTable.add(UIFactoryCommon.getTextLabel("your cargo", Align.center))
                .size(620, 70)
                .center()
                .padRight(10);

        screenContentTable.add(UIFactoryCommon.getTextLabel("items to barter", Align.center))
                .size(620, 70)
                .center()
                .padRight(10);

        screenContentTable.add(UIFactoryCommon.getTextLabel("marketplace", Align.center))
                .size(620, 70)
                .center()
                .padRight(10);

        screenContentTable.row();


        Table playerItems = new Table();
        screenContentTable.add(new BarterWindow(playerItems, stage))
                .size(620, 800)
                .padRight(10);

        Table barterItems = new Table();
        screenContentTable.add(new BarterWindow(barterItems, stage))
                .size(620, 800)
                .padRight(10);

        Table marketItems = new Table();
        screenContentTable.add(new BarterWindow(marketItems, stage))
                .size(620, 800)
                .padRight(10);

        refreshWindow(Company.items, playerItems);
        refreshWindow(Collections.emptyList(), barterItems);
        refreshWindow(star.facilities.stream()
                        .filter(f -> f instanceof Marketplace)
                        .map(f -> (Marketplace) f)
                        .findAny()
                        .get()
                        .itemsToSell
                , marketItems);


        screenContentTable.row();

        ImageButton backButton = UIFactoryCommon.getBackButton();
        backButton.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                GameState.game.setScreen(GameState.previousScreen);
            }
        });

        screenContentTable.add(backButton)
                .size(64, 64)
                .left();

        stage.addActor(screenContentTable);
        Gdx.input.setInputProcessor(stage);
    }

    private void refreshWindow(List<Item> itemsToSell,
                               Table itemsTable) {

        int max = 60;

        itemsTable.clear();

        for (int i = 0; i < max; i++) {

            if (i < itemsToSell.size()) {
                Table container = new Table();
                container.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class)));
                container.add(itemsToSell.get(i))
                        .size(128);
                itemsTable.add(container)
                        .size(128);
            } else {
                Table container = new Table();
                container.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class)));
                itemsTable.add(container)
                        .size(128);

            }

            if (i % 4 == 3) {
                itemsTable.row();
            }
        }

    }
}
