package client.ui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorProvider {

    private static final Map<Integer, Color> idToColor = new HashMap<>() {{
        put(0, Color.GREEN);
        put(1, Color.BLUE);
        put(2, Color.RED);
    }};

    public Color getColor(int id) {
        return idToColor.getOrDefault(id, Color.BLACK);
    }

}
