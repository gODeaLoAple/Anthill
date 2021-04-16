package client;


import java.awt.*;
import java.util.*;

public class AnthillPlace {
    public ArrayList<Shape> shapes;

    public AnthillPlace(Shape[] shapes) {
        this.shapes = new ArrayList<>();
        Collections.addAll(this.shapes, shapes);
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

}
