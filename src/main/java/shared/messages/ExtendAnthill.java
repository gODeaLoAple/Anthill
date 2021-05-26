package shared.messages;

import client.domain.Game;

import java.awt.*;

public class ExtendAnthill extends NetMessage {
    private Point point;
    private int playerId;

    @Override
    public void handle(Game game) {
        var player = game.getPLayerById(playerId);
        var shape = game.getPartsMap().getShapeAtPoint(point);
        player.getAnthill().extend(shape);
    }
}
