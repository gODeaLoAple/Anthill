package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BattleWindow extends JPanel {

    private final Map map;
    private List<Player> players;
    private Point lastMousePosition;

    public BattleWindow(Map map, Player[] players) {
        super();
        this.map = map;
        setSize(640,480);
        setPreferredSize(getSize());
        lastMousePosition = new Point(0, 0);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                lastMousePosition = e.getPoint();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                var shape = map.getShapeAtPoint(lastMousePosition);
                if (shape != null)
                    players[0].getAnthill().getPlace().add(shape);
            }
        });
        this.players = List.of(players);

        //var timer = new Timer();
        //timer.scheduleAtFixedRate(new TimerTask() {
        //    @Override
        //    public void run() {
        //        repaint();
        //    }
        //}, 0, 1000);
    }

    @Override
    public void paint(Graphics g) {
        var g2d = (Graphics2D)g;
        g2d.clearRect(0, 0, 800, 600);
        players.stream()
                .map(x -> x.getAnthill().getPlace())
                .forEach(place -> {
                    for (var hexagon : place.shapes)
                        g2d.fill(hexagon);
                });

        var shape = map.getShapeAtPoint(lastMousePosition);
        if (shape != null)
            g2d.draw(shape);
        g2d.drawArc(lastMousePosition.x, lastMousePosition.y, 2, 2, 0, 360);
    }

}


