package client;

public class Anthill {
    private final AnthillPlace place;
    private Resources resources;

    public Anthill(AnthillPlace startOwnLand, Resources resources) {
        place = startOwnLand;
        this.resources = resources;
    }

    public AnthillPlace getPlace() {
        return place;
    }

    public Resources getResources() {
        return resources;
    }

}
