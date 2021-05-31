package client.domain;

import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {

    private final MapContainer container;
    private Player[] players;
    private final ResourceSpawner spawner;
    private ResourcePickupObserver pickupObserver;

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

    public ResourcesMap getResourcesMap() {
        return container.getResources();
    }

    public Player getMainPlayer() {
        return players[0];
    }

    public Player getPlayerById(int id){
        return players[id];
    }

    public void step() {
        handleResources();
        handlePlayersCount();
        moveAnts();
        //handleAntBitesAss();
    }

    private void handleResources() {
        var resourceMap = getResourcesMap();
        var shapesToRemove = new ArrayList<Shape>();
        for (var player : getPlayers()) {
            var anthill = player.getAnthill();
            var movement = anthill.getMovement();
            var ants = anthill.getAnts();

            for (var res : getResourcesMap().getShapes()) {
                if (movement.isAnyNearPoint(ants, res.getBounds().getLocation())) {
                    anthill
                            .getResources()
                            .change(Anthill.RESOURCE);
                    shapesToRemove.add(res);
                    if (pickupObserver != null)
                        pickupObserver.onResourcePickup(player, res);
                }
            }

        }
        shapesToRemove.forEach(resourceMap::remove);
    }

    private void moveAnts() {
        for (var player : getPlayers()) {
            var movement = player.getAnthill().getMovement();
            var ants = player.getAnthill().getAnts();
            for (var ant : ants)
                movement.moveAnt(ant);
        }
    }

    private void handlePlayersCount() {
        for (var player : getPlayers()) {
            if (!checkIsAlive(player)) {
                removePLayer(player);
            }
        }
    }

    private void handleAntBitesAss() {
        for (var player : players) {
            var anthill = player.getAnthill();
            for (var other : players) {
                if (player != other) {
                    anthill.battle(other.getAnthill());
                }
            }
        }
        for (var player : players) {
            player.getAnthill().removeDeadAnts();
        }
    }

    private void removePLayer(Player player) {
        var res = new Player[players.length - 1];
        var c = 0;
        for (var i = 0; i < players.length; i++) {
            if (players[i].equals(player)) {
                c = 1;
                continue;
            }
            res[i - c] = players[i];
        }
        players = res;
    }

    private boolean checkIsAlive(Player player) {
        return player.getAnthill().getShapes().size() != 0;
    }

    public boolean isGameOver() {
        return players.length == 1;
    }

    public void setPickupObserver(ResourcePickupObserver pickupObserver) {
        this.pickupObserver = pickupObserver;
    }
}
