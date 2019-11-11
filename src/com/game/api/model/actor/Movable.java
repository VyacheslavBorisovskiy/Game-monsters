package com.game.api.model.actor;

import java.awt.*;

public interface Movable {
    void moveTo(Point point);
    boolean canMoveTo(Point point);
    Point getCoordinates();
}
