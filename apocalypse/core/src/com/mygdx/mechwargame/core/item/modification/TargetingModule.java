package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class TargetingModule extends Modification {

    public TargetingModule() {
        super(AssetManagerV2.TARGETING_MODULE);
        name = "targeting module";
        price = 800;
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.AAMissile
                || socket == Socket.AGMissile
                || socket == Socket.LargeMissile
                || socket == Socket.SmallMissile;
    }

    @Override
    void apply(Weapon weapon) {
        super.apply(weapon);
        if(weapon.accuracy < Config.MAX_WEAPON_STAT_LEVEL) {
            weapon.accuracy++;
        }
    }

    @Override
    void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.accuracy--;
    }
}
