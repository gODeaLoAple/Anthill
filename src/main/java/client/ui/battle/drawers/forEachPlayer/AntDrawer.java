package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.entities.Player;
import client.entities.Vector;
import client.ui.battle.drawers.GameDrawer;
import client.ui.battle.utils.ImageProvider;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

public class AntDrawer extends GameDrawer implements ForEachPlayerDrawer{

    private final BufferedImage antImage;
    private final BufferedImage enemyAntImage;
    private final int height;
    private final int width;
    private final ImageObserver obs;
    private final Map<Integer, BufferedImage> angleToImageMainPlayer;
    private final Map<Integer, BufferedImage> angleToImageEnemyPlayer;

    public AntDrawer(Game game, ImageProvider provider) {
        super(game);
        obs = (img, infoflags, x, y, width, height) -> false;
        antImage = provider.getAntImage();
        width = antImage.getWidth();
        height = antImage.getHeight();
        angleToImageMainPlayer = createRotations(antImage);
        enemyAntImage = provider.getEnemyAntImage();
        angleToImageEnemyPlayer = createRotations(enemyAntImage);
    }

    @Override
    public void draw(Graphics2D graphics, Player player) {
        for (var ant : player.getAnthill().getAnts()) {
            var position = ant.getPosition();
            var vector = new Vector(position, ant.getDestination());
            var angle = (int)Math.floor(Math.toDegrees(vector.getAngle()));
            if (player.getId() == 0)
                graphics.drawImage(angleToImageMainPlayer.get(angle), position.x, position.y, 30, 30, obs);
            else
                graphics.drawImage(angleToImageEnemyPlayer.get(angle), position.x, position.y, 30, 30, obs);
        }
    }

    private Map<Integer, BufferedImage> createRotations(BufferedImage image) {
        var map = new HashMap<Integer, BufferedImage>(360);
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

    @Override
    public void draw(Graphics2D graphics) {
        for(var player : game.getPlayers())
            draw(graphics, player);
    }
}
