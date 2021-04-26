package client.ui.battle.actionStates;

import client.domain.Game;
import client.domain.entities.Anthill;

import java.awt.*;
import java.util.Arrays;

public class Attack extends ActionState {

    public Attack(Game game) {
        super(game);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null && canAttack(shape, game)) {
            graphics.setColor(Color.RED);
            graphics.draw(shape);
        }
    }

    private boolean canAttack(Shape shape, Game game){
        return !game.getMainPlayer().getAnthill().hasShape(shape)
                && Arrays.stream(game.getPlayers()).anyMatch(x -> x.getAnthill().hasShape(shape));
    }

    @Override
    public void clicked(Point point) {
        var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape == null)
            return;
        var res = game.getMainPlayer().getAnthill().getResources();
        for (var player : game.getPlayers()) {
            var anthill = player.getAnthill();
            var part = anthill.getPartByShape(shape);
            if (part != null) {
                if (canAttack(shape, game)){
                    if (res.getCount() >= Anthill.RESOURCE_FOR_ATTACK){
                        anthill.applyDamage(part, 20);
                        res.change(-Anthill.RESOURCE_FOR_ATTACK);
                    }
                }
                break;
            }
        }
    }
}
