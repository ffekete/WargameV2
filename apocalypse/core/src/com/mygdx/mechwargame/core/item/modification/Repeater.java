package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class Repeater extends Modification {

    public Repeater() {
        super(AssetManagerV2.REPEATER);
        name = "repeater";
        shortName = "rpt";
        price = 800;
        shortDescription = "+1 rate of fire\n" +
                "slot: laser";
        description =
                "+1 rate of fire.\n" +
                        "slot: laser\n\n" +
                        "A chip to make it easy to repeat laser beams in a rapid burst.";
        addToolTip();
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.Laser;
    }

    @Override
    public void apply(Weapon weapon) {
        super.apply(weapon);
        if (weapon.rateOfFire < Config.MAX_WEAPON_STAT_LEVEL) {
            weapon.rateOfFire++;
            weapon.modes.add(Mode.Burst);
        }
    }

    @Override
    public void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.modes.remove(Mode.Burst);
        weapon.rateOfFire--;
    }
}
