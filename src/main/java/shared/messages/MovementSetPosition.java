package shared.messages;

import client.domain.Game;

import java.awt.*;

public class MovementSetPosition extends NetMessage {
    private Point position;
    private int playerId;

    @Override
    public void handle(Game game) {
        var player = game.getPLayerById(playerId);
        player.getAnthill().getMovement().setLocation(position);
    }
}
