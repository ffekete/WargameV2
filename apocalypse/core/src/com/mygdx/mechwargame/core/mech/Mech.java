package com.mygdx.mechwargame.core.mech;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.mechwargame.core.weapon.Weapon;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

public abstract class Mech extends Image {

    public int armor;
    public Weapon primaryWeapon;
    public Weapon secondaryWeapon;
    public int movementPoints;
    public int initiative;
    public int hp;
    public String name;

    protected String idleImagePath;
    protected AnimatedDrawable idleDrawable;

    public AnimatedDrawable copyIdleDrawable() {
        return new AnimatedDrawable(idleImagePath, 32, 32, true, 0.3f, 0.5f);
    }

    public abstract String getDescription();


}
