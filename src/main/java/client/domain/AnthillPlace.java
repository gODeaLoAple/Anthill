package client.domain;


import java.awt.*;
import java.util.*;

public class AnthillPlace {
    private ArrayList<Shape> shapes;
    public AnthillPlace(Shape[] shapes) {
        this.shapes = new ArrayList<>();
        Collections.addAll(this.getShapes(), shapes);
    }

    public void add(Shape shape) {
        getShapes().add(shape);
    }

    public boolean hasShape(Shape shape) {
        return getShapes().contains(shape);
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
