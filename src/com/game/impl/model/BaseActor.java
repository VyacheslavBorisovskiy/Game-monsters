package com.game.impl.model;

import com.game.api.model.Character;
import com.game.api.model.CharacterAction;
import com.game.api.model.actor.Actor;
import com.game.api.model.actor.Movable;

import java.awt.*;

public abstract class BaseActor extends BaseCharacter implements Actor, Movable {
    protected int attackPower;
    private int maxMoveDistance;
    private int attackDistance;
    private Point coordinates;

    public BaseActor(int health, int attackPower, int maxMoveDistance) {
        this(health, attackPower, maxMoveDistance, 1);
    }

    public BaseActor(int health, int attackPower, int maxMoveDistance, int attackDistance) {
        super(health);
        this.attackPower = attackPower;
        this.maxMoveDistance = maxMoveDistance;
        this.attackDistance = attackDistance;
    }

    @Override
    public final void attack(Character character) {
        getAttack(character).doAction();
    }

    protected abstract CharacterAction getAttack(Character character);

    @Override
    public Point getCoordinates() {
        return this.coordinates;
    }

    @Override
    public int getAttackDistance() {
        return this.attackDistance;
    }

    @Override
    public final void moveTo(Point point) {
        this.coordinates = point;
    }

    @Override
    public final boolean canMoveTo(Point point) {
        if (this.coordinates == null) {
            return true;
        } else {
            return this.coordinates.distance(point.getX(), point.getY()) < this.maxMoveDistance;
        }
    }

    @Override
    public final boolean canDoAction(CharacterAction action) {
        if (canFly(action.getActionTarget()) && !canFly(this)) {
            return false;
        }
        return true;
    }
}
