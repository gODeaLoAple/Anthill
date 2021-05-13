package client.entities;

import java.awt.*;

public class Vector {
    private final Point point;

    public Vector(Point point) {
        this.point = point;
    }

    public Vector(Point start, Point end) {
        var x = end.x - start.x;
        var y = end.y - start.y;
        this.point = new Point(x, y);
    }

    public Vector(int x, int y) {
        this.point = new Point(x, y);
    }

    public Point getPoint() { return point; }

    public int getX() { return point.x; }

    public int getY() { return point.y; }

    public double getLength() {
        return Math.sqrt(this.point.x * this.point.x + this.point.y * this.point.y);
    }

    public double getAngle(){
        var l = getLength();
        return Math.atan2(this.getY() / l, this.getX() / l);
    }
}
