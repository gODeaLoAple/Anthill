package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

import java.io.Serializable;

public class PlayerId extends NetMessage implements Serializable {
    public final int playerId;
    private final Player player;

    public PlayerId(Player player){
        playerId = player.getId();
        this.player = player;
    }

    public PlayerId(int id){
        playerId = id;
        player = null;
    }

    @Override
    public void handle(Game game) {
        if (playerId != game.getPlayerId())
            game.addPlayer(player);
        System.out.println("Add player by id: " + playerId);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }
}
