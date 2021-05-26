package client.domain.algorithm;

import client.domain.entities.ants.Ant;
import client.entities.Vector;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ChaoticMovement {
    private Point location;
    private final int radius = 60;
    public static final Random rnd = new Random();

    public ChaoticMovement(Point location) {
        this.location = new Point(location);
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void moveAnt(Ant ant) {
        var source = ant.getPosition();
        var dest = ant.getDestination();
        var dx = dest.x - source.x;
        var dy = dest.y - source.y;
        if (dx * dx + dy * dy < radius) dest = updateDestination(ant);
        translateAnt(ant, source, dest);
    }

    private void translateAnt(Ant ant, Point source, Point dest) {
        var vector = new Vector(dest.x - source.x, dest.y - source.y);
        var length = vector.getLength();
        var speed = ant.getSpeed();
        var dx = vector.getX() * speed / length;
        var dy = vector.getY() * speed / length;
        source.translate((int) dx, (int) dy);
    }

    public boolean isAnyInRadius(List<Ant> ants) {
        return ants.stream().anyMatch(ant -> {
            var position = ant.getPosition();
            return Point.distanceSq(position.x, position.y, location.x, location.y) <= radius * radius;
        });
    }

    public boolean isAnyNearPoint(List<Ant> ants, Point point) {
        return ants.stream().anyMatch(ant -> {
            var position = ant.getPosition();
            return Point.distanceSq(position.x , position.y, point.x, point.y) <= radius * radius * 0.25;
        });
    }

    public Point getLocation() {
        return location;
    }

    public Point updateDestination(Ant ant) {
        var r = radius * rnd.nextDouble();
        var angle = rnd.nextInt(360);
        var dest = new Point(location.x + (int)(r * Math.cos(angle)), location.y + (int)(r * Math.sin(angle)));
        ant.setDestination(dest);
        return dest;
    }

    public double getRadius() {
        return radius;
    }
}
