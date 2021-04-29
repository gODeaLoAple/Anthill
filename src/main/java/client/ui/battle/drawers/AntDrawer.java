package client.ui.battle.drawers;

import client.domain.Game;
import client.ui.battle.ImageProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AntDrawer extends GameDrawer {

    private Image antImage;

    public AntDrawer(Game game, ImageProvider provider) {
        super(game);
        antImage = provider.getAntImage();
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var player : game.getPlayers()) {
            var anthill = player.getAnthill();
            var ants = anthill.getAnts();
            anthill.getMovement().moveAll(ants);
            for (var ant : ants) {
                var position = ant.getPosition();
                graphics.drawImage(antImage, position.x, position.y, 30, 30, (img, infoflags, x, y, width, height) -> false);
            }
        }
    }
}
