package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;

public class PlayerStats extends NetMessage {
    private int playerId;
    private int antsCount;

    public PlayerStats(Player player){
        this.playerId = player.getId();
        this.antsCount = player.getAnthill().getAnts().size();
    }

    @Override
    public void handle(Game game) {
        System.out.println(antsCount);
        System.out.println(playerId);
    }

    @Override
    public int getPlayerId(){
        return playerId;
    }
}
