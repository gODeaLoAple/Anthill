package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.algorithm.ChaoticMovement;
import client.domain.entities.Player;
import client.domain.entities.ants.Ant;
import client.entities.Vector;
import client.ui.battle.drawers.GameDrawer;
import client.ui.battle.utils.ImageProvider;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;
import java.util.stream.Stream;

public class AntDrawer extends GameDrawer implements ForEachPlayerDrawer{

    private final BufferedImage antImage;
    private final int height;
    private final int width;
    private final ImageObserver obs;

    public AntDrawer(Game game, ImageProvider provider) {
        super(game);
        obs = (img, infoflags, x, y, width, height) -> false;
        antImage = provider.getAntImage();
        width = antImage.getWidth();
        height = antImage.getHeight();
    }

    @Override
    public void  draw(Graphics2D graphics, Player player) {
        var anthill = player.getAnthill();
        var ants = anthill.getAnts();
        var movement = anthill.getMovement();
        moveAndRotateAnt(graphics, ants, movement);
    }

    private void moveAndRotateAnt(Graphics2D graphics, List<Ant> ants, ChaoticMovement movement){
        Stream.iterate(0, n -> n).limit(ants.size()).parallel().forEach(ant -> {
            movement.moveAnt(ants.get(ant));
            rotateSprite(graphics, ants.get(ant));
        });
    }

    private void rotateSprite(Graphics2D graphics, Ant ant){
        var position = ant.getPosition();
        var vector = new Vector(position, ant.getDestination());
        var at = new AffineTransform();
        var newImage = new BufferedImage(width, height, antImage.getType());
        var newImageHeight = newImage.getHeight();
        var newImageWidth = newImage.getWidth();
        at.rotate(vector.getAngle() + Math.PI / 2, newImageWidth / 2.0, newImageHeight / 2.0);
        at.translate((newImageWidth - width) / 2.0, (newImageHeight - height) / 2.0);
        var op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        try {
            graphics.drawImage(op.filter(antImage, newImage), position.x, position.y, 30, 30, obs);
        } catch (Exception ignored) {}
    }

    @Override
    public void draw(Graphics2D graphics) {
        for(var player : game.getPlayers())
            draw(graphics, player);
    }
}
