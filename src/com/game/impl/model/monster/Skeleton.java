package com.game.impl.model.monster;

import com.game.api.model.Character;
import com.game.api.model.CharacterAction;
import com.game.impl.model.BaseActor;
import com.game.impl.model.attack.MaleAttack;

public class Skeleton extends BaseActor {

    public Skeleton(int health, int power, int maxMoveDistance) {
        super(health, power, maxMoveDistance);
    }

    @Override
    protected CharacterAction getAttack(Character character) {
        return new MaleAttack(character, attackPower);
    }
}
