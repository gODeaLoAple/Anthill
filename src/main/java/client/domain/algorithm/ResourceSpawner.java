package client.domain.algorithm;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class ResourceSpawner implements Serializable {
    public static final int maxResourceCountOnMap = 10;
    private final ShapeFactory factory;
    private final Random rnd;

    public ResourceSpawner(ShapeFactory factory) {
        rnd = new Random();
        this.factory = factory;
    }
    public void spawn(Game game) {
        var resourceMap = game.getResourcesMap();
        var resourceShapes = resourceMap.getShapes();

        var count = maxResourceCountOnMap - resourceShapes.size();
        for (var i = 0; i < count; i++) {
            var added = false;
            do {
                var center = getRandomCenter(resourceMap.getSize());
                var shape = factory.createShape(center);
                var rect = shape.getBounds();
                added = canAddResource(center, game) && canAddResource(new Point(rect.x, rect.y), game);
                if (added)
                    resourceShapes.add(shape);
            } while (!added);
        }
    }

    private Point getRandomCenter(Dimension bounds){
        return new Point(rnd.nextInt(bounds.width), rnd.nextInt(bounds.height));
    }

    private boolean canAddResource(Point rndPoint, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(rndPoint);
        return Arrays.stream(game.getPlayers().toArray(new Player[0])).noneMatch(x -> x.getAnthill().hasShape(shape));
    }
}
