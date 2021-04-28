package client.domain;

import client.domain.entities.Player;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

public class Game {

    private final MapContainer container;
    private final Player[] players;

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
}
