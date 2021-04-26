package client.domain.map;

import java.awt.*;
import java.util.stream.Stream;

public interface Map {

    Shape getShapeAtPoint(Point lastMousePosition);

    Dimension getSize();

    Shape[] getShapes();

    Stream<Shape> getNeighbours(Shape shape);

}