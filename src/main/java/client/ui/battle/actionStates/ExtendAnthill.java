package client.ui.battle.actionStates;

import client.domain.Game;
import client.domain.entities.Anthill;

import java.awt.*;
import java.util.Arrays;

public class ExtendAnthill implements PlayerActionState {
// TODO скорее всего, лучеш использовать абстрактный класс с полем game, чем в метод передавать каждый раз
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
        var playerAnthill = game.getMainPlayer().getAnthill();
        return game.getMainPlayer().getAnthill().hasEnoughResourcesToExtend()
                && isShapeNearAnthill(shape, playerAnthill, game)
                && isFreeShape(shape, game);
    }

    private boolean isShapeNearAnthill(Shape shape, Anthill anthill, Game game) {
        return game.getPartsMap().getNeighbours(shape).anyMatch(anthill::hasShape);
    }
    private boolean isFreeShape(Shape shape, Game game) {
        return Arrays.stream(game.getPlayers()).noneMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape != null && canAddShape(game, shape))
            game.getMainPlayer().getAnthill().extend(shape);
    }

}
