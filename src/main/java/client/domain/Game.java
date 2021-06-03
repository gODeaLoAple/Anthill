package client.domain;

import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.map.Map;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Game implements Serializable {
    private MapContainer container;
    private final ArrayList<Player> players;
    private ResourceSpawner spawner;
    private ResourcePickupObserver pickupObserver;
    private int playerId;

    public Game(MapContainer container, ArrayList<Player> players, ResourceSpawner spawner) {
        this.container = container;
        this.players = players;
        this.spawner = spawner;
    }

    public Game(Game other){
        this(other.getContainer(), other.getPlayers(), other.getResourceSpawner());
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Map getPartsMap() {
        return container.getParts();
    }

    public void setPartsMap(MapContainer map) {
        this.container = map;
    }

    public ResourceSpawner getResourceSpawner() {
        return spawner;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players.addAll(players);
    }

    public void setResourceSpawner(ResourceSpawner sp) {
        this.spawner = sp;
    }

    public MapContainer getContainer() {
        return container;
    }

    public ResourcesMap getResourcesMap() {
        return container.getResources();
    }

    public  int getPlayerId() {
        return playerId;
    }

    public Player getMainPlayer() {
        return players.get(playerId);
    }

    public void setMainPlayer(int id){
        playerId = id;
    }

    public Player getPlayerById(int id) {
        return players.get(id);
    }

    public synchronized void step() {

        try {
            handleResources();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        handlePlayersCount();
        moveAnts();
        //handleAntBitesAss();
    }

    private void handleResources() throws IOException, ClassNotFoundException {
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

    public void moveAnts() {
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
        players.remove(player);
    }

    private boolean checkIsAlive(Player player) {
        return player.getAnthill().getShapes().size() != 0;
    }

    public boolean isGameOver() {
        return players.size() == 1000;
    }

    public void setPickupObserver(ResourcePickupObserver pickupObserver) {
        this.pickupObserver = pickupObserver;
    }
}
