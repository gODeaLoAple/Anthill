package client.ui.main;

import client.domain.Game;
import client.net.NetDispatcher;
import client.ui.PanelsSwitcher;
import client.ui.battle.BattleWindow;
import client.ui.battle.utils.ImageProvider;
import shared.messages.CollectResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu extends JPanel {

    private final PanelsSwitcher switcher;
    private final NetDispatcher dispatcher;

    public MainMenu(PanelsSwitcher switcher, NetDispatcher dispatcher) {
        super();
        this.switcher = switcher;
        this.dispatcher = dispatcher;
        setLayout(new BorderLayout());

        var buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 0, 5));

        var startGameButton = new JButton("Старт");
        startGameButton.addActionListener(e -> {
            try {
                startGame();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });
        buttons.add(startGameButton, BorderLayout.NORTH);

        var exitGameButton = new JButton("Выход");
        exitGameButton.addActionListener(e -> exitGame());
        buttons.add(exitGameButton, BorderLayout.CENTER);

        add(buttons);
    }

    public void startGame() throws IOException, ClassNotFoundException {
        switcher.switchToPanel(createGame());
    }

    public BattleWindow createGame() throws IOException, ClassNotFoundException {
        var game = getGame();
        var imageProvider = new ImageProvider();

        try {
            imageProvider.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        getParent().setPreferredSize(game.getResourcesMap().getSize());
        setDispatcher(game, dispatcher);
        return new BattleWindow(switcher, game, imageProvider, dispatcher);
    }

    private Game getGame() {
        return dispatcher.getGame();
    }

    private void setDispatcher(Game game, NetDispatcher dispatcher) throws IOException {
        dispatcher.setGame(game);
        game.setPickupObserver((player, res) -> {
            var bound = res.getBounds2D();
            dispatcher.send(new CollectResources(player, new Point((int) bound.getCenterX(), (int) bound.getCenterY())));
        });
    }


    public void exitGame() {
        System.exit(0);
    }
}

