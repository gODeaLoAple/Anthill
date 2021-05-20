package client.ui.battle.utils;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ColorProvider {

    private static final Map<Integer, Color> idToColor = new HashMap<>() {{
        put(0, Color.GREEN);
        put(1, Color.BLUE);
        put(2, Color.RED);
    }};

    public Collection<Color> getAllColors() {
        return idToColor.values();
    }

    public Color getColor(int id) {
        return idToColor.getOrDefault(id, Color.BLACK);
    }

}
