package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public abstract class ActionState implements PlayerActionState{
    public final Game game;

    public ActionState(Game game) {
        this.game = game;
    }
}
