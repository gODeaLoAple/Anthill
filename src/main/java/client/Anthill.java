package client;

public class Anthill {
    private final AnthillPlace place;

    public Anthill(AnthillPlace startOwnLand) {
        place = startOwnLand;
    }

    public AnthillPlace getPlace() {
        return place;
    }

}
