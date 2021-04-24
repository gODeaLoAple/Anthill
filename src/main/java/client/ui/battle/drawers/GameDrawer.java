package client.ui.battle.drawers;

import client.domain.Game;
import client.domain.entities.AnthillPart;
import client.domain.entities.Player;
import client.ui.ColorProvider;
import client.ui.battle.ShapeFiller;

import java.awt.*;

public abstract class GameDrawer implements Drawer {

    protected final Game game;

    public GameDrawer(Game game) {
        this.game = game;
    }

}

