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
import com.mygdx.mechwargame.core.starsystem.Marketplace;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.DynamicTextLabel;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.market.BarterWindow;

import java.util.*;
import java.util.function.Consumer;

public class MarketViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    int barterPrice = 0;

    Map<Item, List> itemsOriginalList = new HashMap<>();

    Table playerItems = new Table();
    Table marketItems = new Table();
    Table barterItems = new Table();

    List<Item> itemsToSell;
    List<Item> barterList = new LinkedList<>();
    Consumer<Item> barterItemsConsumer;
    Consumer<Item> playerItemsConsumer;
    Consumer<Item> itemsToSellConsumer;

    public MarketViewScreen(Star star,
                            Sector sector) {
        this.star = star;
        this.sector = sector;

        itemsToSell = star.facilities.stream()
                .filter(f -> f instanceof Marketplace)
                .map(f -> (Marketplace) f)
                .findAny()
                .get()
                .itemsToSell;
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

        screenContentTable.add(new BarterWindow(playerItems, stage))
                .size(620, 800)
                .padRight(10);

        screenContentTable.add(new BarterWindow(barterItems, stage))
                .size(620, 800)
                .padRight(10);


        screenContentTable.add(new BarterWindow(marketItems, stage))
                .size(620, 800)
                .padRight(10);

        playerItemsConsumer = (Item item) -> {
            barterItems.add(item);
            itemsOriginalList.put(item, Company.items);
            barterPrice -= item.price;
            Company.items.remove(item);
        };

        barterItemsConsumer = (Item item) -> {
            List<Item> list = itemsOriginalList.get(item);

            if (list == Company.items) {
                barterPrice += item.price;
                Company.items.add(item);
            } else {
                barterPrice -= item.price;
                itemsToSell.add(item);
            }
            barterList.remove(item);
        };

        itemsToSellConsumer = (Item item) -> {
            barterList.add(item);
            itemsOriginalList.put(item, itemsToSell);
            barterPrice += item.price;
            itemsToSell.remove(item);
        };

        refreshWindows();

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
                itemsToSell.addAll(barterList);
            }
        });

        Table menuTable = new Table();

        menuTable.add(backButton)
                .size(64, 64)
                .padRight(20)
                .left();

        menuTable.add(UIFactoryCommon.getDynamicTextLabel(() -> barterPrice < 0 ? "sell for" : "pay"))
        .padRight(30);
        menuTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(barterPrice)));

        menuTable.add(UIFactoryCommon.getTextLabel(", your money:"))
                .padRight(30);
        menuTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(Company.money)));

        screenContentTable.add(menuTable)
                .left()
                .colspan(3);

        stage.addActor(screenContentTable);
        Gdx.input.setInputProcessor(stage);
    }

    private void refreshWindows() {
        refreshWindow(Company.items,
                playerItems,
                playerItemsConsumer);

        refreshWindow(barterList,
                barterItems,
                barterItemsConsumer);

        refreshWindow(itemsToSell,
                marketItems,
                itemsToSellConsumer);
    }

    private void refreshWindow(List<Item> itemsToSell,
                               Table itemsTable,
                               Consumer<Item> consumer) {

        int max = 60;

        itemsTable.clear();

        Deque<Item> itemQueue = new ArrayDeque<>(itemsToSell);

        for (int i = 0; i < max; i++) {

            if (i < itemsToSell.size()) {
                Table container = new Table();
                Item item = itemQueue.pop();
                container.background(new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class)));
                container.add(item)
                        .size(128);
                itemsTable.add(container)
                        .size(128);

                item.getListeners().clear();
                item.addListener(new ClickListener() {
                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);
                        consumer.accept(item);
                        refreshWindows();
                    }
                });
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
