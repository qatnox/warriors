package com.company.characters;

public class Defender extends Warrior{
    private int health = 60;
    private int maxHealth = 60;
    private int attack = 3;
    private int defense = 2;

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
    public void receiveDamage(int damage) {
        setHealth(getHealth()-Math.max(0, (damage-defense)));

    }

    @Override
    public int getMaxHealth() {
        return maxHealth;

    }
}
