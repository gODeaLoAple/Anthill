package client.ui.battle;

import client.domain.Game;
import client.ui.PanelsSwitcher;
import client.ui.battle.actionStates.Attack;
import client.ui.battle.actionStates.ExtendAnthill;
import client.ui.battle.actionStates.HireMole;
import client.ui.battle.actionStates.PickUpResources;
import client.ui.main.MainMenu;

import javax.swing.*;
import java.awt.*;

public class BattleInfo extends JPanel {

    private final PanelsSwitcher switcher;
    private final Label resourcesLabel;

    public BattleInfo(PanelsSwitcher switcher, Game game, BattleField field) {
        this.switcher = switcher;

        resourcesLabel = new Label("Ресурсы: " + game.getMainPlayer().getAnthill().getResources().getCount());
        setLayout(new BorderLayout());
        add(resourcesLabel, BorderLayout.NORTH);

        var buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 0, 5));

        var extendButton = new JButton("Расширить");
        extendButton.addActionListener(e -> field.setState(new ExtendAnthill(game)));
        buttons.add(extendButton, BorderLayout.NORTH);

        var attackButton = new JButton("Атаковать");
        attackButton.addActionListener(e -> field.setState(new Attack(game)));
        buttons.add(attackButton, BorderLayout.CENTER);

        var pickupButton = new JButton("Собрать");
        pickupButton.addActionListener(e -> field.setState(new PickUpResources(game)));
        buttons.add(pickupButton, BorderLayout.SOUTH);

        var molButton = new JButton("Нанять крота");
        molButton.addActionListener(e -> field.setState(new HireMole(game)));
        buttons.add(molButton, BorderLayout.LINE_END);

        var armagedon = new JButton("ВЫЗВАТЬ АРМАГЕДОН");
        armagedon.addActionListener(e -> field.setState(new HireMole(game)));
        buttons.add(armagedon, BorderLayout.LINE_END);


        var exit = new JButton("ВЫХОД");
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
        switcher.switchToPanel(new MainMenu(switcher));
    }

}
