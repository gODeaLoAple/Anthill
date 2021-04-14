package client;

import client.Entities.Resources;

public class Anthill {
    private final AnthillPlace place;
    private Resources resources;

    public Anthill(AnthillPlace startOwnLand) {
        place = startOwnLand;
    }

    public AnthillPlace getPlace() {
        return place;
    }

}
