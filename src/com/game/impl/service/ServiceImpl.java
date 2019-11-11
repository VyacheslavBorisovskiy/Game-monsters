package com.game.impl.service;

import com.game.api.model.Character;
import com.game.api.model.actor.Actor;
import com.game.api.model.actor.Movable;
import com.game.api.service.Service;
import com.game.impl.model.monster.Skeleton;
import com.game.impl.model.monster.Zombie;
import com.game.impl.model.user.Angel;
import com.game.impl.model.user.Warrior;

import java.awt.*;
import java.util.Random;

public class ServiceImpl implements Service {
    public static final int MAX_MOVE_LENGTH = 5;
    private static final int ANGEL_HEALTH = 60;
    private static final int ANGEL_POWER = 15;
    private static final int WARRIOR_HEALTH = 65;
    private static final int ANGEL_MOVE_DISTANCE = 20;
    private static final int ANGEL_ATTACK_DISTANCE = 4;
    private static final int WARRIOR_ATTACK_DISTANCE = 3;
    private static final int WARRIOR_MOVE_DISTANCE = 5;
    private static final int WARRIOR_POWER = 20;
    private static final int SKELETON_POWER = 9;
    private static final int ZOMBIE_POWER = 11;
    private static final int SKELETON_HEALTH = 10;
    private static final int ZOMBIE_HEALTH = 15;
    private static final int MAX_MONSTERS = 5;
    private Character user;
    private Actor[] monsters;
    private GameProcessor processor;

    public ServiceImpl() {
        this.monsters = generateRandomMonsters();
        this.user = generateRandomUserCharacter();
        this.processor = this.new GameProcessor();
        this.processor.printStat();
    }

    private static final Character generateRandomUserCharacter() {
        Character result;
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            result = new Angel(ANGEL_HEALTH, ANGEL_POWER, ANGEL_MOVE_DISTANCE, ANGEL_ATTACK_DISTANCE);
        } else {
            result = new Warrior(WARRIOR_HEALTH, WARRIOR_POWER, WARRIOR_MOVE_DISTANCE, WARRIOR_ATTACK_DISTANCE);
        }
        if (result.canMove()) { // to check if our result is what we need.
            ((Movable) result).moveTo(new Point(0, 0));
        }
        return result;
    }

    private static final Actor[] generateRandomMonsters() {
        Random random = new Random();
        Actor[] monsters = new Actor[random.nextInt(MAX_MONSTERS) + MAX_MONSTERS];
        for (int i = 0; i < monsters.length; i++) {
            Actor monster;
            if (random.nextInt(2) == 0) {
                monster = new Skeleton(SKELETON_HEALTH, SKELETON_POWER, random.nextInt(3) + 1);
            } else {
                monster = new Zombie(ZOMBIE_HEALTH, ZOMBIE_POWER, random.nextInt(8) + 1);
            }
            monsters[i] = monster;
        }
        return monsters;
    }

    @Override
    public Character getUserCharacter() {
        return this.user;
    }

    @Override
    public Actor[] getMonsters() {
        return this.monsters;
    }

    @Override
    public void play() {
        if (user.getHealth() > 0) {
            this.processor.processNextStep();
        } else {
            endGame();
            System.exit(0);
        }
    }

    public void endGame() {
        System.out.println("Your character is dead. Game over");
    }

    private class GameProcessor {
        private Movable[] movableActors = new Movable[monsters.length + 1];

        public GameProcessor() {
            Random random = new Random();
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i].canMove()) {
                    int x = random.nextInt(MAX_MOVE_LENGTH);
                    int y = random.nextInt(MAX_MOVE_LENGTH);
                    Movable m = (Movable) monsters[i];
                    m.moveTo(new Point(x, y));
                    movableActors[i] = m;
                }
            }
            movableActors[movableActors.length - 1] = (Movable) user;
        }

        public void processNextStep() {
            move();
            fight();
            printStat();
        }

        private final void move() {
            Random random = new Random();
            for (int i = 0; i < movableActors.length; i++) {
                Movable actor = movableActors[i];
                int sign = random.nextInt(2) == 0 ? -1 : 1;
                actor.moveTo(new Point(actor.getCoordinates().x + random.nextInt(MAX_MOVE_LENGTH) * sign,
                        actor.getCoordinates().y + random.nextInt(MAX_MOVE_LENGTH) * sign));
            }
        }

        private final void fight() {
            Random random = new Random();
            for (int i = 0; i < monsters.length; i++) {
                if (((Movable) user).getCoordinates().distance(((Movable) monsters[i]).getCoordinates().getX(),
                        ((Movable) monsters[i]).getCoordinates().getY()) <= ((Actor) user).getAttackDistance()) {
                    while (user.getHealth() > 0 && monsters[i].getHealth() > 0) {
                        System.out.println(user.getClass().getName() + " fights with " + monsters[i].getClass().getName());
                        if (random.nextInt(2) == 0) {
                            ((Actor) user).attack(monsters[i]);
                            System.out.println("user health " + user.getHealth());
                            System.out.println("monster health " + monsters[i].getHealth());
                        } else {
                            monsters[i].attack(user);
                            System.out.println("user health " + user.getHealth());
                            System.out.println("monster health " + monsters[i].getHealth());
                        }
                    }
                }
            }
        }

        private final void printStat() {
            for (int i = 0; i < movableActors.length; i++) {
                Actor actor = (Actor) movableActors[i];
                System.out.println(actor.getClass().getName()
                        + " health="
                        + actor.getHealth()
                        + " position="
                        + movableActors[i].getCoordinates()
                );
            }
        }
    }
}