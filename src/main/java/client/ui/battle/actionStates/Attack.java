package client.ui.battle.actionStates;

import client.domain.Game;

import java.awt.*;
import java.util.Arrays;

public class Attack implements PlayerActionState {

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null && canAttack(shape, game)) {
            graphics.setColor(Color.BLACK);
            graphics.fill(shape);
        }
    }

    private boolean canAttack(Shape shape, Game game){
        return !game.getMainPlayer().getAnthill().hasShape(shape)
                && Arrays.stream(game.getPlayers()).anyMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point, Game game) {
        var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape == null)
            return;
        for (var player : game.getPlayers()) {
            var anthill = player.getAnthill();
            var part = anthill.getPartByShape(shape);
            if (part != null) {
                if (canAttack(shape, game))
                    anthill.applyDamage(part, 20);
                break;
            }
        }
    }
}
