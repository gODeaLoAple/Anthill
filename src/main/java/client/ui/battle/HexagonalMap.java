package client.ui.battle;

import client.domain.map.DynamicMap;
import client.domain.map.Map;
import client.ui.battle.Hexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class HexagonalMap implements Map {

    public final Hexagon[] hexagons;
    private final int radius;
    private final Dimension size;

    public HexagonalMap(int width, int height, int R) {
        size = new Dimension(width, height);
        radius = R;
        hexagons = createMap(width, height, R);
    }

    @Override
    public Shape getShapeAtPoint(Point point) {
        for (var hexagon : hexagons)
            if (hexagon.contains(point))
                return hexagon;
        return null;
    }

    @Override
    public Shape[] getShapes() {
        return hexagons;
    }

    @Override
    public Stream<Shape> getNeighbours(Shape shape) {
        var hexagon = (Hexagon) shape; // TODO плохо, скорее всего, нужно использовать Generics
        var center = hexagon.getCenter();
        return Arrays.stream(hexagons)
                .filter(x -> x.getCenter().distanceSq(center) <= 3 * (radius + 1) * (radius + 1))
                .map(x -> x);
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
        return new Point((int) x, (int) y);
    }

    private Hexagon[] createMap(final int width, final int height, final int R) {
        final var columns = (int) Math.floor(width / (Math.sqrt(3) * R));
        final var rows = height / (2 * R);
        var lst = new ArrayList<Hexagon>(columns * rows);
        for (var i = 0; i < columns; ++i)
            for (var j = 0; j <= rows; ++j) {
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