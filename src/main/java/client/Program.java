package client;

import client.net.NetDispatcher;
import client.ui.PanelsSwitcher;
import client.ui.main.MainMenu;
import shared.messages.NetMessage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Program {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            var frame = new JFrame("Anthill");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(800, 600));
            var switcher = new PanelsSwitcher(frame);
            NetDispatcher dispatcher = null;
            try {
                dispatcher = new NetDispatcher();
            } catch (IOException e) {
                e.printStackTrace();
            }
            var mainMenu = new MainMenu(switcher, dispatcher);
            switcher.setCurrent(mainMenu);
            frame.add(mainMenu);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
