package client.domain.entities.ants;


import java.awt.*;

public abstract class Ant {
<<<<<<< HEAD
    private int health;
    private Point position;
    private Point direction;
=======
    private int id;
    private int health;
    private int speed;
    private Point position;
    private Point destination;


    public void setPosition(Point point){
        position = point;
    }
>>>>>>> 4ba6822f723c9ce6f6291407993d5a7dd86d0247

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

    public Point getPosition() {
        return position;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public int getSpeed() {
        return speed;
    }
}
