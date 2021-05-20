package client.ui.battle.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProvider {

    private BufferedImage antImage;
    private BufferedImage enemyImage;

    public BufferedImage getAntImage() {
        return antImage;
    }

    public void loadAll() throws IOException {
        antImage = loadImage("./assets/ant.png");
        enemyImage = loadImage("./assets/ant.png");
    }

    private BufferedImage loadImage(String pathToImage) throws IOException {
        return ImageIO.read(new File(pathToImage));
    }

    public BufferedImage getEnemyAntImage() throws IOException {
        var img = colorImage(enemyImage, new Color(150, 50, 10));
        var graphics = img.createGraphics();
        //var newColor = new Color(50, 150, 50 , 100);
        //graphics.setBackground(new Color(0x00000000, true));
        graphics.setXORMode(new Color(img.getRGB(antImage.getHeight() / 2, antImage.getHeight() / 2), true  ));
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();
        return img;
    }

    private static BufferedImage colorImage(BufferedImage image, Color color) {
        var width = image.getWidth();
        var height = image.getHeight();
        var raster = image.getRaster();

        for (var xx = 0; xx < width; xx++) {
            for (var yy = 0; yy < height; yy++) {
                var pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
}
