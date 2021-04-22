package client.domain;

import java.awt.*;

public interface Map {

    Shape getShapeAtPoint(Point lastMousePosition);

    Dimension getSize();

    Shape[] getShapes();
}
