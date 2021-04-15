package client.domain;

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

    public ArrayList<Shape> getShapes() {
        return place.getShapes();
    }
}
