package client.domain.entities.anthill;

import client.domain.algorithm.ChaoticMovement;
import client.domain.entities.ants.Ant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Anthill {
    public static final int RESOURCE_FOR_EXTEND = 60;
    public static final int RESOURCE_FOR_ATTACK = 20;
    public static final int RESOURCE = 300; // NICE DELAEM
    public static final int RESOURCE_FOR_HIRE = 1000; // NICE DELAEM
    private final AnthillPlace place;
    private final Resources resources;
    private final List<Ant> ants;
    private final ChaoticMovement movement;

    public Anthill(AnthillPlace startOwnLand, Resources resources, ChaoticMovement movement) {
        place = startOwnLand;
        this.resources = resources;
        ants = new ArrayList<>();
        this.movement = movement;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public void addAnt(Ant ant) {
        ants.add(ant);
    }

    public void killAnt(Ant ant) {
        ants.remove(ant);
    }

    public Resources getResources() {
        return resources;
    }

    public boolean hasShape(Shape shape) {
        return place.hasShape(shape);
    }

    public boolean hasEnoughResourcesToExtend() {
        return resources.getCount() >= RESOURCE_FOR_EXTEND;
    }

    public boolean hasEnoughResourcesToAttack() {
        return resources.getCount() >= RESOURCE_FOR_ATTACK;
    }

    public boolean hasEnoughResourcesToHire() {
        return resources.getCount() >= RESOURCE_FOR_HIRE;
    }

    public void extend(Shape shape) {
        resources.change(-RESOURCE_FOR_EXTEND);
        place.add(shape);
    }

    public ArrayList<AnthillPart> getShapes() {
        return place.getShapes();
    }

    public AnthillPart getPartByShape(Shape shape) {
        var parts = place.getShapes();
        for (var part : parts)
            if (part.getShape().getBounds().x == shape.getBounds().x
                    && part.getShape().getBounds().y == shape.getBounds().y)
                return part;
        return null;
    }

    public void applyDamage(AnthillPart part, int damage) {
        place.applyDamage(part, damage);
    }

    public AnthillPlace getPlace() {
        return place;
    }

    public ChaoticMovement getMovement() {
        return movement;
    }

<<<<<<< HEAD
    public void battleAnt(Anthill otherPlayerAnthill) {
        for (var ant : ants) {
=======
    public void battle(Anthill otherPlayerAnthill) {
        if (movement.getLocation().distance(otherPlayerAnthill.movement.getLocation()) < movement.getRadius()) {
>>>>>>> a6425dbb695b3c8cb01359c507d73a9eeb098a16
            for (var otherAnt : otherPlayerAnthill.getAnts()) {
                var myDamage = ChaoticMovement.rnd.nextDouble() <= 0.005 ? 100 : 0;
                otherAnt.applyDamage( myDamage);
            }
        }
    }

    public void removeDeadAnts() {
        ants.removeIf(x -> x.getHealth() <= 0);
    }
}

