package com.mygdx.mechwargame.core.item;

import com.mygdx.mechwargame.core.item.consumable.HydrogenCell;
import com.mygdx.mechwargame.core.item.modification.EnhancedSensors;
import com.mygdx.mechwargame.core.item.weapon.aamissile.StandardAirToAirMissile;
import com.mygdx.mechwargame.core.item.weapon.agmissile.StandardAirToGroundMissile;
import com.mygdx.mechwargame.core.item.weapon.gun.LargeLaserCannon;
import com.mygdx.mechwargame.core.item.weapon.gun.LaserCannon;
import com.mygdx.mechwargame.core.item.weapon.missile.LongRangeMissile;
import com.mygdx.mechwargame.core.item.weapon.missile.ShortRangeMissile;
import com.mygdx.mechwargame.core.item.modification.TargetingModule;

import java.util.Arrays;
import java.util.List;

public class ItemsRegistry {

    // add all items here so demands can be generated properly
    public static List<Class<? extends Item>> items = Arrays.asList(
            HydrogenCell.class,
            LargeLaserCannon.class,
            LaserCannon.class,
            StandardAirToAirMissile.class,
            StandardAirToGroundMissile.class,
            LongRangeMissile.class,
            ShortRangeMissile.class,
            TargetingModule.class,
            EnhancedSensors.class
    );

}
