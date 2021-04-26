package client.domain.map;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ResourcesMap implements DynamicMap {

    private final Dimension size;
    private final java.util.List<Shape> resourceShapes;

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
}
