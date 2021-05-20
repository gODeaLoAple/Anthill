package client.ui.battle.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageCircular {
    private final Map<Integer, BufferedImage> rotations;

    public ImageCircular(BufferedImage originalImage) {
        rotations = createRotations((originalImage));
    }

    public BufferedImage getImage(int degree) {
        return rotations.get(degree);
    }

    private Map<Integer, BufferedImage> createRotations(BufferedImage image) {
        var map = new HashMap<Integer, BufferedImage>(360);
        var width = image.getWidth();
        var height = image.getHeight();
        for (var i = -180; i < 180; i++) {
            var at = new AffineTransform();
            var newImage = new BufferedImage(width, height, image.getType());
            var newImageHeight = newImage.getHeight();
            var newImageWidth = newImage.getWidth();
            at.rotate(Math.toRadians(i) + Math.PI / 2, newImageWidth / 2.0, newImageHeight / 2.0);
            at.translate((newImageWidth - width) / 2.0, (newImageHeight - height) / 2.0);
            var op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            map.put(i, op.filter(image, newImage));
        }
        return map;
    }
}
