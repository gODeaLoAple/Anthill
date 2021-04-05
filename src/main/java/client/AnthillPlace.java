package client;

import java.awt.*;
import java.awt.geom.Point2D;

public class AnthillPlace {
    private final Polygon place;
    private final Point center;
    
    public AnthillPlace(Polygon place) {
        this.place = place;
        center = calculateCenter();
    }

    private Point calculateCenter() {
        final var firstPoint = new Point(place.xpoints[0], place.ypoints[0]);
        var result = new Point((int)firstPoint.getX(), (int)firstPoint.getY());
        for (var i = 1; i < place.npoints; ++i)
            result.translate(place.xpoints[i], place.ypoints[i]);
        return new Point((int)(result.getX() / place.npoints), (int)(result.getY() / place.npoints));
    }

    public void addPoint(Point point) {
        place.addPoint((int)point.getX(), (int)point.getY()); // TODO
    }

    public Point getCenter() {
        return center;
    }

    public Polygon getPolygon() {
        return place;
    }


}
