package com.game.api.model;

public interface Character {
    int getHealth();
    void modifyHealth(int value);
    void doAction(CharacterAction action);
    boolean canDoAction(CharacterAction action);
    boolean canMove();
    boolean isNpc();
}
