package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;
import java.io.Serializable;

public class ExtendAnthill extends NetMessage implements Serializable {
    private final Point point;
    private final int playerId;

    public ExtendAnthill(Player player, Point point) {
        playerId = player.getId();
        this.point = point;
    }

    @Override
    public void handle(Game game) {
        var player = game.getPlayerById(playerId);
        var shape = game.getPartsMap().getShapeAtPoint(point);
        player.getAnthill().extend(shape);
    }

    public int getPlayerId(){
        return playerId;
    }
}
