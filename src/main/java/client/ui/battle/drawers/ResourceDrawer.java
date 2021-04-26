package client.ui.battle.drawers;

import client.domain.Game;

import java.awt.*;

public class ResourceDrawer extends GameDrawer {

    public ResourceDrawer(Game game) {
        super(game);
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var shape : game.getResourcesMap().getShapes()) {
            graphics.setColor(Color.CYAN);
            graphics.fill(shape);
        }
    }
}