package server;

import client.domain.Game;
import client.domain.algorithm.ChaoticMovement;
import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.anthill.AnthillPlace;
import client.domain.entities.anthill.Resources;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;
import client.ui.battle.utils.Hexagon;
import client.ui.battle.utils.HexagonResourcePoint;
import client.ui.battle.utils.HexagonalMap;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;


public class MainServer {
    public volatile static Game game;
    public static LinkedList<GameServer> servers = new LinkedList<>();
    public static ArrayList<Integer> players = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        createGame();
        var srvskt = new ServerSocket(GameServer.SocketServerPORT);
        System.out.println("Server Started");
        while (true) {
            try {
                var s = srvskt.accept();
                servers.add(new GameServer(s));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createGame() {
        var size = new Dimension(1366, 782);
        var map = new HexagonalMap(size.width, size.height, 30);
        var resourcesMap = new ResourcesMap(size, new Shape[]
                {
                        new HexagonResourcePoint(new Point(200, 300), 20, 20),
                        new HexagonResourcePoint(new Point(200, 400), 20, 20),
                        new HexagonResourcePoint(new Point(200, 500), 20, 20),
                });
        var container = new MapContainer(map, resourcesMap);
        var players = new ArrayList<Player>();
        var spawner = new ResourceSpawner(center -> new Hexagon(center, 20));
        game = new Game(container, players, spawner);
    }
}
