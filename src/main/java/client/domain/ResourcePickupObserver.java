package client.domain;

import client.domain.entities.Player;

import java.awt.*;
import java.io.IOException;

public interface ResourcePickupObserver {
    void onResourcePickup(Player player, Shape res) throws IOException, ClassNotFoundException;
}
