package com.mygdx.mechwargame.core.world;

import java.util.*;

public class GalaxySetupParameters {

    public int width;
    public int height;
    public int defaultSize = 1;

    public int numberOfStars = 50;
    public int numberOfStarsMultiplier = 1;

    public int numberOfPirates = 5;
    public int minNumberOfPirates = 0;
    public int maxNumberOfPirates = 10;

    public int minNumberOfFactions = 4;
    public int maxNumberOfFactions = 8;
    public int defaultNumberOfFactions = 4;

    public int factionStrength = 20;
    public int pirateStrength = 1;


    public int seed = new Random().nextInt(10000000);

    public Map<Integer, String> sizes;

    public GalaxySetupParameters() {
        sizes = new HashMap<>();
        sizes.put(1, "small");
        sizes.put(2, "medium");
        sizes.put(3, "large");
        sizes.put(4, "huge");

        width = 40;
        height = 20;
    }

    public int nextFactionsCount() {
        defaultNumberOfFactions++;
        if(defaultNumberOfFactions > maxNumberOfFactions) {
            defaultNumberOfFactions = minNumberOfFactions;
        }

        return defaultNumberOfFactions;
    }

    public int previousFactionsCount() {
        defaultNumberOfFactions--;
        if(defaultNumberOfFactions < minNumberOfFactions) {
            defaultNumberOfFactions = maxNumberOfFactions;
        }

        return defaultNumberOfFactions;
    }

    public int nextPiratesCount() {
        numberOfPirates++;
        if(numberOfPirates > maxNumberOfPirates) {
            numberOfPirates = minNumberOfPirates;
        }

        return numberOfPirates;
    }

    public int previousPiratesCount() {
        numberOfPirates--;
        if(numberOfPirates < minNumberOfPirates) {
            numberOfPirates = maxNumberOfPirates;
        }

        return numberOfPirates;
    }

    public int nextStarsMultiplier() {
        numberOfStarsMultiplier++;
        if(numberOfStarsMultiplier > 4) {
            numberOfStarsMultiplier = 1;
        }

        return numberOfStarsMultiplier;
    }

    public int previousStarsMultiplier() {
        numberOfStarsMultiplier--;
        if(numberOfStarsMultiplier < 1) {
            numberOfStarsMultiplier = 4;
        }

        return numberOfStarsMultiplier;
    }

    public int nextSize() {
        defaultSize++;
        if(defaultSize > 4) {
            defaultSize = 1;
        }

        maxNumberOfFactions = 7 + defaultSize;
        if (defaultNumberOfFactions > maxNumberOfFactions) {
            defaultNumberOfFactions = maxNumberOfFactions;
        }

        numberOfStars = 50 * defaultSize;

        return defaultSize;
    }

    public int previousSize() {
        defaultSize--;
        if(defaultSize < 0) {
            defaultSize = 4;
        }

        maxNumberOfFactions = 7 + defaultSize;
        if (defaultNumberOfFactions > maxNumberOfFactions) {
            defaultNumberOfFactions = maxNumberOfFactions;
        }

        numberOfStars = 50 * defaultSize;

        return defaultSize;
    }
}
