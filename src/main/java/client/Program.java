package client;

import client.domain.*;
import client.domain.entities.*;
import client.domain.entities.anthill.Anthill;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.anthill.AnthillPlace;
import client.domain.entities.anthill.Resources;
import client.domain.map.AntsMap;
import client.domain.map.MapContainer;
import client.domain.map.ResourcesMap;
import client.ui.battle.HexagonResourcePoint;
import client.ui.battle.HexagonalMap;
import client.ui.battle.BattleWindow;

import javax.swing.*;
import java.awt.*;


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
        var ants = new AntsMap(size, new Shape[0]);
        var container = new MapContainer(map, resourcesMap, ants);
        var players = new Player[] {
            new Player(0, new Anthill(new AnthillPlace(new AnthillPart[]{
                    new AnthillPart(map.hexagons[5], 100, 100),
            }), new Resources(1000))),
            new Player(1, new Anthill(new AnthillPlace(new AnthillPart[] {
                    new AnthillPart(map.hexagons[0], 100, 100),
                    new AnthillPart(map.hexagons[1], 100, 100),
                    new AnthillPart(map.hexagons[2], 100, 100),
            }), new Resources()))
        };
        var game = new Game(container, players);

        SwingUtilities.invokeLater(() -> {
            var frame = new JFrame("Anthill");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BattleWindow(game));
            frame.setPreferredSize(map.getSize());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

}
