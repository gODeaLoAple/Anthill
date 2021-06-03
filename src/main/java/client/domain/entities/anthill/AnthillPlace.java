package client.domain.entities.anthill;


import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class AnthillPlace implements Serializable {
    private final ArrayList<AnthillPart> shapes;
    public AnthillPlace(AnthillPart[] shapes) {
        this.shapes = new ArrayList<>();
        Collections.addAll(this.shapes, shapes);
    }

    public void add(Shape shape) {
        shapes.add(new AnthillPart(shape));
    }

    public void applyDamage(AnthillPart part, int damage){
        part.changeHealth(-damage);
        if (part.getHealth() <= 0)
            shapes.remove(part);
    }

    public boolean hasShape(Shape shape) {
        return shapes.stream().anyMatch(x -> x.getShape().equals(shape));
    }

    public ArrayList<AnthillPart> getShapes() {
        return shapes;
    }
}
