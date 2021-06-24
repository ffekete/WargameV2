package com.mygdx.mechwargame.core;

import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class Weapon {

    public int damage;
    public int range;
    public int rateOfFire;
    public int ammo;

    public abstract LayeredAnimatedImage getIcon();

}
