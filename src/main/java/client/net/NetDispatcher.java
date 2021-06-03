package client.net;

import client.domain.Game;
import server.GameServer;
import server.MainServer;
import shared.messages.ExtendAnthill;
import shared.messages.InitMap;
import shared.messages.NetMessage;
import shared.messages.PlayerId;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NetDispatcher {
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    public Game game;
    private Socket socket;
    public Integer playerId;

    public NetDispatcher() throws IOException {
        try {
            var address = InetAddress.getByName(null);
            socket = new Socket(address, GameServer.SocketServerPORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            send(new PlayerId(-1));
            playerId = ((PlayerId)in.readObject()).playerId;
            System.out.println("ПОЛУЧИЛ ID: " + playerId);
            game = ((InitMap) in.readObject()).getGame();
            var net = new Thread(this::handle);
            net.setDaemon(true);
            net.start();
            System.out.println("ПОЛУЧИЛ GAYME: " + game.getClass().getSimpleName());
            System.out.println("ИГРОКОВ В GAYME: " + game.getPlayers().size());
            game.setMainPlayer(playerId);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void receive(NetMessage message) throws IOException, ClassNotFoundException {
        System.out.println("ПОЛУЧИЛ MESSAGE " + message.getClass().getSimpleName());
        message.handle(game);
    }

    public void handle() {
        while (true) {
            try {
                var message = (NetMessage) in.readObject();
                receive(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void send(NetMessage message) throws IOException, ClassNotFoundException {
        System.out.println("ОТСЫЛАЮ " + message.getClass().getSimpleName());
        out.writeObject(message);
        out.flush();
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}