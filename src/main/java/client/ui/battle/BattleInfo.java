package client.ui.battle;

import client.domain.Game;
import client.domain.entities.anthill.Anthill;
import client.net.NetDispatcher;
import client.ui.PanelsSwitcher;
import client.ui.battle.actionStates.*;
import client.ui.main.MainMenu;

import javax.swing.*;
import java.awt.*;

public class BattleInfo extends JPanel {

    private final PanelsSwitcher switcher;
    private final Label resourcesLabel;
    private final NetDispatcher dispatcher;

    public BattleInfo(PanelsSwitcher switcher, Game game, BattleField field, NetDispatcher dispatcher) {
        this.switcher = switcher;

        resourcesLabel = new Label("Ресурсы: " + game.getMainPlayer().getAnthill().getResources().getCount());
        this.dispatcher = dispatcher;
        setLayout(new BorderLayout());
        add(resourcesLabel, BorderLayout.NORTH);

        var buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 0, 5));

        var extendButton = new JButton("Расширить");
        extendButton.addActionListener(e -> field.setState(new ExtendAnthill(game, dispatcher)));
        buttons.add(extendButton, BorderLayout.NORTH);

        var attackButton = new JButton("Атаковать (" + Anthill.RESOURCE_FOR_ATTACK +")");
        attackButton.addActionListener(e -> field.setState(new Attack(game, dispatcher)));
        buttons.add(attackButton, BorderLayout.CENTER);

        var pickupButton = new JButton("Собрать");
        pickupButton.addActionListener(e -> field.setState(new PickUpResources(game, dispatcher)));
        buttons.add(pickupButton, BorderLayout.SOUTH);

        var molButton = new JButton("Нанять крота (" + HireMole.COST + ")");
        molButton.addActionListener(e -> field.setState(new HireMole(game, dispatcher)));
        buttons.add(molButton, BorderLayout.LINE_END);

        var biteAss = new JButton("Укусить за задницу");
        biteAss.addActionListener(e -> field.setState(new BiteAss(game, dispatcher)));
        buttons.add(biteAss, BorderLayout.LINE_END);


        var exit = new JButton("ВЫХОД (" + 500 + ")");
        exit.addActionListener(e -> switchToMainMenu());
        buttons.add(exit, BorderLayout.LINE_END);


        add(buttons, BorderLayout.CENTER);

        var resources = game.getMainPlayer().getAnthill().getResources();
        var timer = new Timer(100, e -> {
            var resourcesCount = resources.getCount();
            resourcesLabel.setText("Ресурсы: " + resourcesCount);
        });
        timer.start();
    }

    private void switchToMainMenu() {
        switcher.switchToPanel(new MainMenu(switcher, dispatcher));
    }

}
