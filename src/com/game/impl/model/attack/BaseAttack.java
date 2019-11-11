package com.game.impl.model.attack;

import com.game.api.model.AttackType;
import com.game.api.model.Character;
import com.game.api.model.CharacterAction;

public abstract class BaseAttack implements CharacterAction {
    private Character targetCharacter;
    private int attackPower;

    public BaseAttack(Character targetCharacter, int attackPower) {
        this.targetCharacter = targetCharacter;
        this.attackPower = attackPower;
    }

    public abstract AttackType getAttackType();

    @Override
    public void doAction() {
        this.targetCharacter.modifyHealth(this.attackPower);
    }

    @Override
    public Character getActionTarget() {
        return this.targetCharacter;
    }
}
