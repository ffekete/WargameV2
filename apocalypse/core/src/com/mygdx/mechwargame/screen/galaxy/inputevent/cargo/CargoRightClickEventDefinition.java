package com.mygdx.mechwargame.screen.galaxy.inputevent.cargo;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.core.item.consumable.ConsumableItem;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.state.GameData;

import java.util.List;

import static com.mygdx.mechwargame.screen.galaxy.inputevent.cargo.CargoClickEvent.refreshWindow;

public class CargoRightClickEventDefinition {

    static void rightClickDown(Item item) {
        if (item instanceof ConsumableItem) {

            ParallelAction sequenceAction = new ParallelAction();
            ScaleToAction scaleToAction = Actions.scaleTo(0.8f, 0.8f);

            MoveByAction moveByAction = new MoveByAction();
            moveByAction.setAmount(32 * 0.25f, 32 * 0.25f);

            sequenceAction.addAction(moveByAction);
            sequenceAction.addAction(scaleToAction);

            scaleToAction.setDuration(0.05f);

            item.addAction(sequenceAction);
        }
    }

    static void rightClickUp(Item item,
                             List<Item> items,
                             Table itemsTable) {
        if (item instanceof ConsumableItem) {

            ParallelAction parallelAction1 = new ParallelAction();
            ScaleToAction scaleBackAction = Actions.scaleTo(1f, 1f);

            parallelAction1.addAction(scaleBackAction);

            scaleBackAction.setDuration(0.025f);

            MoveByAction moveByAction = new MoveByAction();
            moveByAction.setAmount(-32 * 0.25f, -32 * 0.25f);

            parallelAction1.addAction(moveByAction);

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(parallelAction1);

            if (((ConsumableItem) item).consume()) {

                sequenceAction.addAction(Actions.fadeOut(0.5f));
                sequenceAction.addAction(Actions.removeActor());
                sequenceAction.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        GameData.starShip.cargoBay.removeItem(item);

                        refreshWindow(items, itemsTable);
                        return true;
                    }
                });

            }


            item.addAction(sequenceAction);
        }
    }

}
