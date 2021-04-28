package client.domain.entities.ants;


import java.awt.*;

public abstract class Ant {
    private int health;
    private Point position;
    private Point direction;

    public Ant(Point point, int health) {
        this.health = health;
        position = point;
    }
    public void applyDamage(int damage){
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
