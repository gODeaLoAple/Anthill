package client.ui;

import client.domain.Map;

import java.awt.*;
import java.util.ArrayList;

public class HexagonalMap implements Map {

    public final Hexagon[] hexagons;
    private final int radius;
    private final Dimension size;

    public HexagonalMap(int width, int height, int R) {
        size = new Dimension(width, height);
        radius = R;
        hexagons = createMap(width, height, R);
    }

    public Shape getShapeAtPoint(Point point) {
        for (var hexagon : hexagons)
            if (hexagon.contains(point))
                return hexagon;
        return null;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
    public Point transformFromHexPoint(int i, int j) {
        final var R = radius;
        var x = Math.sqrt(3) * R * i;
        var y = R + 3 * R * j / 2;
        return j % 2 == 0 ? fromDouble(R + x, y) : fromDouble(f(R) + x, y);
    }

    private static Point fromDouble(double x, double y) {
        return new Point((int)x, (int)y);
    }

    private Hexagon[] createMap(final int width, final int height, final int R) {
        final var columns = (int)Math.floor(width / (Math.sqrt(3) * R));
        final var rows = height / (2 * R);
        var lst = new ArrayList<Hexagon>(columns * rows);
        for (var i = 0; i < columns; ++i)
            for (var j = 0; j < rows; ++j) {
                lst.add(new Hexagon(transformFromHexPoint(i, j), R + 1));
            }
        return lst.toArray(new Hexagon[0]);
    }

    private int f(int r) {
        return switch (r) {
            case 10 -> 3;
            case 20 -> 4;
            case 30 -> 5;
            case 40 -> 6;
            case 50 -> 7;
            case 60 -> 9;
            default -> 0;
        };
    }
}