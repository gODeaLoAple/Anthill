package client.ui.battle.actionStates;

import client.domain.Game;
import client.net.NetDispatcher;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class HireMole extends ActionState implements Serializable {
    public final static int COST = 1000;

    public HireMole(Game game, NetDispatcher dispatcher) {
        super(game, dispatcher);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null && canHire()) {
            graphics.setColor(Color.magenta);
            graphics.draw(shape);
        }
    }

    private boolean canHire() {
        return game.getMainPlayer().getAnthill().hasEnoughResourcesToHire();
    }

    @Override
    public void clicked(Point point) throws IOException, ClassNotFoundException {
        var shape = game
                .getPartsMap()
                .getShapeAtPoint(point);
        if (shape == null)
            return;
        var dest = shape.getBounds().getLocation();
        var mainPlayer = game.getMainPlayer();
        var anthill = mainPlayer.getAnthill();
        if (canHire()) {
            anthill.hireMole(dest);
            dispatcher.send(new shared.messages.HireMole(mainPlayer, dest));
        }
    }
}
