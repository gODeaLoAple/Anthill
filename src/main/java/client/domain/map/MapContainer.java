package client.domain.map;

import client.ui.battle.utils.HexagonalMap;

import java.io.Serializable;

public class MapContainer implements Serializable {

    private Map parts;
    private final ResourcesMap resources;

    public MapContainer(Map parts, ResourcesMap resources) {
        this.parts = parts;
        this.resources = resources;
    }

    public Map getParts() {
        return parts;
    }

    public HexagonalMap getHexagonalMap(){
        return (HexagonalMap) parts;
    }

    public ResourcesMap getResources() {
        return resources;
    }

    public void setParts(Map map) {
        this.parts = map;
    }
}
