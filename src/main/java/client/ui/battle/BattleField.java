package client.ui.battle;

import client.domain.Game;
import client.domain.entities.Player;
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
            var rectangle = shape.getBounds();
            var health = ap.getHealth();
            paintHexagon(graphics, rectangle, shape, Color.RED, 0, 1 - health / 100.0);
            paintHexagon(graphics, rectangle, shape, Color.BLUE,
                    (int) (rectangle.height * (1 - health / 100.0)), health / 100.0);
        });
    }

    private static void paintHexagon(Graphics2D graphics,
                                     Rectangle rectangle, Shape shape,
                                     Color color, double k1, double k2) {
        var originalClipBounds = graphics.getClipBounds();
        var rectX = (int) (rectangle.getCenterX() - Program.hexRadius * Math.sqrt(3) / 2);
        var rectY = (int) (rectangle.getCenterY() - Program.hexRadius);
        try {
            graphics.clipRect(rectX,
                    (int) (rectY + (rectangle.height * k1)),
                    rectangle.width,
                    (int) (rectangle.height * k2));
            graphics.setColor(color);
            graphics.fill(shape);
        } finally {
            graphics.setClip(originalClipBounds);
        }
    }

    private void drawResources(Graphics2D graphics) {
        for (var shape : game.getResourcesMap().getShapes())
        {
            graphics.setColor(Color.CYAN);
            graphics.fill(shape);
        }
    }

}
