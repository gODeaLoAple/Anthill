package client;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;

//public class Vector {
//    private int x;
//    private int y;
//
//    public Vector(int x, int y)
//}

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

    public void (){
        health -=
    }

}
