package com.mygdx.mechwargame.screen.galaxy.inputevent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.ConsumableItem;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.view.galaxy.CargoViewWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CargoClickEvent {

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

    private static void refreshWindow(List<Item> items,
                                      Table itemsTable) {

        int max = GameData.starShip.cargoBay.maxCapacity;

        itemsTable.clear();

        Deque<Item> itemQueue = new ArrayDeque<>(items);

        for (int i = 0; i < max; i++) {

            if (i < items.size()) {
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
                        event.stop();

                        if (item instanceof ConsumableItem) {

                            SequenceAction sequenceAction = new SequenceAction();
                            ScaleToAction scaleToAction = Actions.scaleTo(0.8f, 0.8f);
                            ScaleToAction scaleBackAction = Actions.scaleTo(1f, 1f);
                            sequenceAction.addAction(scaleToAction);
                            sequenceAction.addAction(scaleBackAction);

                            scaleToAction.setActor(item);
                            scaleToAction.setTarget(item);
                            scaleToAction.setDuration(0.05f);

                            scaleBackAction.setActor(item);
                            scaleBackAction.setTarget(item);
                            scaleBackAction.setDuration(0.025f);

                            item.addAction(sequenceAction);

                            if (((ConsumableItem) item).consume()) {
                                items.remove(item);
                                refreshWindow(items, itemsTable);
                            }
                        }
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
