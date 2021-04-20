package client.domain;

import client.domain.Anthill;

import java.awt.*;

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

    public void attack(Anthill anthill, Shape target){
        var shapes = anthill.getShapes();
        for (var shape: shapes){
            if (shape.hashCode() == target.hashCode()){
                System.out.println("Jopa");
            }
        }
    }

    public Anthill getAnthill() {
        return this.anthill;
    }

}

