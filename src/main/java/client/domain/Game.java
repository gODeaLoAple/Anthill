package client.domain;

import client.domain.entities.Player;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

public class Game {

    private final MapContainer container;
    private Player[] players;

    public Game(MapContainer container, Player[] players) {
        this.container = container;
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Map getPartsMap() {
        return container.getParts();
    }

    public ResourcesMap getResourcesMap() { return container.getResources(); }

    public Player getMainPlayer() {
        return players[0];
    }

    public void removePLayer(Player player){
        var res = new Player[players.length - 1];
        var c = 0;
        for (var i = 0; i < players.length; i++){
            if (players[i].equals(player)){
                c = 1;
                continue;
            }
            res[i - c] = players[i];
        }
        players = res;
    }

    public boolean checkIsAlive(Player player){
        return player.getAnthill().getShapes().size() != 0;
    }

    public boolean isGameOver(){
        return players.length == 1;
    }
}
