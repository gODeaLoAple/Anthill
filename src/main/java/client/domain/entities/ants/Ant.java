package client.domain.entities.ants;


import java.awt.*;

public abstract class Ant {
    public int id;
    public int health;
    public int speed;
    public Point position;
    public Point direction;

    public void setPosition(int x, int y){
        position = new Point(x, y);
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
}
