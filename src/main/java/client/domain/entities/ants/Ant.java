package client.domain.entities.ants;


import java.awt.*;

public abstract class Ant {
    private int id;
    private int health;
    private int speed;
    private Point position;
    private Point destination;


    public void setPosition(Point point){
        position = point;
    }

    public void move(){
        position = new Point(0, 0);
    }

    public void applyDamage(int damage){
        health -= damage;
    }

    public void setId(int id){
        this.id = id;
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
