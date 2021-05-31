package server;

import client.domain.Game;
import client.domain.algorithm.ChaoticMovement;
import client.domain.algorithm.ResourceSpawner;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.anthill.AnthillPlace;
import client.domain.entities.anthill.Resources;
import client.domain.entities.ants.SlaveAnt;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;
import client.ui.battle.utils.Hexagon;
import client.ui.battle.utils.HexagonResourcePoint;
import client.ui.battle.utils.HexagonalMap;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.stream.IntStream;

public class GameServer {
    private static Game createGame(){
        var size = new Dimension(1366, 782);
        var map = new HexagonalMap(size.width, size.height, 30);
        var resourcesMap = new ResourcesMap(size, new Shape[]
                {
                        new HexagonResourcePoint(new Point(200, 300), 20, 20),
                        new HexagonResourcePoint(new Point(200, 400), 20, 20),
                        new HexagonResourcePoint(new Point(200, 500), 20, 20),
                });
        var container = new MapContainer(map, resourcesMap);
        var players = new Player[]{
                new Player(0, new Anthill(new AnthillPlace(new AnthillPart[]{
                        new AnthillPart(map.hexagons.get(5), 100),
                }), new Resources(1000), new ChaoticMovement(new Point(500, 500)))),
                new Player(1, new Anthill(new AnthillPlace(new AnthillPart[]{
                        new AnthillPart(map.hexagons.get(0), 100),
                        new AnthillPart(map.hexagons.get(1), 100),
                        new AnthillPart(map.hexagons.get(2), 100),
                }), new Resources(), new ChaoticMovement(new Point(100, 100))))
        };

        addAnts(players[0], 1_000, new Point(500, 500));
        addAnts(players[1], 5_000, new Point(100, 100));


        var spawner = new ResourceSpawner(center -> new Hexagon(center, 20));
        return new Game(container, players, spawner);
    }

    private static void addAnts(Player player, int count, Point start) {
        var anthill = player.getAnthill();
        IntStream
                .range(0, count)
                .forEach(i -> anthill
                        .addAnt(new SlaveAnt(start, 100)));
    }


    public static void main(String[] args) throws IOException {
        var socket = new ServerSocket(500);
        var game = createGame();
        var logic = new Thread(() -> {
            while (true) {
                game.step();
            }
        });

        logic.setDaemon(true);
        logic.start();
    }
}