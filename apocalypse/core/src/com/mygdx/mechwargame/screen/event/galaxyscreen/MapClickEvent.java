package com.mygdx.mechwargame.screen.event.galaxyscreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.util.MathUtils;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class MapClickEvent {

    public static Action check(float x, float y) {

        SequenceAction rotateAndMoveAction = new SequenceAction();

        ParallelAction doThemTogetherAction = new ParallelAction();

        RotateToAction rotateToAction = new RotateToAction();
        float angle = MathUtils.getAngle(new Vector2(GameData.starShip.getX(), GameData.starShip.getY()), new Vector2(x, y));
        rotateToAction.setRotation(angle);
        rotateToAction.setActor(GameData.starShip);

        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setActor(GameData.starShip);
        moveToAction.setDuration(0.01f * (float) Math.abs(MathUtils.getDistance(GameData.starShip.getX(), GameData.starShip.getY(), x, y)));
        moveToAction.setPosition(x - SECTOR_SIZE / 2f, y - SECTOR_SIZE / 2f);

        doThemTogetherAction.addAction(rotateToAction);
        doThemTogetherAction.addAction(moveToAction);

        rotateAndMoveAction.addAction(doThemTogetherAction);

        if (GameData.galaxyMapPlayerAction != null) {
            // remove previous action
            GameData.starShip.getActions().removeValue(GameData.galaxyMapPlayerAction, true);
        }

        GameData.galaxyMapPlayerAction = rotateAndMoveAction;

        return rotateAndMoveAction;
    }

}
