package client.domain.map;

public class MapContainer {

    private final Map parts;
    private final DynamicMap resources;
    public MapContainer(Map parts, DynamicMap resources) {
        this.parts = parts;
        this.resources = resources;
    }

    public Map getParts() {
        return parts;
    }

    public DynamicMap getResources() {
        return resources;
    }

}
