package shared.messages;

import client.domain.Game;

public class PlayerStats extends NetMessage {
    private int playerId;
    private int antsCount;

    @Override
    public void handle(Game game) {

    }
}
