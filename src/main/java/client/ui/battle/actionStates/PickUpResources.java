package client.ui.battle.actionStates;

import client.domain.Game;
import client.domain.entities.anthill.Anthill;


import java.awt.*;

public class PickUpResources extends ActionState {

    public PickUpResources(Game game) {
        super(game);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var shape = game.getResourcesMap().getShapeAtPoint(clickedPoint);
        if (shape != null) {
            graphics.setColor(Color.BLACK);
            graphics.draw(shape);
        }
    }

    @Override
    public void clicked(Point point) {
        var resourceMap = game.getResourcesMap();
        var shape = resourceMap.getShapeAtPoint(point);
        if (shape != null) {
            var bounds = shape.getBounds();
            sendAntsToResource(point);
        }
    }

    private void sendAntsToResource(Point p) {
        game.getMainPlayer().getAnthill().getMovement().setLocation(p);
    }
}

