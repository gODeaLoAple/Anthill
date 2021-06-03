package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;
import java.io.Serializable;

public class MovementSetPosition extends NetMessage implements Serializable {
    private final Point position;
    private final int playerId;

    public MovementSetPosition(Player player, Point point) {
        playerId = player.getId();
        position = point;
    }

    @Override
    public void handle(Game game) {
        var player = game.getPlayerById(playerId);
        player.getAnthill().getMovement().setLocation(position);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }
}
