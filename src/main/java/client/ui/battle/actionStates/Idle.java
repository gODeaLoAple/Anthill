package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public class Idle extends ActionState {
    public Idle(Game game) {
        super(game);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {

    }

    @Override
    public void clicked(Point point) {

    }
}
