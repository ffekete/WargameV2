package com.mygdx.mechwargame.core.item.weapon.agmissile;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class AirToGroundMissile extends Weapon {

    public AirToGroundMissile() {
        super(AssetManagerV2.AG_MISSILE);
        name = "AG missile";
        longName = "air to ground missile";
        damage = 1;
        range = 4;
        rateOfFire = 4;
        ammo = 40;
        accuracy = 2;
        price = 1000;

        socket = Socket.AGMissile;

        addToolTip();
    }

}
