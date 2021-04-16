package client.ui.main;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public void create() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(500, 400));

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

