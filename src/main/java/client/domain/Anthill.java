package client.domain;

import client.ui.Hexagon;

import java.awt.*;
import java.util.ArrayList;

public class Anthill {
    public static final int RESOURCE_FOR_EXTEND = 10;
    private final AnthillPlace place;
    private final Resources resources;

    public Anthill(AnthillPlace startOwnLand, Resources resources) {
        place = startOwnLand;
        this.resources = resources;
    }

    public Resources getResources() {
        return resources;
    }

    public boolean hasShape(Shape shape) {
        return place.hasShape(shape);
    }

    public boolean hasEnoughResourcesToExtend() {
        return resources.getCount() > RESOURCE_FOR_EXTEND;
    }

    public void extend(Shape shape) {
        resources.remove(RESOURCE_FOR_EXTEND);
        place.add(shape);
    }

    public ArrayList<AnthillPart> getShapes() {
        return place.getShapes();
    }

    public AnthillPart getPartByShape(Shape shape){
        var parts = place.getShapes();
        for (var part: parts)
            if (part.getShape().getBounds().x == shape.getBounds().x && part.getShape().getBounds().y == shape.getBounds().y) return part;
        return null;
    }

    public void applyDamage(AnthillPart part, int damage) {
        place.applyDamage(part, damage);
    }
    public AnthillPlace getPlace() {
        return place;
    }

}
