package client.domain.algorithm;

import client.domain.entities.ants.Ant;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;
import client.entities.Vector;

public class ChaoticMovement {
    private Point location;
    private final int radius = 60;
    private final Random rnd;

    public ChaoticMovement(Point location){
        this.location = location;
        rnd = new Random();
    }

    public void setLocation(Point location) {
        this.location = new Point(location);
    }

    public void moveAll(List<Ant> ants){

        for (var ant : ants){
            var source = ant.getPosition();
            var dest = ant.getDestination();
            var distance = (dest.x - source.x) * (dest.x - source.x) + (dest.y - source.y) * (dest.y - source.y);
            if (distance < radius){
                var dx = radius * rnd.nextDouble();
                var dy = radius * rnd.nextDouble();
                dest = new Point(location.x + (int)dx, location.y + (int)dy);
                ant.setDestination(dest);
            }
            var vector = new Vector(new Point(dest.x - source.x, dest.y - source.y));
            var length = vector.getLength();
            var dx = vector.getPoint().x * ant.getSpeed() / length;
            var dy = vector.getPoint().y * ant.getSpeed() / length;
            source.translate((int)dx, (int)dy);
        }
    }

    public boolean isAnyInRadius(List<Ant> ants) {
        return ants.stream().anyMatch(x -> {
            var position = x.getPosition();
            return Point.distanceSq(position.x, position.y, location.x, location.y) <= radius * radius;
        });
    }

    public Point getLocation() {
        return location;
    }
}
