package client.ui.battle.drawers;

import client.domain.Game;

import java.awt.*;

public class AntsDrawer extends GameDrawer{

    public AntsDrawer(Game game) {
        super(game);
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var ant : game.getAntsMap().getShapes()) {
            graphics.draw(ant);
        }
    }
}
