package server;

import client.domain.algorithm.ChaoticMovement;
import client.domain.entities.Player;
import client.domain.entities.anthill.Anthill;
import client.domain.entities.anthill.AnthillPart;
import client.domain.entities.anthill.AnthillPlace;
import client.domain.entities.anthill.Resources;
import client.domain.entities.ants.SlaveAnt;
import shared.messages.InitMap;
import shared.messages.NetMessage;
import shared.messages.PlayerId;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.stream.IntStream;


public class GameServer extends Thread {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private Integer playerId;
    public static final int SocketServerPORT = 8080;

    public GameServer(Socket s) throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());
        out.flush();
        playerId = MainServer.players.size();
        MainServer.players.add(playerId);
        System.out.println("Server Started");
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                var message = (NetMessage) in.readObject();
                if (message == null) continue;
                System.out.println("ПОЛУЧИЛ " + message.getClass().getSimpleName());

                if (message instanceof PlayerId) {
                    var newId = MainServer.players.size() - 1;
                    var newPlayer = addPlayer(newId);
                    System.out.println("ОТСОСАЛ GAME");
                    sendAll(new PlayerId(newPlayer));
                    sendAll(new InitMap(MainServer.game));
                    out.flush();
                    out.reset();
                    continue;
                }
                message.handle(MainServer.game);
                sendAll(message);
                Thread.sleep(100);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void write(NetMessage nm) {
        try {
            out.writeObject(nm);
            out.flush();
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAll(NetMessage message) throws IOException {
        MainServer.servers.forEach(gs -> gs.write(message));
    }

    public Player addPlayer(int id) {
        var rndY = ChaoticMovement.rnd.nextInt(500);
        var rndX = ChaoticMovement.rnd.nextInt(500);
        var hexs = MainServer.game.getContainer().getHexagonalMap().hexagons;
        var rndShape = ChaoticMovement.rnd.nextInt(hexs.size());
        var anthill = new Anthill(new AnthillPlace(new AnthillPart[]{new AnthillPart(hexs.get(rndShape), 100),
        }), new Resources(1000), new ChaoticMovement(new Point(rndX, rndY)));

        var player = new Player(id, anthill);
        addAnts(player, 15, new Point(500, 500));
        MainServer.game.addPlayer(player);
        return player;
    }

    public static void addAnts(Player player, int count, Point start) {
        var anthill = player.getAnthill();
        IntStream
                .range(0, count)
                .forEach(i -> anthill
                        .addAnt(new SlaveAnt(start, 100)));
    }
}