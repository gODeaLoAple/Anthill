package client.domain.entities.ants;

import java.awt.*;
import java.io.Serializable;

public class SlaveAnt extends Ant implements Serializable {

    public SlaveAnt(Point point, int health) {
        super(point, health);
    }
}
