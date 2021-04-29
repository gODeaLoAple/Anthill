package client.domain.entities.ants;

import java.awt.*;

public class SlaveAnt extends Ant{

    public SlaveAnt(Point point, int health) {
        super(point, health);
    }

    public void collectFood(){

    }
    private final int health = 100;
    private final int speed = 10;
    private final int id = 0;
}
