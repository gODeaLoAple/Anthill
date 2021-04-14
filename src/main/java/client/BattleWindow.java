package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BattleWindow extends JPanel {

    private List<Player> players;
    private Point lastMousePosition = new Point(0, 0);
    private Polygon pp;

    public BattleWindow(Player[] players) {
        super();
        setSize(640, 480);
        setPreferredSize(getSize());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                lastMousePosition = e.getPoint();
                paint(getGraphics());
            }
        });
        this.players = List.of(players);

    }

    public Point getLastMousePosition() {
        return lastMousePosition;
    }

    @Override
    public void paint(Graphics g) {
        var g2d = (Graphics2D) g;
        final int size = 50;
        if (lastMousePosition.x != 0 && lastMousePosition.y != 0) {
            pp = new Polygon(new int[]{lastMousePosition.x, lastMousePosition.x + size, lastMousePosition.x + size, lastMousePosition.x},
                    new int[]{lastMousePosition.y, lastMousePosition.y, lastMousePosition.y + size, lastMousePosition.y + size}, 4);
        }
        else
            pp = new Polygon(new int[]{size, size * 2, size * 2, size}, new int[]{size, size, size * 2, size * 2}, 4);
        g2d.drawPolygon(pp);

//      g2d.clearRect(0, 0, 800, 600);
//        players.stream()
//                .map(x -> x.getAnthill().getPlace())
//                .forEach(place -> {
//                    var circle = place.getCenter();
//                    g2d.drawArc(circle.x, circle.y, 2, 2, 0, 360);
//                    g2d.drawPolygon(place.getPolygon());
//
//                    var segment = new SegmentFinder(place.getPolygon()).getSegmentForPoint(lastMousePosition);
//                    if (segment != null) {
//                        g2d.drawLine(segment.start.x, segment.start.y, lastMousePosition.x, lastMousePosition.y);
//                        g2d.drawLine(segment.end.x, segment.end.y, lastMousePosition.x, lastMousePosition.y);
//                    }
//                });
        //g2d.drawArc(lastMousePosition.x, lastMousePosition.y, 2, 2, 0, 360);
    }

}

