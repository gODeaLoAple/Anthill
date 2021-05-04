package client.ui.battle.utils;

import java.awt.*;

public class ShapeFiller implements IShapeFiller {

    private final Color upper;
    private final Color bottom;

    public  ShapeFiller(Color upper, Color bottom) {
        this.upper = upper;
        this.bottom = bottom;
    }

    public void fill(Shape shape, Graphics2D graphics, double percents) {
        if (shape instanceof Hexagon) {
            var hexagon = (Hexagon)shape;
            paintHexagon(hexagon, graphics, upper, 0, 1 - percents);
            paintHexagon(hexagon, graphics, bottom, 1 - percents, percents);
        }
    }

    private void paintHexagon(Hexagon shape, Graphics2D graphics, Color color, double k1, double k2){
        var clip = graphics.getClip();
        var rectangle = shape.getBounds();
        var radius = shape.getRadius();
        var rectX = (rectangle.getCenterX() - radius * Math.sqrt(3) / 2);
        var rectY = (rectangle.getCenterY() - radius);
        try {
            graphics.clipRect((int)rectX,
                    (int) (rectY + (rectangle.height * k1)),
                    rectangle.width,
                    (int) (rectangle.height * rectangle.height * k2));
            graphics.setColor(color);
            graphics.fill(shape);
        } finally {
            graphics.setClip(clip);
        }
    }
}
