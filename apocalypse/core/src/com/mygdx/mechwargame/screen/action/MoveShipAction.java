package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygdx.mechwargame.core.ship.BaseShip;

public class MoveShipAction extends MoveToAction {

    private float fuelConsumptionDelay = 0;
    private BaseShip starShip;

    public MoveShipAction(BaseShip starShip) {
        this.starShip = starShip;
    }

    @Override
    public boolean act(float delta) {
        fuelConsumptionDelay += delta;

        if(starShip.engine.getFuel() <= 0) {
            return true;
        }

        if (fuelConsumptionDelay >= 0.5f) {
            starShip.consumeFuel();
            fuelConsumptionDelay = 0;
        }
        return super.act(delta);
    }
}
