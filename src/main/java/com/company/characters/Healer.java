package com.company.characters;

public class Healer extends Warrior  {
    private int health = 60;
    private final int maxHealth = 60;
    private int attack = 0;
    private int healing = 2;

    @Override
    public void hit(Warrior opponent) {

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
    public int getMaxHealth(){
        return maxHealth;

    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(getMaxHealth(), health);

    }

    @Override
    public boolean isAlive(){
        return health > 0;

    }

    public void heal(Warrior war) {
        war.getHealed(getHealing());

    }

    public int getHealing() {
        return healing;

    }

    @Override
    protected void processCommand(Command command, Warrior sender) {
        heal(getWarriorBefore());
        super.processCommand(command, sender);
    }
}
