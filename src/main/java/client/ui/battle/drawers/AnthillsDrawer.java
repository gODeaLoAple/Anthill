package client.ui.battle.drawers;

import client.domain.Game;
import client.domain.entities.AnthillPart;
import client.domain.entities.Player;
import client.ui.battle.ColorProvider;
import client.ui.battle.IShapeFiller;

import java.awt.*;

public class AnthillsDrawer extends GameDrawer {

    private final ColorProvider colorProvider;
    private final IShapeFiller filler;

    public AnthillsDrawer(Game game, ColorProvider provider, IShapeFiller filler) {
        super(game);
        this.colorProvider = provider;
        this.filler = filler;
    }

    @Override
    public void draw(Graphics2D graphics) {
        var players = game.getPlayers();
        for (var player : players) {
            var playerColor = colorProvider.getColor(player.getId());
            for (var part : player.getAnthill().getShapes()) {
                graphics.setColor(playerColor);
                graphics.fill(part.getShape());
                graphics.setColor(Color.BLACK);
                graphics.draw(part.getShape());
            }

            drawAttackPart(graphics, player);
        }
    }

    private void drawAttackPart(Graphics2D graphics, Player player) {
        player.getAnthill()
                .getShapes()
                .stream()
                .filter(x -> x.getHealth() < AnthillPart.MAX_HEALTH)
                .forEach(ap -> {
                    var shape = ap.getShape();
                    var percents = (double) ap.getHealth() / AnthillPart.MAX_HEALTH;
                    filler.fill(shape, graphics, percents);
                });
    }
}

