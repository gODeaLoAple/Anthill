package shared.messages;

import client.domain.Game;

import java.awt.*;

public class SpawnResources extends NetMessage {
    private final Point point;

    public SpawnResources(Point point) {
        this.point = point;
    }

    @Override
    public void handle(Game game) {
        var resourceMap = game.getResourcesMap();
        var shape = resourceMap.getShapeAtPoint(point);
        resourceMap.add(shape);
    }

    @Override
    public int getPlayerId(){
        return 0;
    }
}
