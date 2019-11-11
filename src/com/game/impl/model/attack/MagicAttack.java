package com.game.impl.model.attack;

import com.game.api.model.AttackType;
import com.game.api.model.Character;

public class MagicAttack extends BaseAttack {

    public MagicAttack(Character targetCharacter, int attackPower) {
        super(targetCharacter, attackPower);
    }

    @Override
    public AttackType getAttackType() {
        return AttackType.MAGIC;
    }
}
