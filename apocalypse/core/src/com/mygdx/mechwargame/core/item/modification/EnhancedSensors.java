package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class EnhancedSensors extends Modification {

    public EnhancedSensors() {
        super(AssetManagerV2.ENHANCED_SENSORS_MODULE);
        name = "enhanced sensors";
        shortName = "ens";
        price = 800;
        shortDescription = "+1 weapon range\n" +
                "slots: all missiles";
        description =
                "+1 weapon range.\n" +
                        "slots: all missiles\n\n" +
                        "Simple chip that enhances the maximum range\n" +
                        "of the missile launcher it is applied to.\n";
        addToolTip();
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.AAMissile
                || socket == Socket.AGMissile
                || socket == Socket.LargeMissile
                || socket == Socket.SmallMissile;
    }

    @Override
    public void apply(Weapon weapon) {
        super.apply(weapon);
        if (weapon.range < Config.MAX_WEAPON_STAT_LEVEL) {
            weapon.range++;
        }
    }

    @Override
    public void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.range--;
    }
}
