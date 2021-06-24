package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.core.Weapon;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class Mech {

    LayeredAnimatedImage mechImage;

    public int armor;
    public Weapon weapon;
    public int movementPoints;
    public int initiative;

    public LayeredAnimatedImage getMechImage() {
        return mechImage;
    }

    public abstract String getDescription();
}
