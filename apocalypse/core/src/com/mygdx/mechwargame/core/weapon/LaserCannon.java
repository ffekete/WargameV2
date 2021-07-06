package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class LaserCannon extends Weapon {

    public LaserCannon() {
        super(AssetManagerV2.LASER_CANNON);
        name = "laser cannon";
        damage = 1;
        range = 5;
        rateOfFire = 1;
        ammo = 10;
        accuracy = 2;

        socket = Socket.Laser;
    }

}
