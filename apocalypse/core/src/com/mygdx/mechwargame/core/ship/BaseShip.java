package com.mygdx.mechwargame.core.ship;

import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.core.ship.component.armor.Armor;
import com.mygdx.mechwargame.core.ship.component.cargo.CargoBay;
import com.mygdx.mechwargame.core.ship.component.energy.EnergyGrid;
import com.mygdx.mechwargame.core.ship.component.engine.Engine;
import com.mygdx.mechwargame.core.ship.component.mechbay.MechBay;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class BaseShip extends BaseActor {

    public EnergyGrid energyGrid;
    public CargoBay cargoBay;
    public Armor hullArmor;
    public Engine engine;
    public MechBay mechBay;

    public String name;
    public String modelName;

    public BaseShip(LayeredAnimatedImage layeredAnimatedImage, String modelName) {
        super(layeredAnimatedImage);
        this.modelName = modelName;
    }

    public void consumeFuel() {
        this.engine.consumeFuel();
    }
}
