package com.company.characters;

public class Lancer extends Warrior{
    private int attack = 6;
    private int health = 50;
    private final int maxHealth = 50;
    private final int lancing = 50;

    public int getLancing() {

        return lancing;

    }

    @Override
    public int getAttack(){

        return attack;

    }

    @Override
    public int getHealth(){

        return health;

    }

    @Override
    public void setHealth(int health) {

        this.health = Math.min(getMaxHealth(), health);
    }

    @Override
    public boolean isAlive(){

        return health > 0;
    }

    @Override
    public int getMaxHealth() {

        return maxHealth;
    }

    @Override
    public void hit(Warrior opponent) {

        int healthBeforeHit = opponent.getHealth();

        super.hit(opponent);
        int dealtDamage = Math.max(0, healthBeforeHit-opponent.getHealth());

        if(opponent.getWarriorBehind() != null) {

            opponent.getWarriorBehind().receiveDamage(dealtDamage*getLancing()/100);
        }
    }
}
