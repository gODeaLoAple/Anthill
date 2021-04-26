package client.ui.battle.actionStates;

import client.domain.Game;
import client.ui.battle.ResourcePoint;

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
        var map = game.getResourcesMap();
        var shape = game.getResourcesMap().getShapeAtPoint(point);
        if (shape instanceof ResourcePoint){
            var resource = (ResourcePoint)shape;
            game.getMainPlayer().getAnthill().getResources().change(resource.getResources());
            map.remove(shape);
        }
    }

}

