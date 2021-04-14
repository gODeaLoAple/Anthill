package client;

import client.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Game {

    private final Map map;
    private final Player[] players;

    public Game(Map map, Player[] players) {
        this.map = map;
        this.players = players;
    }

    public void start() {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BattleWindow(map, players));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
