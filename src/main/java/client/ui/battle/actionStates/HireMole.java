package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public class HireMole extends ActionState {
    public HireMole(Game game) {
        super(game);
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
    public void clicked(Point point) {
        var dest = game
                .getPartsMap()
                .getShapeAtPoint(point)
                .getBounds()
                .getLocation();
        var anthill = game.getMainPlayer().getAnthill();
        if (canHire()) {
            anthill
                    .getAnts()
                    .forEach(ant -> {
                        var movement = game.getMainPlayer().getAnthill().getMovement();
                        movement.setLocation(dest);
                        ant.setPosition(dest);
                        movement.updateDestination(ant);
                    });
            anthill.getResources().change(-1000);
        }
    }
}
