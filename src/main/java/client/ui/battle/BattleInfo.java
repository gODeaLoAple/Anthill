package client.ui.battle;

import client.domain.Game;
import client.ui.battle.actionStates.Attack;
import client.ui.battle.actionStates.ExtendAnthill;
import client.ui.battle.actionStates.PickUpResources;

import javax.swing.*;
import java.awt.*;

public class BattleInfo extends JPanel {

    private final Game game;
    private final Label resourcesLabel;

    public BattleInfo(Game game, BattleField field) {

        this.game = game;

        resourcesLabel = new Label("Ресурсы: 0");
        setLayout(new BorderLayout());
        add(resourcesLabel, BorderLayout.NORTH);

        var buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1, 0, 5));

        var extendButton = new JButton("Расширить");
        extendButton.addActionListener(e -> field.setState(new ExtendAnthill()));
        buttons.add(extendButton, BorderLayout.NORTH);

        var attackButton = new JButton("Атаковать");
        attackButton.addActionListener(e -> field.setState(new Attack()));
        buttons.add(attackButton, BorderLayout.CENTER);

        var pickupButton = new JButton("Собрать");
        pickupButton.addActionListener(e -> field.setState(new PickUpResources()));
        buttons.add(pickupButton, BorderLayout.SOUTH);

        add(buttons, BorderLayout.CENTER);

    }

    @Override
    public void paint(Graphics g) {
        resourcesLabel.setText("Ресурсы: 1");
    }
}
