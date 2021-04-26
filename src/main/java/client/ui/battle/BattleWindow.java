package client.ui.battle;

import client.domain.Game;

import javax.swing.*;
import java.awt.*;

public class BattleWindow extends JPanel {


    public BattleWindow(Game game) {
        super();
        setLayout(new BorderLayout());

        var field = new BattleField(game,  new ShapeFiller(Color.RED, Color.BLUE));
        var info = new BattleInfo(game, field);

        add(field, BorderLayout.CENTER);
        add(info, BorderLayout.WEST);
    }

}




