package com.mygdx.mechwargame.core.ship;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.ship.component.armor.StandardHullArmor;
import com.mygdx.mechwargame.core.ship.component.cargo.StandardCargoBay;
import com.mygdx.mechwargame.core.ship.component.energy.StandardEnergyGrid;
import com.mygdx.mechwargame.core.ship.component.engine.StandardEngine;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class SmallStarShip extends BaseShip {

    public SmallStarShip() {
        super(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.SHIP, 32, 32, 0.15f)), "small starship");

        this.engine = new StandardEngine(1);
        this.hullArmor = new StandardHullArmor(1);
        this.cargoBay = new StandardCargoBay(1);
        this.energyGrid = new StandardEnergyGrid(1);
    }

}
