package com.mygdx.mechwargame.core.world.generator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechwargame.core.faction.Faction;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.state.GameData;

import java.util.*;

public class FactionDistributor {

    public static Random random;

    public static void distribute(GalaxySetupParameters galaxySetupParameters) {

        List<Vector2> startingPoints = new ArrayList<>();
        Map<Faction, Vector2> factions = new HashMap<>();
        Map<Faction, Integer> factionStrengths = new HashMap<>();

        int galaxyWidth = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        int galaxyHeight = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        int strength = galaxySetupParameters.factionStrength * galaxySetupParameters.defaultSize;

        // assign starting stars
        for (int i = 0; i < galaxySetupParameters.defaultNumberOfFactions; i++) {
            int x, y;
            do {
                x = random.nextInt(galaxyWidth);
                y = random.nextInt(galaxyHeight);

            } while (startingPoints.contains(new Vector2(x, y)) || GameData.galaxy.sectors[x][y].stars.isEmpty());

            Faction faction = new Faction("Faction " + i, Color.WHITE);
            GameData.galaxy.sectors[x][y].sectorOwnerArea.owner = faction;
            factionStrengths.put(faction, random.nextInt(strength / 2)  + strength / 2);

            startingPoints.add(new Vector2(x, y));
            factions.put(faction, new Vector2(x, y));
        }

        // expand
        for (int i = 0; i < strength; i++) {
            for (Map.Entry<Faction, Vector2> entry : factions.entrySet()) {

                if (factionStrengths.get(entry.getKey()) < i) {
                    continue;
                }
                Vector2 startingPoint = entry.getValue();
                spread(entry.getKey(), startingPoint);
            }
        }

    }

    private static void spread(Faction faction,
                               Vector2 startingPoint) {

        List<Vector2> possibilities = new ArrayList<>();
        Deque<Vector2> starts = new ArrayDeque<>();

        starts.add(startingPoint);

        while (!starts.isEmpty()) {

            Vector2 next = starts.pop();

            // look for free sectors
            if (next.x > 0 && GameData.galaxy.sectors[(int) next.x - 1][(int) next.y].sectorOwnerArea.owner == null) {
                possibilities.add(new Vector2(next.x - 1, next.y));
            }
            if (next.y > 0 && GameData.galaxy.sectors[(int) next.x][(int) next.y - 1].sectorOwnerArea.owner == null) {
                possibilities.add(new Vector2(next.x, next.y - 1));
            }
            if (next.x < GameData.galaxy.width - 1 && GameData.galaxy.sectors[(int) next.x + 1][(int) next.y].sectorOwnerArea.owner == null) {
                possibilities.add(new Vector2(next.x + 1, next.y));
            }
            if (next.y < GameData.galaxy.height - 1 && GameData.galaxy.sectors[(int) next.x][(int) next.y + 1].sectorOwnerArea.owner == null) {
                possibilities.add(new Vector2(next.x, next.y + 1));
            }

            List<Vector2> shuffledStarts = new ArrayList<>();

            // find new ones if this one is occupied by current faction already
            if (next.x > 0 && GameData.galaxy.sectors[(int) next.x - 1][(int) next.y].sectorOwnerArea.owner == faction) {
                shuffledStarts.add(new Vector2(next.x - 1, next.y));
            }
            if (next.y > 0 && GameData.galaxy.sectors[(int) next.x][(int) next.y - 1].sectorOwnerArea.owner == faction) {
                shuffledStarts.add(new Vector2(next.x, next.y - 1));
            }
            if (next.x < GameData.galaxy.width - 1 && GameData.galaxy.sectors[(int) next.x + 1][(int) next.y].sectorOwnerArea.owner == faction) {
                shuffledStarts.add(new Vector2(next.x + 1, next.y));
            }
            if (next.y < GameData.galaxy.height - 1 && GameData.galaxy.sectors[(int) next.x][(int) next.y + 1].sectorOwnerArea.owner == faction) {
                shuffledStarts.add(new Vector2(next.x, next.y + 1));
            }

            Collections.shuffle(shuffledStarts, random);
            starts.addAll(shuffledStarts);

            if (possibilities.size() > 0) {
                Vector2 selected = possibilities.get(random.nextInt(possibilities.size()));
                GameData.galaxy.sectors[(int) selected.x][(int) selected.y].sectorOwnerArea.owner = faction;
                return;
            }
        }
    }

}
