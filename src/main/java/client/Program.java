package client;

import client.domain.*;
import client.domain.algorithm.ChaoticMovement;
import client.domain.entities.*;
import client.domain.entities.anthill.Anthill;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.anthill.AnthillPlace;
import client.domain.entities.anthill.Resources;
import client.domain.entities.ants.SlaveAnt;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;
import client.ui.battle.HexagonResourcePoint;
import client.ui.battle.HexagonalMap;
import client.ui.battle.BattleWindow;
import client.ui.battle.ImageProvider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Program {
    public static void main(String[] args) {
        var size = new Dimension(800, 600);
        var map = new HexagonalMap(size.width, size.height, 60);
        var resourcesMap = new ResourcesMap(size, new Shape[]
                {
                        new HexagonResourcePoint(new Point(200, 300), 20, 20),
                        new HexagonResourcePoint(new Point(200, 400), 20, 20),
                        new HexagonResourcePoint(new Point(200, 500), 20, 20),
                });
        var container = new MapContainer(map, resourcesMap);
        var players = new Player[] {
            new Player(0, new Anthill(new AnthillPlace(new AnthillPart[]{
                    new AnthillPart(map.hexagons[5], 100, 100),
            }), new Resources(1000), new ChaoticMovement(new Point(500, 500)))),
            new Player(1, new Anthill(new AnthillPlace(new AnthillPart[] {
                    new AnthillPart(map.hexagons[0], 100, 100),
                    new AnthillPart(map.hexagons[1], 100, 100),
                    new AnthillPart(map.hexagons[2], 100, 100),
            }), new Resources(), new ChaoticMovement(new Point(100,100))))
        };

        players[0].getAnthill().addAnt(new SlaveAnt(new Point(500, 500), 100));
        var game = new Game(container, players);
        var imageProvider = new ImageProvider();

        try {
            imageProvider.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        SwingUtilities.invokeLater(() -> {
            var frame = new JFrame("Anthill");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BattleWindow(game, imageProvider));
            frame.setPreferredSize(map.getSize());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

}
