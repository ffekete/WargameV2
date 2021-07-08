package com.mygdx.mechwargame.screen.galaxy.inputevent.cargo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.view.galaxy.CargoViewWindow;

import java.util.*;

public class CargoClickEvent {

    private static final TextureRegionDrawable SELECTED_BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_SELECTED_ITEM_BG, Texture.class));
    private static final TextureRegionDrawable BACKGROUND = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.CARGO_ITEM_BG, Texture.class));

    private static Map<Item, Integer> cellMap = new HashMap<>();
    private static Item selectedItem;
    private static Table selectedContainer;

    public static void handle(SequenceAction sequenceAction,
                              Stage stage) {

        Table content = new Table();
        CargoViewWindow cargoViewWindow = new CargoViewWindow(content, stage);
        GameData.cargoViewWindow = cargoViewWindow;

        Camera camera = stage.getCamera();
        cargoViewWindow.setVisible(false);

        cargoViewWindow.setPosition(camera.position.x - cargoViewWindow.getWidth() / 2f, camera.position.y - cargoViewWindow.getHeight() / 2f);

        stage.addActor(cargoViewWindow);

        refreshWindow(GameData.starShip.cargoBay.getItems(), content);

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);

        visibleAction.setActor(cargoViewWindow);
        visibleAction.setTarget(cargoViewWindow);
    }

    static void refreshWindow(List<Item> items,
                              Table itemsTable) {

        int max = GameData.starShip.cargoBay.maxCapacity;

        itemsTable.clear();
        cellMap.clear();

        Deque<Item> itemQueue = new ArrayDeque<>(items);

        for (int i = 0; i < max; i++) {

            if (i < items.size()) {
                Table container = new Table();
                Item item = itemQueue.pop();

                container.background(BACKGROUND);
                container.add(item)
                        .size(128);

                Cell<Table> cell = itemsTable.add(container)
                        .size(128);

                cellMap.put(item, i);

                List<EventListener> toClear = new ArrayList<>();
                item.getListeners().forEach(eventListener -> {
                    if (eventListener instanceof ClickListener) {
                        toClear.add(eventListener);
                    }
                });

                toClear.forEach(l -> item.getListeners().removeValue(l, true));

                item.addListener(new ClickListener(Input.Buttons.LEFT) {
                    @Override
                    public boolean touchDown(InputEvent event,
                                             float x,
                                             float y,
                                             int pointer,
                                             int button) {
                        boolean result = super.touchDown(event, x, y, pointer, button);

                        return result;
                    }

                    @Override
                    public void touchUp(InputEvent event,
                                        float x,
                                        float y,
                                        int pointer,
                                        int button) {
                        super.touchUp(event, x, y, pointer, button);

                        if (selectedItem == null) {
                            selectedItem = item;
                            selectedContainer = cell.getActor();
                            selectedContainer.background(SELECTED_BACKGROUND);
                        } else {
                            int targetIndex = items.indexOf(item);
                            int sourceIndex = items.indexOf(selectedItem);

                            items.remove(selectedItem);
                            items.add(targetIndex, selectedItem);
                            items.remove(item);
                            items.add(sourceIndex, item);

                            selectedContainer.background(BACKGROUND);
                            selectedContainer = null;
                            selectedItem = null;
                            refreshWindow(items, itemsTable);
                        }
                    }
                });

                item.addListener(CargoRightClickListener.newInstance(item, items, itemsTable));

            } else {
                Table container = new Table();
                container.background(BACKGROUND);
                itemsTable.add(container)
                        .size(128);
            }

            if (i % 4 == 3) {
                itemsTable.row();
            }
        }
    }


}
