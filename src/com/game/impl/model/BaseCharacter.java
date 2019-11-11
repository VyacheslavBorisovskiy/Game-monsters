package com.game.impl.model;

import com.game.api.model.Character;
import com.game.api.model.CharacterAction;
import com.game.api.model.Npc;
import com.game.api.model.actor.Flying;
import com.game.api.model.actor.Movable;
import com.game.api.model.actor.Swimming;

public abstract class BaseCharacter implements Character {
    private int health;

    public BaseCharacter(int health) {
        this.health = health;
    }

    @Override
    public final int getHealth() {
        return health;
    }

    @Override
    public final void modifyHealth(int power) {
        health -= power;
        if (health < 0) health = 0;
    }

    @Override
    public final void doAction(CharacterAction action) {
        if (canDoAction(action) && health > 0) {
            action.doAction();
        }
    }

    @Override
    public final boolean canMove() {
        return Movable.class.isAssignableFrom(this.getClass());
    }

    @Override
    public final boolean isNpc() {
        return Npc.class.isAssignableFrom(this.getClass());
    }

    protected boolean canFly(Object o) {
        return Flying.class.isAssignableFrom(o.getClass());
    }

    protected boolean canSwim(Object o) {
        return Swimming.class.isAssignableFrom(o.getClass());
    }
}
