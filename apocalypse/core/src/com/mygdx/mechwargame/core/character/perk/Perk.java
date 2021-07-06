package com.mygdx.mechwargame.core.character.perk;

public interface Perk {

    String getName();
    String getDescription();

    boolean checkPrerequisites(); // can be perk, attribute level, skill level, character level
}
