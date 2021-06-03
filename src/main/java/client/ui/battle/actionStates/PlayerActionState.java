package client.ui.battle.actionStates;

import java.awt.*;
import java.io.IOException;

public interface PlayerActionState {

    void paint(Point clickedPoint, Graphics2D graphics);

    void clicked(Point point) throws IOException, ClassNotFoundException;
}

