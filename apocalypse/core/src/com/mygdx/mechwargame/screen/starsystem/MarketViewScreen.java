package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.IntAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;
import com.mygdx.mechwargame.ui.view.common.ItemsViewWindow;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class MarketViewScreen extends GenericScreenAdapter {

    private static final int HEADER_HEIGHT = 120;
    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    int barterPrice = 0;
    int initialCapacity;

    Map<Item, List> itemsOriginalList = new HashMap<>();

    Table playerItemsTable = new Table();
    Table marketItemsTable = new Table();
    Table barterItemsTable = new Table();

    List<Item> marketItems;
    List<Item> playerItems;
    List<Item> barterItems = new LinkedList<>();
    Consumer<Item> barterItemsConsumer;
    Consumer<Item> playerItemsConsumer;
    Consumer<Item> itemsToSellConsumer;

    Map<Item, Integer> itemPrices = new HashMap<>();
    Map<Item, Integer> marketPrices = new HashMap<>();

    public MarketViewScreen(Star star,
                            Sector sector) {
        this.star = star;
        this.sector = sector;

        screenContentTable.setColor(1, 1, 1, 1);

        playerItems = GameData.starShip.cargoBay.getItems();

        playerItems.forEach(item -> {
            itemPrices.computeIfAbsent(item, v -> (int) (item.getPrice() * sector.sectorOwnerArea.owner.itemsDemand.get(item.getClass()) * 0.9f));
            marketPrices.computeIfAbsent(item, v -> (int) (item.getPrice() * sector.sectorOwnerArea.owner.itemsDemand.get(item.getClass()) * 1.1f));
        });

        marketItems = star.facilities.stream()
                .filter(f -> f instanceof Marketplace)
                .map(f -> (Marketplace) f)
                .findAny()
                .get()
                .itemsToSell;

        marketItems.forEach(item -> {
            itemPrices.computeIfAbsent(item, v -> (int) (item.getPrice() * sector.sectorOwnerArea.owner.itemsDemand.get(item.getClass()) * 0.9f));
            marketPrices.computeIfAbsent(item, v -> (int) (item.getPrice() * sector.sectorOwnerArea.owner.itemsDemand.get(item.getClass()) * 1.1f));
        });

    }

    public void show() {
        super.show();

        initialCapacity = GameData.starShip.cargoBay.capacity;

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        Table playerInfoTable = new Table();
        playerInfoTable.add(UIFactoryCommon.getTextLabel("your money ", UIFactoryCommon.fontSmall))
                .padRight(30);
        playerInfoTable.add(UIFactoryCommon.getDynamicTextLabel(() -> Company.money + "c", UIFactoryCommon.fontSmall));

        screenContentTable.add(playerInfoTable)
                .size(620, HEADER_HEIGHT)
                .center()
                .padRight(10);

        Table barterInfoTable = new Table();

        Supplier<String> priceMessage = new Supplier<String>() {
            @Override
            public String get() {
                if (barterPrice == 0) {
                    return "no payment";
                }

                return barterPrice < 0 ? "you get" : "you pay";
            }
        };

        barterInfoTable.add(UIFactoryCommon.getTextLabel("capacity:", UIFactoryCommon.fontSmall))
                .width(300)
                .padRight(30);

        Label capacityLabel = UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(initialCapacity), UIFactoryCommon.fontSmall);
        barterInfoTable.add(capacityLabel)
                .width(280)
                .left()
                .colspan(2)
                .row();

        capacityLabel.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (initialCapacity < 0) {
                    capacityLabel.setColor(Color.RED);
                } else {
                    capacityLabel.setColor(Color.GREEN);
                }
                return false;
            }
        });


        barterInfoTable.add(UIFactoryCommon.getDynamicTextLabel(priceMessage, UIFactoryCommon.fontSmall))
                .width(300)
                .padRight(30);
        Label barterPriceLabel = UIFactoryCommon.getDynamicTextLabel(() -> barterPrice == 0 ? "" : (Math.abs(barterPrice) + "c"), UIFactoryCommon.fontSmall);
        barterInfoTable.add(barterPriceLabel)
                .width(280);
        barterPriceLabel.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (barterPrice > Company.money) {
                    barterPriceLabel.setColor(Color.RED);
                } else {
                    barterPriceLabel.setColor(Color.GREEN);
                }
                return false;
            }
        });


        screenContentTable.add(barterInfoTable)
                .size(620, HEADER_HEIGHT)
                .center()
                .padRight(10);

        screenContentTable.add(UIFactoryCommon.getTextLabel("marketplace", UIFactoryCommon.fontSmall, Align.center))
                .size(620, HEADER_HEIGHT)
                .center()
                .padRight(10);

        screenContentTable.row();

        screenContentTable.add(new ItemsViewWindow(playerItemsTable, stage))
                .size(620, 800)
                .padRight(10);

        screenContentTable.add(new ItemsViewWindow(barterItemsTable, stage))
                .size(620, 800)
                .padRight(10);


        screenContentTable.add(new ItemsViewWindow(marketItemsTable, stage))
                .size(620, 800)
                .padRight(10);

        playerItemsConsumer = (Item item) -> {
            barterItems.add(item);
            itemsOriginalList.put(item, playerItems);
            barterPrice -= itemPrices.get(item);
            GameData.starShip.cargoBay.removeItem(item);
            initialCapacity++;
        };

        barterItemsConsumer = (Item item) -> {
            List<Item> list = itemsOriginalList.remove(item);

            if (list == playerItems) {
                barterPrice += itemPrices.get(item);
                GameData.starShip.cargoBay.addItem(item);
                initialCapacity--;
            } else {
                barterPrice -= marketPrices.get(item);
                marketItems.add(item);
                initialCapacity++;
            }
            barterItems.remove(item);
        };

        itemsToSellConsumer = (Item item) -> {
            barterItems.add(item);
            itemsOriginalList.put(item, marketItems);
            barterPrice += marketPrices.get(item);
            marketItems.remove(item);
            initialCapacity--;
        };

        refreshWindows();

        screenContentTable.row();
        screenContentTable
                .add()
                .size(15)
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
                SequenceAction sequenceAction = new SequenceAction();
                AlphaAction alphaAction = new AlphaAction();
                sequenceAction.addAction(alphaAction);
                alphaAction.setAlpha(0);
                alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                alphaAction.setActor(screenContentTable);

                sequenceAction.addAction(new SetScreenAction(GameState.previousScreen));

                stage.addAction(sequenceAction);

                playerItems.forEach(item -> {
                    List<EventListener> toClear = new ArrayList<>();
                    item.getListeners().forEach(eventListener -> {
                        if (eventListener instanceof ClickListener) {
                            toClear.add(eventListener);
                        }
                    });
                    toClear.forEach(l -> item.getListeners().removeValue(l, true));
                });

                resetBarter();
            }
        });

        Table footerMenuTable = new Table();

        footerMenuTable.add(backButton)
                .size(64, 64)
                .padRight(620 - 64 - 100)
                .left();

        Table buyButtonTable = new Table();
        ImageTextButton buyButton = UIFactoryCommon.getMenuButton("trade");
        ImageTextButton resetButton = UIFactoryCommon.getMenuButton("reset");

        buyButton.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if(Company.money < barterPrice || initialCapacity < 0) {
                    buyButton.setDisabled(true);
                } else {
                    buyButton.setDisabled(false);
                }
                return false;
            }
        });

        // ************    BUY BUTTON   ************************
        buyButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                if (Company.money >= barterPrice && !barterItems.isEmpty()) {

                    if (initialCapacity < 0) {

                    } else {

                        IntAction intAction = new IntAction() {
                            @Override
                            public boolean act(float delta) {
                                boolean result = super.act(delta);
                                Company.money = getValue();
                                return result;
                            }
                        };

                        intAction.setStart(Company.money);
                        intAction.setEnd(Company.money - barterPrice);
                        intAction.setDuration(0.5f);

                        stage.addAction(intAction);

                        barterPrice = 0;

                        itemsOriginalList.entrySet().forEach(entry -> {
                            if (entry.getValue() == playerItems) {
                                marketItems.add(entry.getKey());
                            } else {
                                GameData.starShip.cargoBay.addItem(entry.getKey());
                            }
                            barterItems.remove(entry.getKey());

                            //sortItems(playerItems);
                            sortItems(marketItems);
                        });

                        itemsOriginalList.clear();

                        barterItems.clear();
                        refreshWindows();
                    }
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

        buyButtonTable.add(resetButton)
                .width(400)
                .padRight(30);
        buyButtonTable.add(buyButton)
                .width(400);

        footerMenuTable.add(buyButtonTable)
                .padRight(30);

        screenContentTable.add(footerMenuTable)
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
            List<Item> list = itemsOriginalList.get(entry.getKey());

            if (list == playerItems) {
                barterPrice += itemPrices.get(entry.getKey());
                GameData.starShip.cargoBay.addItem(entry.getKey());
            } else {
                barterPrice -= marketPrices.get(entry.getKey());
                marketItems.add(entry.getKey());
            }
            barterItems.remove(entry.getKey());
        });

        itemsOriginalList.clear();

        initialCapacity = GameData.starShip.cargoBay.capacity;

        //sortItems(playerItems);
        sortItems(marketItems);
        sortItems(barterItems);

    }

    private void refreshWindows() {
        //sortItems(playerItems);
        sortItems(marketItems);
        sortItems(barterItems);

        refreshWindow(playerItems,
                playerItemsTable,
                playerItemsConsumer);

        refreshWindow(barterItems,
                barterItemsTable,
                barterItemsConsumer);

        refreshWindow(marketItems,
                marketItemsTable,
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

    @Override
    public void dispose() {
        super.dispose();
    }
}
