package client.domain.algorithm;

import java.awt.*;
import java.io.Serializable;

public interface ShapeFactory extends Serializable {
    Shape createShape(Point center);
}
