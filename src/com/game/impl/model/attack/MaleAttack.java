package com.game.impl.model.attack;

import com.game.api.model.AttackType;
import com.game.api.model.Character;

public class MaleAttack extends BaseAttack {

    public MaleAttack(Character targetCharacter, int attackPower) {
        super(targetCharacter, attackPower);
    }

    @Override
    public AttackType getAttackType() {
        return AttackType.MALE;
    }
}
