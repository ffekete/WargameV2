package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public abstract class Modification extends Item {

    public Modification(String image) {
        super(image);
        order = Config.MODIFICATION_ORDER;
    }

    public abstract boolean canBeAppliedTo(Socket socket);

    public void apply(Weapon weapon) {

    }

    public void remove(Weapon weapon) {

    }

}
