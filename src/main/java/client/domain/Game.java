package client.domain;

public class Game {

    private final Map map;
    private final Player[] players;

    public Game(Map map, Player[] players) {
        this.map = map;
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Map getMap() {
        return map;
    }

}
