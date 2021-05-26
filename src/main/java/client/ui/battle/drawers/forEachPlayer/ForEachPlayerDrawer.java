package client.ui.battle.drawers.forEachPlayer;

import client.domain.entities.Player;

import java.awt.*;

public interface ForEachPlayerDrawer {
    void draw(Graphics2D graphics2D, Player player);
}
