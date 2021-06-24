package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public abstract class Weapon {

    public int damage;
    public int range;
    public int rateOfFire;
    public int ammo;
    public int accuracy; // roll accuracy against armor, if this roll is bigger, hit

    public LayeredAnimatedImage icon;

    public String name;

}
