package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class AirToAirMissile extends Weapon {

    public AirToAirMissile() {
        super(AssetManagerV2.AA_MISSILE);
        name = "AA missile";
        damage = 1;
        range = 4;
        rateOfFire = 4;
        ammo = 20;
        accuracy = 3;

        socket = Socket.AAMissile;
    }

}
