package client.ui.battle.drawers.forEachPlayer;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;

public interface ForEachPlayerDrawer {
    public void draw(Graphics2D graphics2D, Player player);
}
