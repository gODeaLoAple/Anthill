package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;

public class ExtendAnthill implements PlayerActionState {

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics, Game game) {
        var shape = game.getMap().getShapeAtPoint(clickedPoint);
        if (shape != null)
            graphics.draw(shape);
    }

    @Override
    public void clicked(Point point, Game game) {
        var shape = game.getMap().getShapeAtPoint(point);
        if (shape != null)
            game.getPlayers()[0].getAnthill().getPlace().add(shape);
    }

}
