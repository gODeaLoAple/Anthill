package client.domain.algorithm;

import client.domain.entities.ants.Ant;
import client.entities.Vector;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ChaoticMovement {
    private Point location;
    private final int radius = 60;
    private final Random rnd;
    private final int offset = 0;

    public ChaoticMovement(Point location) {
        this.location = location;
        rnd = new Random();
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void moveAnt(Ant ant) {
        var source = ant.getPosition();
        var dest = ant.getDestination();
        var distance = (dest.x - source.x) * (dest.x - source.x) + (dest.y - source.y) * (dest.y - source.y);
        if (distance < radius) {
            var dx = radius * rnd.nextDouble();
            var dy = radius * rnd.nextDouble();
            dest = new Point(location.x + (int) dx, location.y + (int) dy);
            ant.setDestination(dest);
        }
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
            return Point.distanceSq(position.x + offset, position.y + offset, location.x, location.y) <= radius * radius;
        });
    }

    public boolean isAnyNearPoint(List<Ant> ants, Point point) {
        return ants.stream().anyMatch(ant -> {
            var position = ant.getPosition();
            return Point.distanceSq(position.x + offset, position.y + offset, point.x, point.y) <= radius * radius;
        });
    }

    public Point getLocation() {
        return location;
    }
}
