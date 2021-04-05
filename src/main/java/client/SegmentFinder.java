package client;

import java.awt.*;

public class SegmentFinder {
    private final Polygon polygon;

    public SegmentFinder(Polygon polygon) {
        this.polygon = polygon;
    }

    public Segment getSegmentForPoint(Point point) {
        Segment result = null;
        var minDistance = 1000000d;
        for (var i = 0; i < polygon.npoints - 1; ++i) {
            var p = new Point(polygon.xpoints[i], polygon.ypoints[i]);
            var distance = p.distance(point);
            if (distance < minDistance) {
                var neighbor = new Point(
                        polygon.xpoints[(i + 1) % polygon.npoints],
                        polygon.ypoints[(i + 1) % polygon.npoints]);
                result = new Segment(p, neighbor);
                minDistance = distance;
            }
        }
        return result;
    }
    
}
