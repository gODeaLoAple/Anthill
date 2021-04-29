package client.ui.battle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageProvider {

    private Image antImage;

    public Image getAntImage() {
        return antImage;
    }

    public void loadAll() throws IOException {
        antImage = loadImage("./assets/ant.png");
    }

    private Image loadImage(String pathToImage) throws IOException {
        return ImageIO.read(new File(pathToImage));
    }
}
