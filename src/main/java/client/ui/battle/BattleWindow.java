package client.ui.battle;

import client.domain.Game;
import client.ui.PanelsSwitcher;
import client.ui.battle.utils.ImageProvider;
import client.ui.battle.utils.HexagonFiller;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BattleWindow extends JPanel {


    public BattleWindow(PanelsSwitcher switcher, Game game, ImageProvider imageProvider) throws IOException {
        super();
        setLayout(new BorderLayout());

        var field = new BattleField(game, new HexagonFiller(Color.RED, Color.BLUE), imageProvider);
        var info = new BattleInfo(switcher, game, field);

        add(field, BorderLayout.CENTER);
        add(info, BorderLayout.WEST);
    }

}




