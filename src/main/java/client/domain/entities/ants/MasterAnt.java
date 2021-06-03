package client.domain.entities.ants;

import java.awt.*;
import java.io.Serializable;

public class MasterAnt extends Ant implements Serializable {

    public MasterAnt(Point point, int health) {
        super(point, health);
    }

}
