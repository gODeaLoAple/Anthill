package client.entities;

import java.awt.*;

public class Vector {
    private final Point point;

    public Vector(Point point) {
        this.point = point;
    }

    public Vector multiply(int d) {
        return new Vector(new Point(point.x * d, point.y * d));
    }

    public Point plus(Vector v) {
        return new Point(point.x + v.point.x, point.y + v.point.y);
    }

    public Point minus(Vector v) {
        return new Point(point.x - v.point.x, point.y - v.point.y);
    }

    public Point normalized() {
        var l = getLength();
        return new Point((int) (point.x / l), (int) (point.y / l));
    }

    public double getLength() {
        return Math.sqrt(this.point.x * this.point.x + this.point.y * this.point.y);
    }
}
