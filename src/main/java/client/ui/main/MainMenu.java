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
import client.ui.battle.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.stream.IntStream;

public class MainMenu extends JPanel {

    private final PanelsSwitcher switcher;

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
        var size = new Dimension(1366, 782);
        var map = new HexagonalMap(size.width, size.height, 30);
        var resourcesMap = new ResourcesMap(size, new Shape[]
                {
                        new HexagonResourcePoint(new Point(200, 300), 20, 20),
                        new HexagonResourcePoint(new Point(200, 400), 20, 20),
                        new HexagonResourcePoint(new Point(200, 500), 20, 20),
                });
        var container = new MapContainer(map, resourcesMap);
        var players = new Player[] {
                new Player(0, new Anthill(new AnthillPlace(new AnthillPart[]{
                        new AnthillPart(map.hexagons.get(5), 100, 100),
                }), new Resources(1000), new ChaoticMovement(new Point(500, 500)))),
                new Player(1, new Anthill(new AnthillPlace(new AnthillPart[] {
                        new AnthillPart(map.hexagons.get(0), 100, 100),
                        new AnthillPart(map.hexagons.get(1), 100, 100),
                        new AnthillPart(map.hexagons.get(2), 100, 100),
                }), new Resources(), new ChaoticMovement(new Point(100,100))))
        };

        addAnts(players[0], 10, new Point(500, 500));
        //addAnts(players[1], 1001, new Point(100, 100));



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

    private void addAnts(Player player, int count, Point start) {
        var anthill = player.getAnthill();
        IntStream
                .range(0, count)
                .forEach(i -> anthill
                        .addAnt(new SlaveAnt(start, 100)));
    }
    public void exitGame() {
        System.exit(0);
    }
}

