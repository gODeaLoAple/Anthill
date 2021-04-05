package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BattleWindow extends JPanel {

    private List<Player> players;
    private Point lastMousePosition;

    public BattleWindow(Player[] players) {
        super();
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
                    var circle = place.getCenter();
                    g2d.drawArc(circle.x, circle.y, 2, 2, 0, 360);
                    g2d.drawPolygon(place.getPolygon());

                    var segment = new SegmentFinder(place.getPolygon()).getSegmentForPoint(lastMousePosition);
                    if (segment != null) {
                        g2d.drawLine(segment.start.x, segment.start.y, lastMousePosition.x, lastMousePosition.y);
                        g2d.drawLine(segment.end.x, segment.end.y, lastMousePosition.x, lastMousePosition.y);
                    }
                });
        g2d.drawArc(lastMousePosition.x, lastMousePosition.y, 2, 2, 0, 360);
    }

}

