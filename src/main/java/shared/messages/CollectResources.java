package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;

public class CollectResources extends NetMessage{
    private final int playerId;
    private final Point point;

    public CollectResources(Player player, Point point) {
        playerId = player.getId();
        this.point = point;
    }
    @Override
    public void handle(Game game) {
        var player = game.getPlayerById(playerId);
        var shape = game.getResourcesMap().getShapeAtPoint(point);
        if (shape != null)
            game.getResourcesMap().remove(shape);
    }
}
