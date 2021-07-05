package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
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
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.market.BarterWindow;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

        Table playerInfoTable = new Table();
        playerInfoTable.add(UIFactoryCommon.getTextLabel("your money "))
                .padRight(30);
        playerInfoTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Company.money + "c"));

        screenContentTable.add(playerInfoTable)
                .size(620, 70)
                .center()
                .padRight(10);

        Table barterInfoTable = new Table();

        Supplier<String> priceMessage = new Supplier<String>() {
            @Override
            public String get() {
                if (barterPrice == 0) {
                    return "no payment";
                }

                return barterPrice < 0 ? "sell for" : "pay";
            }
        };

        barterInfoTable.add(UIFactoryCommon.getDynamicTextLabel(priceMessage))
                .padRight(30);
        barterInfoTable.add(UIFactoryCommon.getDynamicTextLabel(() -> barterPrice == 0 ? "" : (barterPrice + "c")));


        screenContentTable.add(barterInfoTable)
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
            barterList.add(item);
            itemsOriginalList.put(item, GameData.starShip.cargoBay.items);
            barterPrice -= item.price;
            GameData.starShip.cargoBay.items.remove(item);
        };

        barterItemsConsumer = (Item item) -> {
            List<Item> list = itemsOriginalList.get(item);

            if (list == GameData.starShip.cargoBay.items) {
                barterPrice += item.price;
                GameData.starShip.cargoBay.items.add(item);
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
        screenContentTable
                .add()
                .size(30)
                .row();

        ImageButton backButton = UIFactoryCommon.getBackButton();

        // ************    BACK BUTTON    ************************
        backButton.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                GameState.game.setScreen(GameState.previousScreen);

                resetBarter();
            }
        });

        Table menuTable = new Table();

        menuTable.add(backButton)
                .size(64, 64)
                .padRight(620 - 64 - 100)
                .left();

        Table buyButtonTable = new Table();
        ImageTextButton buyButton = UIFactoryCommon.getMenuButton("finish");
        ImageTextButton resetButton = UIFactoryCommon.getMenuButton("reset");

        // ************    BUY BUTTON   ************************

        buyButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                if (Company.money >= barterPrice && !barterList.isEmpty()) {
                    Company.money -= barterPrice;
                    barterPrice = 0;

                    itemsOriginalList.entrySet().forEach(entry -> {
                        if (entry.getValue() == GameData.starShip.cargoBay.items) {
                            itemsToSell.add(entry.getKey());
                        } else {
                            GameData.starShip.cargoBay.items.add(entry.getKey());
                        }
                        barterList.remove(entry.getKey());

                        sortItems(GameData.starShip.cargoBay.items);
                        sortItems(itemsToSell);
                    });

                    itemsOriginalList.clear();

                    barterList.clear();
                    refreshWindows();
                }
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                resetBarter();
                refreshWindows();
            }
        });

        buyButtonTable.add(resetButton).padRight(30);
        buyButtonTable.add(buyButton);

        menuTable.add(buyButtonTable);

        screenContentTable.add(menuTable)
                .left()
                .colspan(3);

        stage.addActor(screenContentTable);
        Gdx.input.setInputProcessor(stage);
    }

    private void sortItems(List<Item> items) {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1,
                               Item o2) {
                return Integer.compare(o1.order, o2.order);
            }
        });
    }

    private void resetBarter() {
        itemsOriginalList.entrySet().forEach(entry -> {
            entry.getValue().add(entry.getKey());
            barterList.remove(entry.getKey());
        });
    }

    private void refreshWindows() {
        sortItems(GameData.starShip.cargoBay.items);
        sortItems(itemsToSell);
        sortItems(barterList);

        refreshWindow(GameData.starShip.cargoBay.items,
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

                List<EventListener> toClear = new ArrayList<>();
                item.getListeners().forEach(eventListener -> {
                    if (eventListener instanceof ClickListener) {
                        toClear.add(eventListener);
                    }
                });

                toClear.forEach(l -> item.getListeners().removeValue(l, true));

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
