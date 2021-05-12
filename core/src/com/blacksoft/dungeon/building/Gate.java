package com.blacksoft.dungeon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.GateOpenCheckAction;
import com.blacksoft.dungeon.actions.build.PlaceTreasuryAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class Gate implements Building {

    public int level = 1;
    private boolean opened = false;

    private static TextureRegion openedTextureRegion;
    private static TextureRegion closedTextureRegion;

    static {
        openedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/GateOpened.png")));
        closedTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/GateClosed.png")));
    }

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return level <= 4 && PlaceTreasuryAction.class.isAssignableFrom(action.getClass());
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.GATE_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);

        GameState.stage.addAction(new GateOpenCheckAction(this, x, y));
    }

    @Override
    public void upgrade() {
        GameState.loopProgress += Config.GATE_PROGRESS_VALUE;
        level++;
    }

    @Override
    public void destroy() {
    }

    @Override
    public Tile getTile() {
        return opened ? Tile.GateClosed : Tile.GateOpened;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
    }

    @Override
    public boolean getState() {
        return opened;
    }

    @Override
    public void toggleState() {
        opened = !opened;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return opened ? openedTextureRegion : closedTextureRegion;
    }
}