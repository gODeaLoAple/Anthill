package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.Player;
import client.ui.battle.utils.ColorProvider;
import client.ui.battle.drawers.GameDrawer;
import client.ui.battle.utils.ShapeFiller;

import java.awt.*;


public class AnthillsDrawer extends GameDrawer implements ForEachPlayerDrawer {

    private final ColorProvider colorProvider;
    private final ShapeFiller filler;

    public AnthillsDrawer(Game game, ColorProvider provider, ShapeFiller filler) {
        super(game);
        this.colorProvider = provider;
        this.filler = filler;
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var player : game.getPlayers()) {
            draw(graphics, player);
        }
    }

    @Override
    public void draw(Graphics2D graphics, Player player) {
        var playerColor = colorProvider.getColor(player.getId());
        for (var part : player.getAnthill().getShapes()) {
            graphics.setColor(playerColor);
            graphics.fill(part.getShape());
            graphics.setColor(Color.BLACK);
            graphics.draw(part.getShape());
        }

        drawAttackPart(graphics, player);
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

