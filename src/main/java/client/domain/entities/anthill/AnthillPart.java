package client.domain.entities.anthill;

import java.awt.*;

public class AnthillPart {
    public static final int MAX_HEALTH = 100;
    private final Shape shape;
    private int health;

    public AnthillPart(Shape shape, int health){
        this.health = health;
        this.shape = shape;
    }

    public AnthillPart(Shape shape) {
        this(shape, MAX_HEALTH);
    }

    public Shape getShape() {
        return shape;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void changeHealth(int delta) {
        health  += delta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return  shape.getBounds().x * prime * prime + shape.getBounds().y * prime + health;
    }

}
