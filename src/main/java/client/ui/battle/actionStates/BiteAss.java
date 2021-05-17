package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public class BiteAss extends ActionState {

    public BiteAss(Game game) {
        super(game);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.drawArc(clickedPoint.x, clickedPoint.y, 10, 10, 0, 360);
        graphics.setColor(color);
    }

    @Override
    public void clicked(Point point) {
        sendAntsToResource(point);
        // cluster (who)
    }

    private void sendAntsToResource(Point p) {
        var anthill = game.getMainPlayer().getAnthill();
        var movement = anthill.getMovement();
        movement.setLocation(p);
        for (var ant : anthill.getAnts())
            movement.updateDestination(ant);
    }
}
