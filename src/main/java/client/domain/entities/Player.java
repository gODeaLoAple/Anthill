package client.domain.entities;

public class Player {
    private final int id;
    private final Anthill anthill;


    public Player(int id, Anthill anthill) {
        this.id = id;
        this.anthill = anthill;
    }

    public int getId() {
        return id;
    }

    public Anthill getAnthill() {
        return this.anthill;
    }

}

