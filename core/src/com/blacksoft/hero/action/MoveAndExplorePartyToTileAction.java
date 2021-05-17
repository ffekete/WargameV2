package com.blacksoft.hero.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.ResetCreatureActionsAction;
import com.blacksoft.dungeon.actions.TileCleaner;
import com.blacksoft.hero.Party;
import com.blacksoft.state.GameState;

public class MoveAndExplorePartyToTileAction extends MoveToAction {

    Vector2 targetNode;
    Vector2 previousNode = null;
    Party party;

    public MoveAndExplorePartyToTileAction(Party party,
                                           Vector2 targetNode) {
        super.actor = party;
        setPosition(targetNode.x, targetNode.y);
        setDuration(party.getSpeed());
        this.targetNode = targetNode;
        this.party = party;
    }

    @Override
    protected void begin() {
        super.begin();
    }

    @Override
    protected void end() {
        super.end();
        this.party.explored[(int)getX() / 16][(int) getY() / 16] = true;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.paused) {
            return false;
        }

        Party creature = (Party) actor;

        if (this.previousNode == null) {
            creature.previousNode = new Vector2(creature.getX() / 16, creature.getY() / 16);
            this.previousNode = creature.previousNode;
            creature.targetNode = new Vector2(targetNode.x / 16, targetNode.y / 16);
        }

        if(!TileCleaner.canTraverse(GameState.dungeon, (int)creature.targetNode.x,(int)creature.targetNode.y)) {
            creature.setPosition(previousNode.x * 16, previousNode.y * 16);
            creature.addAction(new ResetPartyActionsAction(creature));
            return true;
        }

        boolean result = super.act(delta);
        return result;
    }
}