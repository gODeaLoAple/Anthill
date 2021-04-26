package client.ui.battle.actionStates;

import client.domain.Game;

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
            game
                    .getMainPlayer()
                    .getAnthill()
                    .getResources()
                    .apply(1000);
            resourceMap.remove(shape);
        }
    }

}

