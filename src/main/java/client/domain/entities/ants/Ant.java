package client.domain.entities.ants;


import java.awt.*;
import java.io.Serializable;

public abstract class Ant implements Serializable {
    private int health;
    private Point position;
    private Point destination;


    public Ant(Point point, int health) {
        this.health = health;
        position = new Point(point);
        destination = new Point(position);
    }

    public void applyDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public void setPosition(Point position) {
        this.position = new Point(position);
    }

    public void setDestination(Point destination) {
        this.destination = new Point(destination);
    }

    public Point getPosition() {
        return position;
    }

    public Point getDestination() {
        return destination;
    }

    public int getSpeed() {
        return 3;
    }
}
