package client;

import java.awt.*;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        var players = new Player[] {
            new Player(0, new Anthill(new AnthillPlace(createPolygon(new Point[] {
                    new Point(0, 0),
                    new Point(0, 100),
                    new Point(100, 100),
                    new Point(100, 200),
                    new Point(200, 200),
                    new Point(300, 200),
                    new Point(300, 0)
            }))))
        };
        var game = new Game();
        game.start(players);
    }

    private static Polygon createPolygon(Point[] points) {
        final var count = points.length;
        var start = new Point(200, 200);

        final var xs = new int[count];
        final var ys = new int[count];
        for (var i = 0; i < count; ++i) {
            xs[i] = (int)points[i].getX() + start.x;
            ys[i] = (int)points[i].getY() + start.y;
        }
        return new Polygon(xs, ys, count);
    }
}
