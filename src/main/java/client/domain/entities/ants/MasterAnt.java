package client.domain.entities.ants;

import java.awt.*;

public class MasterAnt extends Ant{

    public MasterAnt(Point point, int health) {
        super(point, health);
    }

    public void attack(){

    }

    public void protectColony(){

    }
    private final int health = 300;
    private final int speed = 15;
    private final int id = 1;
}
