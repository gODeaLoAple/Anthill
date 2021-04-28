package client.domain.algorithm;

import client.domain.entities.ants.Ant;

import java.awt.*;
import java.util.Random;
import client.entities.Vector;

public class ChaoticMovement {
    private Point location;
    private final int radius = 20;
    private final Random rnd;

    public ChaoticMovement(Point location){
        this.location = location;
        rnd = new Random();

    }


    private Point normalizedVector(Point point){
        return new Vector(point).normalized();
    }

    public void getRandomPoint(Ant[] ants){

        for (var ant : ants){
            var source = ant.getPosition();
            var dest = ant.getDestination();
            if (source == dest){
                var dx = radius * rnd.nextInt();
                var dy = radius * rnd.nextInt();
                ant.setDestination(new Point(source.x  + dx, source.y + dy));
            }
            var vector = new Vector(new Point(dest.x - source.x, dest.y - source.x))
                    .multiply(ant.getSpeed())
                    .normalized();
            var destPoint = ant.getPosition();
            destPoint.translate(vector.x, vector.y);
            ant.setPosition(destPoint);
        }
    }
}
