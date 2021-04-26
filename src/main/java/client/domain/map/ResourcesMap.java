package client.domain.map;

import client.ui.Hexagon;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

public class ResourcesMap implements DynamicMap {

    private final Dimension size;
    private final java.util.List<Shape> resourceShapes;
    private final int maxResourceCountOnMap = 5;

    public ResourcesMap(Dimension size, Shape[] resourceShapes) {
        this.size = size;
        this.resourceShapes = new LinkedList<>(Arrays.asList(resourceShapes));
        spawnResources();
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

    public void spawnResources() {
        if (resourceShapes.size() == maxResourceCountOnMap) {
            return;
        }
        for(var i = 0; i < maxResourceCountOnMap; i++){
            var rnd = new Random();
            var rndX = rnd.nextInt(100);
            var rndY = rnd.nextInt(100);
            resourceShapes.add(new Hexagon(new Point(rndX, rndY), 20));
        }

    }

    @Override
    public Stream<Shape> getNeighbours(Shape shape) {
        return Stream.empty();
    }
}
