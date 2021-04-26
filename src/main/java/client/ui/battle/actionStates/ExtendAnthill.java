package client.ui.battle.actionStates;

import client.domain.Game;
import client.domain.entities.Anthill;
import client.ui.battle.Hexagon;

import java.awt.*;
import java.util.Arrays;

public class ExtendAnthill extends ActionState {
    public ExtendAnthill(Game game) {
        super(game);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null) {
            if (canAddShape(shape)) {
                graphics.setColor(Color.BLACK);
                graphics.draw(shape);
            }
            else {
                graphics.setColor(Color.RED);
                graphics.fill(shape);
            }
        }
    }

    private boolean canAddShape(Shape shape) {
        var playerAnthill = game.getMainPlayer().getAnthill();
        return game.getMainPlayer().getAnthill().hasEnoughResourcesToExtend()
                && isShapeNearAnthill(shape, playerAnthill)
                && isFreeShape(shape);
    }


    private boolean isShapeNearAnthill(Shape shape, Anthill anthill) {
        return game.getPartsMap().getNeighbours(shape).anyMatch(anthill::hasShape);
    }
    private boolean isFreeShape(Shape shape) {
        var resourcesMap = game.getResourcesMap();
        for(var player : game.getPlayers()){
            var anthill = player.getAnthill();
            for (var rsh : resourcesMap.getShapes()){
                var rect = rsh.getBounds();
                var centerPoint = new Point((int)rect.getCenterX(), (int)rect.getCenterY());
                if (shape.contains(centerPoint) || shape.contains(rect)
                        || (anthill.hasShape(game.getPartsMap().getShapeAtPoint(centerPoint))))
                    return false;
            }

            if (anthill.hasShape(shape))
                return false;
        }

        return true;
        //return Arrays.stream(game.getPlayers()).noneMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point) {
        var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape != null && canAddShape(shape))
            game.getMainPlayer().getAnthill().extend(shape);
    }

}
