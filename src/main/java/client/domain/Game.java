package client.domain;

import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

public class Game {

    private final MapContainer container;
    private final Player[] players;
    private final ResourceSpawner spawner;

    public Game(MapContainer container, Player[] players, ResourceSpawner spawner) {
        this.container = container;
        this.players = players;
        this.spawner = spawner;
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

    public void step() {
        var resourceMap = getResourcesMap();
        for (var player : getPlayers()) {
            var anthill = player.getAnthill();
            var movement = anthill.getMovement();
            var shape = resourceMap.getShapeAtPoint(movement.getLocation());
            if (shape != null && movement.isAnyInRadius(anthill.getAnts())) {
                anthill
                        .getResources()
                        .change(Anthill.RESOURCE);
                resourceMap.remove(shape);
                spawner.spawn(this);
            }
        }
    }
}
