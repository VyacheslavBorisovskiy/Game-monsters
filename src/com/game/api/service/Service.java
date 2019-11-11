package com.game.api.service;

import com.game.api.model.Character;
import com.game.api.model.actor.Actor;

public interface Service {
    Character getUserCharacter();
    Actor[] getMonsters();
    void play();
}
