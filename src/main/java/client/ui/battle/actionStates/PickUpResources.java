package client.ui.battle.actionStates;

import client.domain.Game;
import client.net.NetDispatcher;

import java.awt.*;

public class PickUpResources extends ActionState {
    public PickUpResources(Game game, NetDispatcher dispatcher) {
        super(game, dispatcher);
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
        if (shape != null)
            sendAntsToResource(point);
    }

    private void sendAntsToResource(Point p) {
        var anthill = game.getMainPlayer().getAnthill();
        var movement = anthill.getMovement();
        movement.setLocation(p);
        for (var ant : anthill.getAnts())
            movement.updateDestination(ant);
    }
}

