package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public abstract class Weapon extends Item {

    public int damage;
    public int range;
    public int rateOfFire;
    public int ammo;
    public int accuracy; // roll accuracy against armor, if this roll is bigger, hit

    public String name;

    Socket socket;

    public Weapon(String image) {
        super(image);
    }
}
