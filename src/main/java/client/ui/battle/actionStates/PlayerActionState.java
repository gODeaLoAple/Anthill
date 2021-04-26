package client.ui.battle.actionStates;

import java.awt.*;

public interface PlayerActionState {

    void paint(Point clickedPoint, Graphics2D graphics);

    void clicked(Point point);
}

