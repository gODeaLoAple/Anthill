package client.domain.map;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ResourcesMap implements DynamicMap, Serializable {

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
    public java.util.List<Shape> getShapes() {
        return resourceShapes;
    }

    @Override
    public Stream<Shape> getNeighbours(Shape shape) {
        return Stream.empty();
    }

}
