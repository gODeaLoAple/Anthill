package client.domain.map;

import java.awt.*;
import java.util.stream.Stream;

public interface Map {

    Shape getShapeAtPoint(Point lastMousePosition);

    Dimension getSize();

    java.util.List<? extends Shape> getShapes();

    Stream<? extends Shape> getNeighbours(Shape shape);

}