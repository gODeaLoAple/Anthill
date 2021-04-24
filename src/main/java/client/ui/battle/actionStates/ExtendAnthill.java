package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;
import java.util.Arrays;

public class ExtendAnthill implements PlayerActionState {

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null) {
            if (canAddShape(game, shape)) {
                graphics.setColor(Color.BLACK);
                graphics.draw(shape);
            }
            else {
                graphics.setColor(Color.RED);
                graphics.fill(shape);
            }
        }
    }

    private boolean canAddShape(Game game, Shape shape) {
        return game.getMainPlayer().getAnthill().hasEnoughResourcesToExtend()
                && Arrays.stream(game.getPlayers()).noneMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape != null && canAddShape(game, shape))
            game.getMainPlayer().getAnthill().extend(shape);
    }

}
