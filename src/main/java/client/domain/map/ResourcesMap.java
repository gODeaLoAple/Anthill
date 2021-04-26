package client.domain.map;

import client.domain.Game;
import client.ui.Hexagon;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

public class ResourcesMap implements DynamicMap {

    private final Dimension size;
    private final java.util.List<Shape> resourceShapes;
    public static final int maxResourceCountOnMap = 10;

    public ResourcesMap(Dimension size, Shape[] resourceShapes) {
        this.size = size;
        this.resourceShapes = new LinkedList<>(Arrays.asList(resourceShapes));
    }

    @Override
    public void add(Shape shape) {
        resourceShapes.add(shape);
    }

    @Override
    public void remove(Shape shape) {
        resourceShapes.remove(shape);
    }

    @Override
    public Shape getShapeAtPoint(Point lastMousePosition) {
        return resourceShapes
                .stream()
                .filter(x -> x.contains(lastMousePosition))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public Shape[] getShapes() {
        return resourceShapes.toArray(new Shape[0]);
    }

    @Override
    public Stream<Shape> getNeighbours(Shape shape) {
        return Stream.empty();
    }

    public void spawnResources(Game game) {
        var size = resourceShapes.size();
        if (size == maxResourceCountOnMap) {
            return;
        }

        for (var i = 0; i < maxResourceCountOnMap - size; i++) {
            var shape = getRandomShape();
            var rect = shape.getBounds();
            if (canAddResource(shape.getCenter(), game) && canAddResource(new Point(rect.x, rect.y), game))
                resourceShapes.add(shape);
            else spawnResources(game);
        }
    }

    private Hexagon getRandomShape(){
        var rnd = new Random();
        var rndPoint = new Point(rnd.nextInt(600), rnd.nextInt(600));
        return new Hexagon(new Point(rndPoint.x, rndPoint.y), 20);
    }
    private boolean canAddResource(Point rndPoint, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(rndPoint);
        return Arrays.stream(game.getPlayers()).noneMatch(x -> x.getAnthill().hasShape(shape));
    }
}
