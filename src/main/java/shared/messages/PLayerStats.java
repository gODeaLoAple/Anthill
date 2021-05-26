package shared.messages;

import client.domain.Game;

public class PLayerStats extends NetMessage {
    private int playerId;
    private int antsCount;

    @Override
    public void handle(Game game) {

    }
}
