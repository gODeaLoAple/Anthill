package client.domain.map;

public class MapContainer {

    private final Map parts;
    private final ResourcesMap resources;
    public MapContainer(Map parts, ResourcesMap resources) {
        this.parts = parts;
        this.resources = resources;
    }

    public Map getParts() {
        return parts;
    }

    public ResourcesMap getResources() {
        return resources;
    }

}
