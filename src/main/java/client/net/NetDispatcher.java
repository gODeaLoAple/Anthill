package client.net;

import client.domain.Game;
import shared.messages.NetMessage;

public class NetDispatcher {
    private Game game;

    public void receive(NetMessage message){
        if (game != null)
            message.handle(game);
    }

    public void send(NetMessage message) {
        System.out.println(message.toString());
    }

    public void setGame(Game game) {
        this.game = game;
    }
}