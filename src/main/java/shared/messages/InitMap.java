package shared.messages;

import client.domain.Game;

import java.io.Serializable;

public class InitMap extends NetMessage implements Serializable {
    private Game game;

    public InitMap(Game game){
        this.game = game;
    }

    @Override
    public void handle(Game game) {
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public int getPlayerId(){
        return game.getPlayerId();
    }
}
