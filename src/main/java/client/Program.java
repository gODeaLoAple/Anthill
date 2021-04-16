package client;

import client.domain.*;
import client.ui.Hexagon;
import client.ui.HexagonalMap;
import client.ui.battle.BattleWindow;

import javax.swing.*;

public class Program {

    public static void main(String[] args) {
        var map = new HexagonalMap(800, 600, 60);
        var players = new Player[] {
            new Player(0, new Anthill(new AnthillPlace(new AnthillPart[0]), new Resources(100))),
            new Player(1, new Anthill(new AnthillPlace(new AnthillPart[] {
                    new AnthillPart(map.hexagons[0], 100, 100),
                    new AnthillPart(map.hexagons[1], 100, 100),
                    new AnthillPart(map.hexagons[2], 100, 100),
            }), new Resources()))
        };
        var game = new Game(map, players);

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
