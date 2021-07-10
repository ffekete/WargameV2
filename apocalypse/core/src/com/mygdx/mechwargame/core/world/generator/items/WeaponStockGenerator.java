package com.mygdx.mechwargame.core.world.generator.items;

import com.badlogic.gdx.Gdx;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.item.armor.ArmorPlating;
import com.mygdx.mechwargame.core.item.weapon.*;
import com.mygdx.mechwargame.core.item.weapon.aamissile.StandardAirToAirMissile;
import com.mygdx.mechwargame.core.item.weapon.agmissile.StandardAirToGroundMissile;
import com.mygdx.mechwargame.core.item.weapon.laser.LargeLaserCannon;
import com.mygdx.mechwargame.core.item.weapon.laser.LaserCannon;
import com.mygdx.mechwargame.core.item.weapon.missile.LongRangeMissile;
import com.mygdx.mechwargame.core.item.weapon.missile.ShortRangeMissile;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WeaponStockGenerator {

    private static List<Class<? extends Weapon>> basicWeapons = Arrays.asList(
            LaserCannon.class,
            LargeLaserCannon.class,
            StandardAirToAirMissile.class,
            StandardAirToGroundMissile.class,
            LongRangeMissile.class,
            ShortRangeMissile.class
    );

    private static List<Class<? extends Weapon>> advancedWeapons = Arrays.asList(
            LaserCannon.class,
            LargeLaserCannon.class,
            StandardAirToAirMissile.class,
            StandardAirToGroundMissile.class,
            LongRangeMissile.class,
            ShortRangeMissile.class
    );

    private static List<Class<? extends Weapon>> rareWeapons = Arrays.asList(
            LaserCannon.class,
            LargeLaserCannon.class,
            StandardAirToAirMissile.class,
            StandardAirToGroundMissile.class,
            LongRangeMissile.class,
            ShortRangeMissile.class
    );

    public static void generate(int level,
                                List<Item> items) {
        Random random = new Random();

        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            Class<? extends Weapon> aClass = basicWeapons.get(random.nextInt(basicWeapons.size()));
            try {
                items.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                Gdx.app.log("error", "cannot instantiate weapon " + aClass);

            }
        }

        if(level > 1 && !advancedWeapons.isEmpty()) {
            for (int i = 0; i < random.nextInt(3) + 1; i++) {
                Class<? extends Weapon> aClass = advancedWeapons.get(random.nextInt(advancedWeapons.size()));
                try {
                    items.add(aClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    Gdx.app.log("error", "cannot instantiate weapon " + aClass);

                }
            }
        }

        if(level > 2 && !rareWeapons.isEmpty()) {
            for (int i = 0; i < random.nextInt(2); i++) {
                Class<? extends Weapon> aClass = rareWeapons.get(random.nextInt(rareWeapons.size()));
                try {
                    items.add(aClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    Gdx.app.log("error", "cannot instantiate weapon " + aClass);

                }
            }
        }

        for (int i = 0; i < random.nextInt(level * 2); i++) {
            items.add(new ArmorPlating());
        }
    }


}
