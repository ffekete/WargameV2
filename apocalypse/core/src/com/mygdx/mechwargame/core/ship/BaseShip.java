package com.mygdx.mechwargame.core.ship;

import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.core.ship.component.armor.Armor;
import com.mygdx.mechwargame.core.ship.component.armor.StandardHullArmor;
import com.mygdx.mechwargame.core.ship.component.cargo.CargoBay;
import com.mygdx.mechwargame.core.ship.component.energy.EnergyGrid;
import com.mygdx.mechwargame.core.ship.component.engine.Engine;
import com.mygdx.mechwargame.core.ship.component.engine.StandardEngine;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class BaseShip extends BaseActor {

    public EnergyGrid energyGrid;
    public CargoBay cargoBay;
    public Armor hullArmor;
    public Engine engine;

    public BaseShip(LayeredAnimatedImage layeredAnimatedImage) {
        super(layeredAnimatedImage);
        this.engine = new StandardEngine(10);
        this.hullArmor = new StandardHullArmor(1);
    }

    public void consumeFuel() {
        this.engine.consumeFuel();
    }
}
