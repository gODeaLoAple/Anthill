package client.domain.map;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class AntsMap implements DynamicMap {

    private final Dimension size;
    private final LinkedList<Shape> ants;

    public AntsMap(Dimension size, Shape[] ants) {
        this.size = size;
        this.ants = new LinkedList<>(Arrays.asList(ants));
    }

    @Override
    public void add(Shape shape) {
        ants.add(shape);
    }

    @Override
    public void remove(Shape shape) {
        ants.remove(shape);
    }

    @Override
    public Shape getShapeAtPoint(Point lastMousePosition) {
        return null;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public Shape[] getShapes() {
        return ants.toArray(new Shape[0]);
    }

    @Override
    public Stream<Shape> getNeighbours(Shape shape) {
        return Stream.empty();
    }
}
