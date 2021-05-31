package client.ui.battle.actionStates;

import client.domain.Game;
import client.domain.entities.anthill.Anthill;
import client.net.NetDispatcher;

import java.awt.*;
import java.util.Arrays;

public class Attack extends ActionState {

    public Attack(Game game, NetDispatcher dispatcher) {
        super(game, dispatcher);
    }

    @Override
    public void paint(Point clickedPoint, Graphics2D graphics) {
        var shape = game.getPartsMap().getShapeAtPoint(clickedPoint);
        if (shape != null && canAttack(shape, game)) {
            graphics.setColor(Color.RED);
            graphics.draw(shape);
        }
    }

    private boolean canAttack(Shape shape, Game game) {
        var parts = game.getPartsMap();
        return !game.getMainPlayer().getAnthill().hasShape(shape)
                && Arrays.stream(game.getPlayers()).anyMatch(x -> x.getAnthill().hasShape(shape) &&
                parts.getNeighbours(shape).anyMatch(y -> game.getMainPlayer().getAnthill().hasShape(y)));
    }

    @Override
    public void clicked(Point point) {
        final var shape = game.getPartsMap().getShapeAtPoint(point);
        if (shape == null)
            return;
        final var mainAnthill = game.getMainPlayer().getAnthill();
        for (var player : game.getPlayers()) {
            final var anthill = player.getAnthill();
            final var part = anthill.getPartByShape(shape);
            if (part != null) {
                if (canAttack(shape, game)) {
                    var res = mainAnthill.getResources();
                    if (mainAnthill.hasEnoughResourcesToAttack()) {
                        anthill.applyDamage(part, Anthill.RESOURCE_FOR_ATTACK);
                        res.change(-Anthill.RESOURCE_FOR_ATTACK);
                        dispatcher.send(new shared.messages.Attack(game.getMainPlayer(), player, part));
                    }
                }
                break;
            }
        }
    }
}
