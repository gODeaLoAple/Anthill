package client.domain;

import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

public class Game {

    private final MapContainer container;
    private Player[] players;
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
        handleResources();
        handlePlayersCount();
    }

    private void handleResources() {
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

    private void handlePlayersCount() {
        for (var player : getPlayers())
            if (!checkIsAlive(player)){
                removePLayer(player);
            }
    }

    private void removePLayer(Player player){
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

    private boolean checkIsAlive(Player player){
        return player.getAnthill().getShapes().size() != 0;
    }

    public boolean isGameOver(){
        return players.length == 1;
    }
}
