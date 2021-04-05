package client;

import client.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Game {

    public void start(Player[] players) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BattleWindow(players));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
