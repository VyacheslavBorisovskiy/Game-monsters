package com.game.api.model.actor;

import com.game.api.model.Character;

public interface Actor extends Character {
    void attack(Character character);
    int getAttackDistance();
}
