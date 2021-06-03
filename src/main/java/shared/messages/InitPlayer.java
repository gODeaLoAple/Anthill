package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;

import java.io.Serializable;

public class InitPlayer extends NetMessage implements Serializable {
    private final int playerId;
    private final Anthill anthill;

    public InitPlayer(Player player){
        playerId = player.getId();
        this.anthill = player.getAnthill();
    }

    @Override
    public void handle(Game game) {
        game.addPlayer(new Player(playerId, anthill));
        System.out.println("Add player by id: " + playerId);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }
}
