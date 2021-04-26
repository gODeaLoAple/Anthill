package client.ui.battle;

import client.domain.Game;
import client.ui.battle.actionStates.*;
import client.ui.battle.drawers.AnthillsDrawer;
import client.ui.battle.drawers.Drawer;
import client.ui.battle.drawers.ResourceDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleField extends JPanel {

    private final Game game;
    private Point lastMousePosition = new Point();
    private PlayerActionState state;
    private final ColorProvider colorProvider = new ColorProvider();
    private final IShapeFiller filler;

    private final Drawer[] drawers;

    public BattleField(Game game, IShapeFiller filter) {
        super();
        this.filler = filter;
        this.game = game;
        state = new Idle(game);
        setFocusable(true);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                repaint();
                lastMousePosition = e.getPoint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state.clicked(lastMousePosition);
                repaint();
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

    private Drawer[] createDrawers() {
        return new Drawer[] {
            new AnthillsDrawer(game, colorProvider, filler),
            new ResourceDrawer(game),
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
        for (var drawer : drawers)
            drawer.draw(g2d);
        state.paint(lastMousePosition, g2d);
    }

}

