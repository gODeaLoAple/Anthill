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

public class AntDrawer extends GameDrawer implements ForEachPlayerDrawer{

    private BufferedImage antImage;
    private int height;
    private int width;
    private ImageObserver obs;
    private AffineTransform identity;

    public AntDrawer(Game game, ImageProvider provider) {
        super(game);
        obs = (img, infoflags, x, y, width, height) -> false;
        antImage = provider.getAntImage();
        width = antImage.getWidth();
        height = antImage.getHeight();
        identity = new AffineTransform();
    }

    @Override
    public void draw(Graphics2D graphics, Player player) {
        var anthill = player.getAnthill();
        var ants = anthill.getAnts();
        anthill.getMovement().moveAll(ants);
        for (var ant : ants) {
            var position = ant.getPosition();
            var vect = new Vector(position, ant.getDestination());
            var angle = vect.getAngle() + Math.PI / 2;
            var trans = new AffineTransform();
            var newImage = new BufferedImage(width, height, antImage.getType());
            var newImageHeight = newImage.getHeight();
            var newImageWidth = newImage.getWidth();
            trans.rotate(angle, newImageWidth / 2.0, newImageHeight / 2.0);
            trans.translate((newImageWidth - width) / 2.0, (newImageHeight - height) / 2.0);
            var op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
            try {
                graphics.drawImage(op.filter(antImage, newImage), position.x, position.y, 30, 30, obs);
            } catch (Exception ignored) {
            }
        }

    }

    @Override
    public void draw(Graphics2D graphics) {
        for(var player : game.getPlayers())
            draw(graphics, player);
    }
}
