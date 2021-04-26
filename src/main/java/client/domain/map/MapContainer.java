package client.domain.map;

public class MapContainer {

    private final Map parts;
    private final ResourcesMap resources;
    private final AntsMap ants;

    public MapContainer(Map parts, ResourcesMap resources, AntsMap ants) {
        this.parts = parts;
        this.resources = resources;
        this.ants = ants;
    }

    public Map getParts() {
        return parts;
    }

    public ResourcesMap getResources() {
        return resources;
    }


    public AntsMap getAnts() {
        return ants;
    }
}
