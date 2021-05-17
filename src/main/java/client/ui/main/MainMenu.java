package client.ui.main;

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
import client.ui.PanelsSwitcher;
import client.ui.battle.BattleWindow;
import client.ui.battle.utils.Hexagon;
import client.ui.battle.utils.HexagonResourcePoint;
import client.ui.battle.utils.HexagonalMap;
import client.ui.battle.utils.ImageProvider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class MainMenu extends JPanel {

    private final PanelsSwitcher switcher;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public MainMenu(PanelsSwitcher switcher) {
        super();
        this.switcher = switcher;
        setLayout(new BorderLayout());

        var buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 0, 5));

        var startGameButton = new JButton("Старт");
        startGameButton.addActionListener(e -> startGame());
        buttons.add(startGameButton, BorderLayout.NORTH);

        var exitGameButton = new JButton("Выход");
        exitGameButton.addActionListener(e -> exitGame());
        buttons.add(exitGameButton, BorderLayout.CENTER);

        add(buttons);
    }

    public void startGame() {
        switcher.switchToPanel(createGame());
    }

    public BattleWindow createGame() {
        var size = new Dimension(WIDTH, HEIGHT);
        var map = new HexagonalMap(size.width, size.height, HexagonalMap.RADIUS);
        var resourcesMap = createResourceMap(size);
        var container = new MapContainer(map, resourcesMap);
        var players = createPlayers(map);
        new Thread(() -> spawnAnts(players)).start();
        //spawnAnts(players);

        var spawner = new ResourceSpawner(center -> new Hexagon(center, 20));
        var game = new Game(container, players, spawner);
        var imageProvider = new ImageProvider();

        try {
            imageProvider.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        getParent().setPreferredSize(game.getResourcesMap().getSize());
        return new BattleWindow(switcher, game, imageProvider);
    }

    private ResourcesMap createResourceMap(Dimension size) {
        return new ResourcesMap(size, new Shape[]
                {
                        new HexagonResourcePoint(new Point(200, 300), 20, 20),
                        new HexagonResourcePoint(new Point(200, 400), 20, 20),
                        new HexagonResourcePoint(new Point(200, 500), 20, 20),
                });
    }

    private Player[] createPlayers(HexagonalMap map) {
        return new Player[]{
                new Player(0, new Anthill(new AnthillPlace(new AnthillPart[]{
                        new AnthillPart(map.hexagons.get(5), 100, 100),
                }), new Resources(1000), new ChaoticMovement(new Point(500, 500)))),
                new Player(1, new Anthill(new AnthillPlace(new AnthillPart[]{
                        new AnthillPart(map.hexagons.get(0), 100, 100),
                        new AnthillPart(map.hexagons.get(1), 100, 100),
                        new AnthillPart(map.hexagons.get(2), 100, 100),
                }), new Resources(), new ChaoticMovement(new Point(100, 100))))
        };
    }

    private void spawnAnts(Player[] players) {
        IntStream
                .range(1, 1000)
                .forEach(i -> players[0]
                        .getAnthill()
                        .addAnt(new SlaveAnt(new Point(500, 500), 100)));
    }

    public void exitGame() {
        System.exit(0);
    }
}

