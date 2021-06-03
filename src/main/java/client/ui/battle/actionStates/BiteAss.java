package client.ui.battle.actionStates;

import client.domain.Game;
import client.net.NetDispatcher;
import shared.messages.MovementSetPosition;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class BiteAss extends ActionState implements Serializable {

    public BiteAss(Game game, NetDispatcher dispatcher) {
        super(game, dispatcher);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.drawArc(clickedPoint.x, clickedPoint.y, 10, 10, 0, 360);
        graphics.setColor(color);
    }

    @Override
    public void clicked(Point point) throws IOException, ClassNotFoundException {
        sendAntsToResource(point);
    }

    private void sendAntsToResource(Point p) throws IOException, ClassNotFoundException {
        var anthill = game.getMainPlayer().getAnthill();
        var movement = anthill.getMovement();
        movement.setLocation(p);
        for (var ant : anthill.getAnts())
            movement.updateDestination(ant);
        dispatcher.send(new MovementSetPosition(game.getMainPlayer(), p));
    }
}
