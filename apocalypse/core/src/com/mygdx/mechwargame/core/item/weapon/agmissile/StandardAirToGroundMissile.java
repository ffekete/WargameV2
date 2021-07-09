package com.mygdx.mechwargame.core.item.weapon.agmissile;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class StandardAirToGroundMissile extends Weapon {

    public StandardAirToGroundMissile() {
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

        modes.add(Mode.SingleShot);

        addToolTip();
    }

}
