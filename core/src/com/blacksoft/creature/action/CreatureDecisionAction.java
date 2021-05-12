package com.blacksoft.creature.action;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.Node;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.state.GameState;

public class CreatureDecisionAction extends Action {

    float duration = 0f;
    private IndexedAStarPathFinder<Node> pathFinder = new IndexedAStarPathFinder<>(GameState.cityGraph);

    @Override
    public boolean act(float delta) {

        duration += delta;

        Creature creature = (Creature) this.actor;

        if (!creature.finishedAllActions) {
            duration = 0f; // reset the counter
        }

        if (duration >= 0.5f) {


            // REST
            if (creature.fatigue <= 0) {
                // look for resting place
                Vector2 target = TileFinder.findNearest(creature.getX() / 16, creature.getY() / 16, Tile.RestingArea);

                if (target != null) {
                    // find a way
                    GraphPath<Node> path = GameState.cityGraph.findPath(GameState.dungeon.getNode(creature.getX() / 16, creature.getY() / 16),
                            GameState.dungeon.getNode(target.x, target.y));

                    resetCreatureActions(creature);
                    creature.finishedAllActions = false;
                    for (int i = 1; i < path.getCount(); i++) {
                        Node node = path.get(i);
                        MoveToTileAction moveToTileAction = new MoveToTileAction(creature, new Vector2(node.x * 16, node.y * 16));
                        moveToTileAction.setActor(creature);
                        creature.sequenceActions.addAction(moveToTileAction);
                    }
                    creature.sequenceActions.addAction(new RestAction(creature));
                    creature.sequenceActions.addAction(new ResetCreatureActionsAction(creature));
                }

            }
            // WANDER
            else {
                resetCreatureActions(creature);
                creature.finishedAllActions = false;
                creature.sequenceActions.addAction(new WanderingAction(creature));
            }

            duration = 0;
        }


        return false;
    }

    public static void resetCreatureActions(Creature creature) {
        creature.sequenceActions.reset();
        creature.addAction(creature.sequenceActions);
        creature.finishedAllActions = true;
    }
}
