package com.mygdx.mechwargame.core.item.weapon.gun;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.modification.EnhancedSensors;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class LaserCannon extends Weapon {

    public LaserCannon() {
        super(AssetManagerV2.LASER_CANNON);
        name = "laser cannon";
        longName = "laser cannon";
        damage = 1;
        range = 5;
        rateOfFire = 1;
        ammo = 10;
        accuracy = 2;
        price = 1000;

        socket = Socket.Gun;

        modes.add(Mode.SingleShot);

        modification = new EnhancedSensors();

        addToolTip();
    }

}
