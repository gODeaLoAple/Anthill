package shared.messages;

import client.domain.Game;
import client.ui.battle.utils.Hexagon;

import java.awt.*;

public class SpawnResources extends NetMessage {
    private final Point point;

    public SpawnResources(Point point) {
        this.point = point;
    }

    @Override
    public void handle(Game game) {
        game.getResourcesMap().add(new Hexagon(point, 20)); // TODO
    }
}
