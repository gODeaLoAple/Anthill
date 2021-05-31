package client.ui.battle.actionStates;

import client.domain.Game;
import client.net.NetDispatcher;

import java.awt.*;

public class Idle extends ActionState {
    public Idle(Game game, NetDispatcher dispatcher) {
        super(game, dispatcher);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {

    }

    @Override
    public void clicked(Point point) {

    }
}
