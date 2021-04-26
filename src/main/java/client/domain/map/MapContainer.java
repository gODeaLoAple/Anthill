package client.domain.map;

public class MapContainer {

    private final Map parts;
    private final DynamicMap resources;
    private final AntsMap ants;

    public MapContainer(Map parts, DynamicMap resources, AntsMap ants) {
        this.parts = parts;
        this.resources = resources;
        this.ants = ants;
    }

    public Map getParts() {
        return parts;
    }

    public DynamicMap getResources() {
        return resources;
    }


    public AntsMap getAnts() {
        return ants;
    }
}
