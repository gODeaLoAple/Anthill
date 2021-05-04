package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.ui.battle.drawers.GameDrawer;

import java.awt.*;

public class ForEachPlayerDrawerContainer extends GameDrawer {

    private final ForEachPlayerDrawer[] drawers;

    public ForEachPlayerDrawerContainer(Game game, ForEachPlayerDrawer[] drawers) {
        super(game);
        this.drawers = drawers;
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (var player : game.getPlayers()){
            for (var drawer : drawers) {
                drawer.draw(graphics, player);
            }
        }
    }
}
