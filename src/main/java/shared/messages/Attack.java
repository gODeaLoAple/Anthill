package shared.messages;

import client.domain.Game;
import client.domain.entities.Player;
import client.domain.entities.anthill.AnthillPart;

import java.awt.*;

public class Attack extends NetMessage{
    private int playerId;
    private int targetId;
    private Point point;
    private int targetHealth;

    public Attack(){}

    public Attack(Player player, Player target, AnthillPart part) {
        this();
        playerId = player.getId();
        targetId = target.getId();
        var bounds = part.getShape().getBounds();
        point = new Point((int)bounds.getCenterX(), (int)bounds.getCenterY());
        targetHealth = part.getHealth();
    }
    @Override
    public void handle(Game game) {
        var player = game.getPlayerById(playerId);
        var targetPLayer = game.getPlayerById(targetId);
        var targetAnthill = targetPLayer.getAnthill();
        var anthillPart = targetAnthill.getPartByShape(game.getPartsMap().getShapeAtPoint(point));
        if (anthillPart != null){
            anthillPart.setHealth(targetHealth);
        }
    }
}

