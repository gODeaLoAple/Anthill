package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public class PickUpResources implements PlayerActionState {

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics, Game game) {
        var shape = game.getResourcesMap().getShapeAtPoint(clickedPoint);
        if (shape != null) {
            graphics.setColor(Color.BLACK);
            graphics.draw(shape);
        }
    }

    @Override
    public void clicked(Point point, Game game) {

    }

}

