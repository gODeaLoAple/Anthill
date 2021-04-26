package client.ui.battle;

import client.ui.Hexagon;

import java.awt.*;

public class ShapeFiller implements IShapeFiller {

    private final Color upper;
    private final Color bottom;

    public  ShapeFiller(Color upper, Color bottom) {

        this.upper = upper;
        this.bottom = bottom;
    }

    public void fill(Shape shape, Graphics2D graphics, double k) {
        if (shape instanceof Hexagon) {
            var hexagon = (Hexagon)shape;
            paintHexagon(hexagon, graphics, upper, 0, 1 - k);
            paintHexagon(hexagon, graphics, bottom, (1 - k), k);
        }
    }

    private void paintHexagon(Hexagon shape, Graphics2D graphics, Color color, double k1, double k2){
        var rectangle = shape.getBounds();
        var originalClipBounds = graphics.getClipBounds();
        var rectX = (int) (rectangle.getCenterX() - shape.getRadius() * Math.sqrt(3) / 2);
        var rectY = (int) (rectangle.getCenterY() - shape.getRadius());
        try {
            graphics.clipRect(rectX, (int) (rectY + (rectangle.height * k1)),
                    rectangle.width, (int) (rectangle.height * rectangle.height * k2));
            graphics.setColor(color);
            graphics.fill(shape);
        } finally {
            graphics.setClip(originalClipBounds);
        }
    }
}
