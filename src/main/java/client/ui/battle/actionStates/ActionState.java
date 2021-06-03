package client.ui.battle.actionStates;

import client.domain.Game;
import client.net.NetDispatcher;

public abstract class ActionState implements PlayerActionState{
    protected Game game;
    protected final NetDispatcher dispatcher;

    public ActionState(Game game, NetDispatcher dispatcher) {
        this.game = game;
        this.dispatcher = dispatcher;
    }
}
