package com.company;

/*

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Supplier;

*/

class Main {
    public static void main(String[] args) {

    }
}

/*class Battle {
    public static boolean fight(Warrior w1, Warrior w2) {
        while (w1.isAlive() && w2.isAlive()) {
            w1.hit(w2);
            System.out.println("Warrior 1 hit! War1 hp: " + w1.getHealth() + " War2 hp: " + w2.getHealth());
            if (w2.isAlive()) {
                w2.hit(w1);
                System.out.println("Warrior 1 hit! War1 hp: " + w1.getHealth() + " War2 hp: " + w2.getHealth());
            }
            else break;
        }
        return w1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        while(army1!=null && army2!=null && !army1.troops.isEmpty() && !army2.troops.isEmpty()) {

            if(army1.troops.peek() instanceof WithLance || army2.troops.peek() instanceof WithLance) {
                var w1 = army1.troops.poll();
                var w2 = army2.troops.poll();
                while (w1.isAlive() && w2.isAlive()) {
                    if(w1 instanceof WithLance) ((WithLance) w1).hit(w2, army2.troops.peek());
                    else w1.hit(w2);
                    System.out.print("Warrior 1 hit! War1 hp: " + w1.getHealth() + " War2 hp: " + w2.getHealth());
                    if(army2.troops.peek() != null) System.out.println(" War3 hp: " + army2.troops.peek().getHealth());
                    else System.out.println();

                    if (!w2.isAlive()) break;
                    if(w2 instanceof WithLance)((WithLance)w2).hit(w1, army1.troops.peek());
                    else w2.hit(w1);
                    System.out.print("Warrior 2 hit! War1 hp: " + w1.getHealth() + " War2 hp: " + w2.getHealth());
                    if(army1.troops.peek() != null) System.out.println(" War3 hp: " + army1.troops.peek().getHealth());
                    else System.out.println();
                }
                if(w1.isAlive()) army1.troops.addFirst(w1);
                else if(w2.isAlive()) army2.troops.addFirst(w2);
            }
            else {
                if(fight(army1.troops.peek(), army2.troops.peek())) {
                    army2.troops.remove();
                }
                else army1.troops.remove();
            }

        }

        return army1.isEmpty();
    }
}



interface HasHealth {
    int getHealth();
    default boolean isAlive() {
        return getHealth() > 0;
    }
}

interface CanReceiveDamage extends HasHealth {
    void receiveDamage(int damage);
}

interface CanAttack {
    int getAttack();

    default void hit(CanReceiveDamage w) {
        w.receiveDamage(getAttack());
    }
}

interface Warrior extends CanReceiveDamage, CanAttack, HasHealth {
    static Warrior newWarrior() {
        return new WarriorImpl(50, 5);
    }
    static Warrior newKnight() {
        return new WarriorImpl(50, 7);
    }
    static Warrior newDefender() {
        return new WithDefence(new WarriorImpl(60, 3), 2);
    }
    static Warrior newVampire() {
        return new WithVampirism(new WarriorImpl(40, 4), 50);
    }
    static Warrior newVampireDefender() {
        return new WithVampirism(newDefender(), 50);
    }
    static Warrior newLancer() {return new WithLance(new WarriorImpl(50, 6), 50);}
}

class WarriorImpl implements Warrior {
    private int health;
    private final int attack;

    WarriorImpl(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    @Override
    public int getAttack() {
        return attack;
    }
}

abstract class WarriorDecorator implements Warrior {
    private Warrior warrior;

    protected Warrior getKernel() {
        if (warrior instanceof WarriorDecorator decorator) {
            return getKernel();
        } else {
            return warrior;
        }
    }

    public WarriorDecorator(Warrior warrior) {
        this.warrior = warrior;
    }
    @Override
    public int getHealth() {
        return warrior.getHealth();
    }

    @Override
    public int getAttack() {
        return warrior.getAttack();
    }
    @Override
    public void receiveDamage(int damage) {
        warrior.receiveDamage(damage);
    }

    @Override
    public void hit(CanReceiveDamage w) {
        Warrior.super.hit(w);
    }
}

interface HasDefence {
    int getDefence();
}

class WithDefence extends WarriorDecorator implements HasDefence {

    private final int defence;

    public WithDefence(Warrior warrior, int defence) {
        super(warrior);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    @Override
    public void receiveDamage(int damage) {
        var reducedDamage = Math.max(0, damage - getDefence());
        super.receiveDamage(reducedDamage);
    }
}

interface HasVampirism {
    int getVampirism();
}

interface HasLance extends CanAttack{
    int getLance();

    default void hit(CanReceiveDamage w1, CanReceiveDamage w2) {
        hit(w1);
        w2.receiveDamage(getAttack()*(getLance()/100));
    }
}

class WithVampirism extends WarriorDecorator implements HasVampirism {
    private final int vampirism;
    public WithVampirism(Warrior warrior, int vampirism) {
        super(warrior);
        this.vampirism = vampirism;
    }

    public int getVampirism() {
        return vampirism;
    }

    @Override
    public void hit(CanReceiveDamage w) {
        var healthBefore = w.getHealth();
        super.hit(w);
        var healthAfter = w.getHealth();
        var dealtDamage = Math.max(0, healthBefore - healthAfter);
        int selfHealing = dealtDamage * getVampirism() / 100;
        var warrior = (WarriorImpl) getKernel();
        warrior.setHealth(warrior.getHealth() + selfHealing);

    }
}

class WithLance extends WarriorDecorator implements HasLance {
    private final int lance;

    public WithLance(Warrior warrior, int lance) {
        super(warrior);
        this.lance = lance;
    }

    @Override
    public int getLance() {
        return lance;
    }

    public void hit(Warrior w1, Warrior w2) {
        w1.receiveDamage(getAttack());
        if(w2 != null) w2.receiveDamage(getAttack()*getLance()/100);
    }
}*/
