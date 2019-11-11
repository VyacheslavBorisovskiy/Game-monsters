package com.game.impl.model.user;

import com.game.api.model.Character;
import com.game.api.model.CharacterAction;
import com.game.impl.model.BaseActor;
import com.game.impl.model.attack.MaleAttack;

public class Warrior extends BaseActor {

    public Warrior(int health, int power, int maxMoveDistance, int attackDistance) {
        super(health, power, maxMoveDistance, attackDistance);
    }

    @Override
    protected CharacterAction getAttack(Character character) {
        return new MaleAttack(character, attackPower);
    }

}
