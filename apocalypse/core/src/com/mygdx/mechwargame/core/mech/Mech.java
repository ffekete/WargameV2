package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.core.weapon.Weapon;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class Mech {

    LayeredAnimatedImage mechImage;

    public int armor;
    public Weapon primaryWeapon;
    public Weapon secondaryWeapon;
    public int movementPoints;
    public int initiative;
    public int hp;

    public LayeredAnimatedImage getMechImage() {
        return mechImage;
    }

    public abstract String getDescription();
}
