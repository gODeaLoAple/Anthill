package client.ui.battle.drawers;

import client.domain.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AntDrawer extends GameDrawer {

    private Image antImage;

    public AntDrawer(Game game) {
        super(game);
        antImage = loadAntImage();
    }

    private Image loadAntImage() {
        var file = "./assets/ant.png";
        try {
            return ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var player : game.getPlayers()) {
            var ants = player.getAnthill().getAnts();
            for (var ant : ants) {
                var position = ant.getPosition();
                graphics.drawImage(antImage, position.x, position.y, 30, 30, (img, infoflags, x, y, width, height) -> false);
            }
        }
    }
}
