package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;

import java.awt.*;
import java.io.Serializable;

public class CollectResources extends NetMessage implements Serializable {
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
        if (shape != null){
            game.getResourcesMap().remove(shape);
            player.getAnthill().getResources().change(Anthill.RESOURCE);
            game.moveAnts();
        }
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }
}
