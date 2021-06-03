package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.entities.Player;
import client.entities.Vector;
import client.ui.battle.drawers.GameDrawer;
import client.ui.battle.utils.ColorProvider;
import client.ui.battle.utils.ImageCircular;
import client.ui.battle.utils.ImageProvider;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

public class AntDrawer extends GameDrawer implements ForEachPlayerDrawer{

    private final ImageObserver obs;
    private final Map<Color, ImageCircular> images;
    private final ColorProvider colorProvider;

    public AntDrawer(Game game, ImageProvider provider, ColorProvider colorProvider) {
        super(game);
        obs = (img, infoflags, x, y, width, height) -> false;

        this.colorProvider = colorProvider;
        images = createImages(colorProvider, provider);
    }

    private Map<Color, ImageCircular> createImages(ColorProvider colors, ImageProvider images) {
        var map = new HashMap<Color, ImageCircular>(colors.getAllColors().size());
        var image = images.getAntImage();
        for (var color : colors.getAllColors())
            map.put(color, new ImageCircular(images.colorImage(image, color)));
        return map;
    }
    @Override
    public void draw(Graphics2D graphics, Player player) {
        var images = this.images.get(colorProvider.getColor(player.getId()));
        for (var ant : player.getAnthill().getAnts()) {
            var position = ant.getPosition();
            var vector = new Vector(position, ant.getDestination());
            var angle = (int)Math.floor(Math.toDegrees(vector.getAngle()));
            graphics.drawImage(images.getImage(angle),  position.x, position.y, 30, 30, obs);
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        for(var player : game.getPlayers())
            draw(graphics, player);
    }
}
