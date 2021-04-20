package client.domain;


import java.awt.*;
import java.util.*;

public class AnthillPlace {
    private final ArrayList<AnthillPart> shapes;
    public AnthillPlace(AnthillPart[] shapes) {
        this.shapes = new ArrayList<>();
        Collections.addAll(this.getShapes(), shapes);
    }

    public void add(Shape shape) {
        getShapes().add(new AnthillPart(shape));
    }

    public boolean hasShape(Shape shape) {
        return getShapes().contains(shape);
    }

    public ArrayList<AnthillPart> getShapes() {
        return shapes;
    }
}
