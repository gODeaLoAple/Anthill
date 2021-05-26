package shared.messages;

import client.domain.Game;
import client.domain.entities.anthill.Anthill;

import java.awt.*;

public class CollectResources extends NetMessage{
    private int playerId;
    private Point point;

    @Override
    public void handle(Game game) {
        var player = game.getPLayerById(playerId);
        var shape = game.getResourcesMap().getShapeAtPoint(point);
        if (shape != null)
            game.getResourcesMap().remove(shape);
    }
}
