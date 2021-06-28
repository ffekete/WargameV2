package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.faction.Faction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class SectorOwnerArea {

    public Faction owner;
    public int x, y;

    public void draw(SpriteBatch spriteBatch) {

        Sector[][] sectors = GameData.galaxy.sectors;
        Faction owner = sectors[x][y].sectorOwnerArea.owner;

        if (owner == null) {
            return;
        }

        int n = 0;
        Galaxy galaxy = GameData.galaxy;

        if (x > 0 && sectors[x - 1][y].sectorOwnerArea.owner == owner) {
            n += 1;
        }

        if (y < galaxy.height - 1 && sectors[x][y + 1].sectorOwnerArea.owner == owner) {
            n += 2;
        }

        if (x < galaxy.width - 1 && sectors[x + 1][y].sectorOwnerArea.owner == owner) {
            n += 4;
        }

        if (y > 0 && sectors[x][y - 1].sectorOwnerArea.owner == owner) {
            n += 8;
        }

        Texture texture = GameState.assetManager.get(AssetManagerV2.AREA_LAYER);

        TextureRegion textureRegion = new TextureRegion(texture);


        switch (n) {
            case 0: {
                textureRegion.setRegion(96, 96, 32, 32);
                break;
            }
            case 1: {
                textureRegion.setRegion(32 * 2, 32 * 3, 32, 32);
                break;
            }
            case 2: {
                textureRegion.setRegion(32 * 3, 32 * 2, 32, 32);
                break;
            }
            case 3: {
                textureRegion.setRegion(32 * 2, 32 * 2, 32, 32);
                break;
            }
            case 4: {
                textureRegion.setRegion(32 * 0, 32 * 3, 32, 32);
                break;
            }
            case 5: {
                textureRegion.setRegion(32 * 1, 32 * 3, 32, 32);
                break;
            }
            case 6: {
                textureRegion.setRegion(32 * 0, 32 * 2, 32, 32);
                break;
            }
            case 7: {
                textureRegion.setRegion(32 * 1, 32 * 2, 32, 32);
                break;
            }
            case 8: {
                textureRegion.setRegion(32 * 3, 32 * 0, 32, 32);
                break;
            }
            case 9: {
                textureRegion.setRegion(32 * 2, 32 * 0, 32, 32);
                break;
            }
            case 10: {
                textureRegion.setRegion(32 * 3, 32 * 1, 32, 32);
                break;
            }
            case 11: {
                textureRegion.setRegion(32 * 2, 32 * 1, 32, 32);
                break;
            }
            case 12: {
                textureRegion.setRegion(32 * 0, 32 * 0, 32, 32);
                break;
            }
            case 13: {
                textureRegion.setRegion(32 * 1, 32 * 0, 32, 32);
                break;
            }
            case 14: {
                textureRegion.setRegion(32 * 0, 32 * 1, 32, 32);
                break;
            }
            case 15: {
                textureRegion.setRegion(32 * 1, 32 * 1, 32, 32);
                break;
            }
        }

        spriteBatch.draw(textureRegion, x * SECTOR_SIZE, y * SECTOR_SIZE, SECTOR_SIZE, SECTOR_SIZE);
    }

}
