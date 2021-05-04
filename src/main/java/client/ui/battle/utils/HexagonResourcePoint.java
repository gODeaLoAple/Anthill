package client.ui.battle.utils;

import java.awt.*;

public class HexagonResourcePoint extends Hexagon implements ResourcePoint {

    private final int resources;

    public HexagonResourcePoint(Point center, int radius, int resources) {
        super(center, radius);
        this.resources = resources;
    }

    @Override
    public int getResources() {
        return resources;
    }
}
