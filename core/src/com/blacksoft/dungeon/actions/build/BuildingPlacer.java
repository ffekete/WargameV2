package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.blacksoft.ActionsHandler;
import com.blacksoft.build.UserAction;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorUpdater;
import com.blacksoft.dungeon.actions.ui.CleanIndicatorsAction;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.dungeon.building.BloodPool;
import com.blacksoft.dungeon.building.Graveyard;
import com.blacksoft.dungeon.building.Torch;
import com.blacksoft.dungeon.building.Treasury;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

import java.util.Random;

import static com.blacksoft.state.Config.MAP_HEIGHT;
import static com.blacksoft.state.Config.MAP_WIDTH;
import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class BuildingPlacer {

    public static boolean canPlace(int x,
                                   int y,
                                   Class<?> clazz) {
        int vx = x / TEXTURE_SIZE;
        int vy = y / TEXTURE_SIZE;

        if (vx >= 0 && vx < MAP_WIDTH && vy >= 0 && vy < MAP_HEIGHT) {

            if (GameState.userAction == UserAction.Place) {
                if (clazz == Graveyard.class ||
                        clazz == BloodPool.class ||
                        clazz == Treasury.class) {
                    if (TileCleaner.isClean(GameState.dungeon, vx, vy) || canUpgrade(vx, vy)) {
                        return true;
                    }
                }

                if (clazz == Torch.class) {
                    if (TileCleaner.isRockWithCorner(GameState.dungeon, vx, vy) || canUpgrade(vx, vy)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean canUpgrade(int x,
                                     int y) {

        if (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT) {

            if (GameState.userAction == UserAction.Place) {
                if (GameState.dungeon.nodes[x][y].canUpgradeBy(GameState.selectedAction)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void place(int x,
                             int y) {
        int vx = x / TEXTURE_SIZE;
        int vy = y / TEXTURE_SIZE;

        if (BuildingPlacer.canPlace(x, y, GameState.selectedAction.getActionResultClass())) {

            GameState.selectedAction.execute();

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.scaleTo(3f, 3f));

            if (GameState.dungeon.nodes[vx][vy].building == null) { // build a new one
                GameState.dungeon.nodes[vx][vy].building = GameState.currentBuilding;
                GameState.dungeon.replaceTile(vx, vy, GameState.currentBuilding.getTile());
                GameState.dungeon.nodes[vx][vy].building.place(x, y);
            } else {
                // upgrade building
                GameState.dungeon.nodes[vx][vy].building.upgrade();
            }

            sequenceAction.addAction(Actions.scaleTo(1f, 1f, 0.2f));
            UIFactory.I.createFloatingLabel(GameState.selectedAction.getProgressAmount(), x / TEXTURE_SIZE * TEXTURE_SIZE, y / TEXTURE_SIZE * TEXTURE_SIZE);
            sequenceAction.addAction(Actions.removeActor());
            GameState.selectedActionImage.addAction(sequenceAction);

            GameState.selectedAction = null;
            GameState.userAction = UserAction.Clean;

            CleanIndicatorsAction.cleanAll(GameState.dungeon);
            CleanIndicatorUpdater.update(GameState.dungeon);

            // draw new action card
            AbstractAction action = ActionsHandler.I.clone(GameState.unlockedActionsPrioritized.get(new Random().nextInt(GameState.unlockedActionsPrioritized.size())));
            GameState.actions.add(action);
            UIFactory.I.addAction(UIState.actionsGroup, action);
        }
    }

}