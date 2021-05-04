package client.ui.battle;

import client.domain.Game;
import client.ui.battle.actionStates.*;
import client.ui.battle.drawers.Drawer;
import client.ui.battle.drawers.ResourceDrawer;
import client.ui.battle.drawers.forEachPlayer.AntDrawer;
import client.ui.battle.drawers.forEachPlayer.AnthillsDrawer;
import client.ui.battle.drawers.forEachPlayer.ForEachPlayerDrawer;
import client.ui.battle.drawers.forEachPlayer.ForEachPlayerDrawerContainer;
import client.ui.battle.utils.ColorProvider;
import client.ui.battle.utils.IShapeFiller;
import client.ui.battle.utils.ImageProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

public class BattleField extends JPanel {

    private final Game game;
    private Point lastMousePosition = new Point();
    private PlayerActionState state;
    private final ImageProvider imageProvider;
    private final ColorProvider colorProvider = new ColorProvider();
    private final IShapeFiller filler;

    private final Drawer[] drawers;

    public BattleField(Game game, IShapeFiller filter, ImageProvider imageProvider) {
        super();
        this.filler = filter;
        this.game = game;
        state = new Idle(game);
        this.imageProvider = imageProvider;
        setFocusable(true);
        startTimer();

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                lastMousePosition = e.getPoint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state.clicked(lastMousePosition);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_E -> setState(new ExtendAnthill(game));
                    case KeyEvent.VK_A -> setState(new Attack(game));
                    case KeyEvent.VK_P -> setState(new PickUpResources(game));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

            }
        });
        drawers = createDrawers();
    }


    private void startTimer() {
        var timer = new java.util.Timer("jopa");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
        timer.schedule(timerTask, 60, 10);
    }

    private Drawer[] createDrawers() {
        return new Drawer[]{
                new ResourceDrawer(game),
                new ForEachPlayerDrawerContainer(game, new ForEachPlayerDrawer[]{
                        new AnthillsDrawer(game, colorProvider, filler),
                        new AntDrawer(game, imageProvider)
                })
        };
    }

    public void setState(PlayerActionState state) {
        this.state = state;
    }

    @Override
    public void paint(Graphics g) {
        setFocusable(true);
        requestFocus();
        var g2d = (Graphics2D) g;
        var clip = g.getClip().getBounds();
        g2d.clearRect(clip.x, clip.y, clip.width, clip.height);

        if (game.isGameOver()) {
            g2d.drawString("Победитель: " + game.getMainPlayer().getId(), 100, 100);
        } else {
            game.step();
        }
        for (var drawer : drawers)
            drawer.draw(g2d);
        state.paint(lastMousePosition, g2d);
    }
}

