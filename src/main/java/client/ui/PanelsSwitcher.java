package client.ui;

import javax.swing.*;

public class PanelsSwitcher {
    private final JFrame frame;
    private JPanel current;

    public PanelsSwitcher(JFrame frame) {
        this.frame = frame;
        this.current = null;
    }

    public void setCurrent(JPanel current) {
        this.current = current;
    }

    public void switchToPanel(JPanel panel) {
        if (current != null)
            frame.remove(current);
        current = panel;
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}
