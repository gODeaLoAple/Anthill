package client;

import client.ui.PanelsSwitcher;
import client.ui.main.MainMenu;

import javax.swing.*;
import java.awt.*;


public class Program {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var frame = new JFrame("Anthill");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(800, 600));
            var switcher = new PanelsSwitcher(frame);
            var mainMenu = new MainMenu(switcher);
            switcher.setCurrent(mainMenu);
            frame.add(mainMenu);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
