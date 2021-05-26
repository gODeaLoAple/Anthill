package client.net;

import client.domain.Game;
import shared.messages.NetMessage;

public class NetDispatcher {
    private Game game;

    public NetDispatcher(Game game){
        this.game = game;
    }

    public void receive(NetMessage message){
        message.handle(game);
    }


}
