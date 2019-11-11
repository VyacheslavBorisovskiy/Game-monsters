package com.game.impl.model.user;

import com.game.api.model.Character;
import com.game.api.model.CharacterAction;
import com.game.api.model.actor.Flying;
import com.game.impl.model.BaseActor;
import com.game.impl.model.attack.MagicAttack;

public class Angel extends BaseActor implements Flying {

    public Angel(int health, int power, int maxMoveDistance, int attackDistance) {
        super(health, power, maxMoveDistance, attackDistance);
    }

    @Override
    protected CharacterAction getAttack(Character character) {
        return new MagicAttack(character, attackPower);
    }
}
