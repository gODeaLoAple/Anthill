package client.ui.battle;

import client.domain.Game;
import client.net.NetDispatcher;
import client.ui.battle.actionStates.*;
import client.ui.battle.drawers.Drawer;
import client.ui.battle.drawers.ResourceDrawer;
import client.ui.battle.drawers.forEachPlayer.AntDrawer;
import client.ui.battle.drawers.forEachPlayer.AnthillsDrawer;
import client.ui.battle.drawers.forEachPlayer.ForEachPlayerDrawer;
import client.ui.battle.drawers.forEachPlayer.ForEachPlayerDrawerContainer;
import client.ui.battle.utils.ColorProvider;
import client.ui.battle.utils.ImageProvider;
import client.ui.battle.utils.ShapeFiller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.TimerTask;

public class BattleField extends JPanel {

    private final Game game;
    private Point lastMousePosition = new Point();
    private PlayerActionState state;
    private final ImageProvider imageProvider;
    private final NetDispatcher dispatcher;
    private final ColorProvider colorProvider = new ColorProvider();
    private final ShapeFiller filler;

    private final Drawer[] drawers;
    private Point2D.Float scale = new Point2D.Float(1, 1);

    public BattleField(Game game, ShapeFiller filler, ImageProvider imageProvider, NetDispatcher dispatcher) {
        super();
        this.game = game;
        state = new Idle(game, null);
        this.filler = filler;
        this.imageProvider = imageProvider;
        this.dispatcher = dispatcher;
        setFocusable(true);
        startTimer();
        new Thread(this::addListeners).start();
        drawers = createDrawers();
    }



    private void addListeners() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                var point = e.getPoint();
                var scale = getScale();
                lastMousePosition = new Point((int)(point.x / scale.x), (int)(point.y / scale.y));
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
                    case KeyEvent.VK_E -> setState(new ExtendAnthill(game, dispatcher));
                    case KeyEvent.VK_A -> setState(new Attack(game, dispatcher));
                    case KeyEvent.VK_P -> setState(new PickUpResources(game, dispatcher));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updateScale();
            }
        });
    }


    private void startTimer() {
        var timer = new java.util.Timer("Timer");
        var timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!game.isGameOver()) {
                    game.step();
                }
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
                        new AntDrawer(game, imageProvider, colorProvider)
                })
        };
    }

    public void setState(PlayerActionState state) {
        this.state = state;
    }

    @Override
    public void paint(Graphics g) {
        var g2d = (Graphics2D) g;
        var clip = g.getClip().getBounds();
        g2d.clearRect(clip.x, clip.y, clip.width, clip.height);
        var scale = getScale();
        g2d.scale(scale.x, scale.y);
        if (game.isGameOver()) {
            g2d.drawString("Победитель: " + game.getMainPlayer().getId(), 100, 100);
        }
        for (var drawer : drawers)
           drawer.draw(g2d);
        state.paint(lastMousePosition, g2d);
    }

    public void updateScale() {
        var size = getParent().getSize();
        var mapSize = game.getResourcesMap().getSize();
        var dx = size.width / (float)mapSize.width;
        var dy = size.height / (float)mapSize.height;
        scale = new Point.Float(dx, dy);
    }

    public Point.Float getScale() {
        return scale;
    }

}

