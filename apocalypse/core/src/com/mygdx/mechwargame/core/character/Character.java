package com.mygdx.mechwargame.core.character;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.HashMap;
import java.util.Map;

public class Character {

    public String firstName;
    public String lastName;
    public String nickName;
    public Image portrait;
    public Map<Attributes, Integer> attributeValues = new HashMap<>();
    public Map<Skills, Integer> skillValues = new HashMap<>();

    public Character() {
        attributeValues.put(Attributes.Endurance, 0);
        attributeValues.put(Attributes.Perception, 0);
        attributeValues.put(Attributes.Reflexes, 0);
        attributeValues.put(Attributes.HandEyeCoordination, 0);

        skillValues.put(Skills.Lasers, 1);
        skillValues.put(Skills.Missiles, 1);
        skillValues.put(Skills.Guns, 1);
        skillValues.put(Skills.Melee, 1);
        skillValues.put(Skills.Evasion, 1);
        skillValues.put(Skills.Piloting, 1);
    }
}
