package com.mygdx.mechwargame.core.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

public abstract class BaseUnit extends Image {

    public int armor;
    public int maxArmor;

    public Weapon primaryWeapon;
    public Socket primaryWeaponSocket;
    public Socket secondaryWeaponSocket;
    public Weapon secondaryWeapon;

    public int movementPoints;
    public int initiative;
    public int hp;
    public String name;

    public UnitType unitType;

    protected String idleImagePath;
    protected AnimatedDrawable idleDrawable;

    public AnimatedDrawable copyIdleDrawable() {
        return new AnimatedDrawable(idleImagePath, 32, 32, true, 0.3f, 0.5f);
    }

    public abstract String getDescription();


}
