package com.mygdx.mechwargame.core.ship;

import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class BaseShip extends BaseActor {

    public float fuelConsumption = 5f;
    public float fuel = 200;
    public float maxFuel = 200;
    public int hull = 150;
    public int maxHull = 200;

    public BaseShip(LayeredAnimatedImage layeredAnimatedImage) {
        super(layeredAnimatedImage);
    }

    public void consumeFuel() {
        this.fuel -= fuelConsumption;
        if (this.fuel < 0) {
            this.fuel = 0;
        }
    }
}
