package client.ui.battle.actionStates;

import client.domain.AnthillPart;
import client.domain.Game;
import client.domain.Player;
import client.ui.battle.BattleField;

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
        var originalClipBounds = graphics.getClipBounds();
        var rectX = (int) shape.getBounds().getCenterX() - r * Math.sqrt(3) / 2;
        var rectY = (int) shape.getBounds().getCenterY() - r;
        var ap = game.getMainPlayer().getAnthill().getAnthillPartById(shape);
        if (ap == null)
            return;
        var health = ap.getHealth();
        var w = shape.getBounds().width;
        var h = shape.getBounds().height;


        try {
            graphics.clipRect((int) rectX,
                    rectY,
                    w,
                    (int) (h * (1 - health / 100.0)));
            graphics.setColor(Color.RED);
            graphics.fill(shape);
        } finally {
            graphics.setClip(originalClipBounds);
        }

        try {
            graphics.clipRect((int) rectX,
                    rectY + (int) (h * (1 - health / 100.0)),
                    w,
                    (int) (h * health / 100.0));
            graphics.setColor(Color.GREEN);
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

            assert shape != null;
            var anthillPartByAnthillPart = player.getAnthill().getAnthillPartById(shape);
            if (anthillPartByAnthillPart != null) {
                if (canAddShape(game, shape)){
                    player.attack(player.getAnthill(), anthillPartByAnthillPart.getShape());
                    anthillPartByAnthillPart.changeHealth(-20);
                }
            }
        }
    }
}
