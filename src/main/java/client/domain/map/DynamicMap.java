package client.domain.map;

import java.awt.*;

public interface DynamicMap extends Map {

    void add(Shape shape);

    void remove(Shape shape);

}

