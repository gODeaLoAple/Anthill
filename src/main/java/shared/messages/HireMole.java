package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

import java.awt.*;
import java.io.Serializable;

public class HireMole extends NetMessage implements Serializable {
    private final int playerId;
    private final Point destination;


    public HireMole(Player player, Point destination) {
        this.playerId = player.getId();
        this.destination = destination;
    }

    @Override
    public void handle(Game game) {
        var player = game.getPlayerById(playerId);
        player.getAnthill().hireMole(destination);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }
}
