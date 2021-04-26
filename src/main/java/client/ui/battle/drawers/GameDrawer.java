package client.ui.battle.drawers;

import client.domain.Game;

public abstract class GameDrawer implements Drawer {

    protected final Game game;

    public GameDrawer(Game game) {
        this.game = game;
    }

}

