package client.domain;

import client.domain.entities.Player;

import java.awt.*;

public interface ResourcePickupObserver {
    void onResourcePickup(Player player, Shape res);
}
