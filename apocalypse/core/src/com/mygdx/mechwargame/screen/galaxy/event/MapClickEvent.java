package com.mygdx.mechwargame.screen.galaxy.event;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.MovementPathEffect;
import com.mygdx.mechwargame.screen.action.MoveShipAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.util.MathUtils;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class MapClickEvent {

    public static void check(SequenceAction mainAction,
                             float x,
                             float y,
                             Stage stage) {

        if(x < 0 || y < 0 || x >= Config.SCREEN_WIDTH * SECTOR_SIZE || y >= Config.SCREEN_HEIGHT * SECTOR_SIZE) {
            return;
        }

        SequenceAction rotateAndMoveAction = new SequenceAction();

        float distance = (float) Math.abs(MathUtils.getDistance(GameData.starShip.getX() , GameData.starShip.getY(), x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f));
        float angle = MathUtils.getAngle(new Vector2(GameData.starShip.getX(), GameData.starShip.getY()), new Vector2(x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f));

        addMovementPathEffect(x, y, stage, distance, angle);

        ParallelAction doThemTogetherAction = new ParallelAction();

        RotateToAction rotateToAction = new RotateToAction();

        rotateToAction.setRotation(angle);
        rotateToAction.setActor(GameData.starShip);

        MoveShipAction moveToAction = new MoveShipAction(GameData.starShip);
        moveToAction.setActor(GameData.starShip);
        moveToAction.setDuration(GameData.starShip.engine.getSpeed() * 0.01f * distance);
        moveToAction.setPosition(x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f);

        doThemTogetherAction.addAction(rotateToAction);
        doThemTogetherAction.addAction(moveToAction);

        rotateAndMoveAction.addAction(doThemTogetherAction);

        mainAction.addAction(rotateAndMoveAction);

        if (GameData.galaxyMapPlayerAction != null) {
            // remove previous action
            GameData.starShip.getActions().removeValue(GameData.galaxyMapPlayerAction, true);
        }

        GameData.galaxyMapPlayerAction = mainAction;
    }

    private static void addMovementPathEffect(float x,
                                  float y,
                                  Stage stage,
                                  float distance,
                                  float angle) {
        MovementPathEffect movementPathEffect = new MovementPathEffect((int) distance);
        movementPathEffect.setRotation(angle);
        stage.addActor(movementPathEffect);
        movementPathEffect.toBack();
        movementPathEffect.setSize(distance, SECTOR_SIZE);
        movementPathEffect.setPosition(GameData.starShip.getX() + SECTOR_SIZE / 2f, GameData.starShip.getY() + SECTOR_SIZE / 2f);

        if (GameData.movementPathEffect != null) {
            stage.getActors().removeValue(GameData.movementPathEffect, true);
        }
        GameData.movementPathEffect = movementPathEffect;

        movementPathEffect.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                float newDistance = (float) Math.abs(MathUtils.getDistance(GameData.starShip.getX(), GameData.starShip.getY(), x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f));
                float fullLength = movementPathEffect.fullLength - SECTOR_SIZE;

                movementPathEffect.srcX = SECTOR_SIZE / 2 + (int) (fullLength - fullLength * newDistance / distance);
                return false;
            }
        });
    }

}
