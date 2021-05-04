package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.entities.Player;
import client.ui.battle.utils.ImageProvider;
import client.ui.battle.drawers.GameDrawer;

import java.awt.*;

public class AntDrawer extends GameDrawer implements ForEachPlayerDrawer {

    private final Image antImage;

    public AntDrawer(Game game, ImageProvider provider) {
        super(game);
        antImage = provider.getAntImage();
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var player : game.getPlayers()) {
            draw(graphics, player);
        }
    }

    @Override
    public void draw(Graphics2D graphics, Player player) {
        var anthill = player.getAnthill();
        var ants = anthill.getAnts();
        anthill.getMovement().moveAll(ants);
        for (var ant : ants) {
            var position = ant.getPosition();
            graphics.drawImage(antImage, position.x, position.y, 30, 30, (img, flags, x, y, width, height) -> false);
        }
    }
}
