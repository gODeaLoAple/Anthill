package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public interface PlayerActionState {

    void paint(Point clickedPoint, Graphics2D graphics, Game game);

    void clicked(Point point, Game game);

}

