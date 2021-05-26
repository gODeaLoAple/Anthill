package client.domain.entities;

import client.domain.entities.anthill.Anthill;

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

