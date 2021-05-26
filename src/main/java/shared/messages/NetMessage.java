package shared.messages;

import client.domain.Game;

public abstract class NetMessage {
    public abstract void handle(Game game);
}

