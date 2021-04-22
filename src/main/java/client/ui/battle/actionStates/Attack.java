package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;
import java.util.Arrays;

public class Attack implements PlayerActionState {

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics, Game game) {
        var shape = game.getMap().getShapeAtPoint(clickedPoint);
        if (shape != null) {
            if (canAddShape(game, shape)) {
                drawAttackPart(graphics, shape, game);
            }
        }
    }

    public static void drawAttackPart(Graphics2D graphics, Shape shape, Game game) {
        var r = 60;
        var rectangle = shape.getBounds();
        var originalClipBounds = graphics.getClipBounds();
        var rectX = (int) (rectangle.getCenterX() - r * Math.sqrt(3) / 2);
        var rectY = (int) (rectangle.getCenterY() - r);
        var ap = game.getMainPlayer().getAnthill().getAnthillPartById(shape);
        if (ap == null)
            return;
        var health = ap.getHealth();
        var w = rectangle.width;
        var h = rectangle.height;
        paintHexagon(graphics, rectX, rectY, h, w, shape, Color.RED, originalClipBounds, 0, health / 100);
        paintHexagon(graphics, rectX, rectY, h, w, shape, Color.GREEN, originalClipBounds,
                (int)(h * (1 - health / 100.0)), health / 100);
    }

    private static void paintHexagon(Graphics2D graphics,
                                     int x, int y,
                                     int height,
                                     int width, Shape shape,
                                     Color color, Rectangle originalClipBounds,
                                     int k1, int k2) {
        try {
            graphics.clipRect(x,
                    y + (height * k1),
                    width,
                    height * k2);
            graphics.setColor(color);
            graphics.fill(shape);
        } finally {
            graphics.setClip(originalClipBounds);
        }
    }

    private boolean canAddShape(Game game, Shape shape) {
        return Arrays.stream(game.getPlayers()).noneMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point, Game game) {
        var shape = game.getMap().getShapeAtPoint(point);
        var players = game.getPlayers();
        for (var player : players) {
            var ap = player.getAnthill().getAnthillPartById(shape);
            if (ap != null) {
                if (canAddShape(game, shape)) {
                    player.attack(player.getAnthill(), ap.getShape());
                    ap.changeHealth(-20);
                }
            }
        }
    }
}
