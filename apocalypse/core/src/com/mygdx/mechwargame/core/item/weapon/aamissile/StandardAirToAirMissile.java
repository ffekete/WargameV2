package com.mygdx.mechwargame.core.item.weapon.aamissile;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class StandardAirToAirMissile extends Weapon {

    public StandardAirToAirMissile() {
        super(AssetManagerV2.AA_MISSILE);
        name = "AA missile";
        longName = "air to air missile";
        damage = 1;
        range = 4;
        rateOfFire = 4;
        ammo = 20;
        accuracy = 3;
        price = 1000;

        modes.add(Mode.SingleShot);

        socket = Socket.AAMissile;

        addToolTip();
    }

}
