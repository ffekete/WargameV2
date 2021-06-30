package com.mygdx.mechwargame.screen.galaxy.event;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.core.MovementPathEffect;
import com.mygdx.mechwargame.screen.action.MoveShipAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.util.MathUtils;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class MapClickEvent {

    public static void check(SequenceAction mainAction, float x, float y, Stage stage) {
        SequenceAction rotateAndMoveAction = new SequenceAction();

        float distance = (float) Math.abs(MathUtils.getDistance(GameData.starShip.getX(), GameData.starShip.getY(), x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f));
        float angle = MathUtils.getAngle(new Vector2(GameData.starShip.getX(), GameData.starShip.getY()), new Vector2(x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f));

        MovementPathEffect movementPathEffect = new MovementPathEffect((int)distance);
        movementPathEffect.setRotation(angle);
        stage.addActor(movementPathEffect);
        movementPathEffect.setSize(distance, 128);
        movementPathEffect.setPosition(GameData.starShip.getX() + SECTOR_SIZE / 2f, GameData.starShip.getY()+ SECTOR_SIZE / 2f);

        ParallelAction doThemTogetherAction = new ParallelAction();

        RotateToAction rotateToAction = new RotateToAction();

        rotateToAction.setRotation(angle);
        rotateToAction.setActor(GameData.starShip);

        MoveShipAction moveToAction = new MoveShipAction(GameData.starShip);
        moveToAction.setActor(GameData.starShip);
        moveToAction.setDuration( GameData.starShip.engine.getSpeed() * 0.01f * distance);
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

}
