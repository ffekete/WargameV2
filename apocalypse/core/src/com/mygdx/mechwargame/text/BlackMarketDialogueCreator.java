package com.mygdx.mechwargame.text;

import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;

public class BlackMarketDialogueCreator {

    public static final String DIALOGUE =
            "There is a black market right down the street.\n" +
                    "Let's have a look, but keep your weapons ready!";

    public static String generate(Star star, Sector sector) {
        return DIALOGUE;
    }

}
