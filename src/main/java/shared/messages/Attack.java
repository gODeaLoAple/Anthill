package shared.messages;

import client.domain.Game;
import client.domain.entities.anthill.Anthill;

import java.awt.*;

public class Attack extends NetMessage{
    private int playerId;
    private int targetPlayerId;
    private Point target;
    private int targetAnthillHealth;
    @Override
    public void handle(Game game) {
        var player = game.getPLayerById(playerId);
        var targetPLayer = game.getPLayerById(targetPlayerId);
        var targetAnthill = targetPLayer.getAnthill();
        var anthillPart = targetAnthill.getPartByShape(game.getPartsMap().getShapeAtPoint(target));
        if (anthillPart != null){
            anthillPart.setHealth(targetAnthillHealth);
        }
    }
}
