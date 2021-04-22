package client.ui.battle;

import client.domain.Game;
import client.domain.Player;
import client.ui.ColorProvider;
import client.ui.battle.actionStates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleField extends JPanel {

    private final Game game;
    private Point lastMousePosition = new Point();
    private PlayerActionState state = new Idle();
    private final ColorProvider colorProvider = new ColorProvider();

    public BattleField(Game game) {
        super();

        this.game = game;
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
                state.clicked(lastMousePosition, game);
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_E -> setState(new ExtendAnthill());
                    case KeyEvent.VK_A -> setState(new Attack());
                    case KeyEvent.VK_P -> setState(new PickUpResources());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

            }
        });

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
        drawAnthills(g2d);
        state.paint(lastMousePosition, g2d, game);
    }

    private void drawAnthills(Graphics2D graphics) {
        var players = game.getPlayers();
        for (var player : players) {
            var playerColor = colorProvider.getColor(player.getId());
            for (var part : player.getAnthill().getShapes()) {
                graphics.setColor(playerColor);
                graphics.fill(part.getShape());
                graphics.setColor(Color.BLACK);
                graphics.draw(part.getShape());
            }
            drawAttackPart(graphics, player);
        }
    }

    private void drawAttackPart(Graphics2D graphics, Player player) {
        player.getAnthill().getShapes().stream().filter(x -> x.getHealth() < 100).forEach(ap -> {
            var shape = ap.getShape();
            var r = 60;
            var originalClipBounds = graphics.getClipBounds();
            var rectX = (int) shape.getBounds().getCenterX() - r * Math.sqrt(3) / 2;
            var rectY = (int) shape.getBounds().getCenterY() - r;
            var health = ap.getHealth();
            var w = shape.getBounds().width;
            var h = shape.getBounds().height;


            try {
                graphics.clipRect((int) rectX,
                        rectY,
                        w,
                        (int) (h * (1 - health / 100.0)));
                graphics.setColor(Color.RED);
                graphics.fill(shape);
            } finally {
                graphics.setClip(originalClipBounds);
            }

            try {
                graphics.clipRect((int) rectX,
                        rectY + (int) (h * (1 - health / 100.0)),
                        w,
                        (int) (h * health / 100.0));
                graphics.setColor(Color.GREEN);
                graphics.fill(shape);
            } finally {
                graphics.setClip(originalClipBounds);
            }
        });
    }

}
