package client.ui.battle.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProvider {

    private BufferedImage antImage;

    public BufferedImage getAntImage() {
        return antImage;
    }

    public void loadAll() throws IOException {
        antImage = loadImage("./assets/ant.png");
    }

    private BufferedImage loadImage(String pathToImage) throws IOException {
        return ImageIO.read(new File(pathToImage));
    }

    public BufferedImage getEnemyAntImage() {
        var img = new BufferedImage(antImage.getWidth(), antImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        var graphics = img.createGraphics();
        var newColor = new Color(58, 255, 0, 0);
        graphics.setXORMode(newColor);
        graphics.drawImage(antImage, 0, 0, null);
        graphics.dispose();
        return img;
    }
}
