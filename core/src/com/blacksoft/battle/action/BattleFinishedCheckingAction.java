package com.blacksoft.battle.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.blacksoft.battle.BattlePhase;
import com.blacksoft.creature.Creature;
import com.blacksoft.dungeon.phase.GamePhase;
import com.blacksoft.hero.Party;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.user.actions.UserAction;

import java.util.List;

public class BattleFinishedCheckingAction extends Action {

    private Party party;
    private List<Creature> creatures;

    public BattleFinishedCheckingAction(Party party,
                                        List<Creature> creatures) {
        this.party = party;
        this.creatures = creatures;
    }

    @Override
    public boolean act(float delta) {

        if(GameState.battlePhase != BattlePhase.FinishTurn) {
            return false;
        }

        if(creatures.stream().noneMatch(creature -> creature.hp > 0)) {
            UIState.battleScreen.remove();
            GameState.paused = false;
            UIState.battleSelectionCursor.setVisible(false);
            GameState.battleSkillIcons.clear();
            GameState.battleImages.clear();
            GameState.battleSelectedCreature = null;
            GameState.nextBattleAction = null;
            GameState.isCombatSequence = false;

            GameState.nextAttackTarget = null;
            GameState.nextAttackTargetImage = null;

            GameState.battlePhase = BattlePhase.FinishTurn; // just for sure

            return true;
        }

        if(this.party.heroes.isEmpty()) {

            GameState.battleSkillIcons.clear();
            GameState.battleImages.clear();
            GameState.battleSelectedCreature = null;
            GameState.nextBattleAction = null;

            GameState.uiStage.addActor(UIFactory.I.addMovingLabel("BUILD PHASE"));
            GameState.uiStage.addActor(UIFactory.I.addMovingLabelShadow("BUILD PHASE"));

            GameState.gamePhase = GamePhase.Build;
            GameState.userAction = UserAction.Clean;

            UIState.battleScreen.remove();
            UIState.battleSelectionCursor.setVisible(false);

            GameState.paused = false;

            GameState.stage.getActors().removeValue(party, true);

            GameState.isCombatSequence = false;

            GameState.nextAttackTarget = null;
            GameState.nextAttackTargetImage = null;

            GameState.battlePhase = BattlePhase.FinishTurn; // just for sure

            return true;
        }

        return false;
    }
}