package com.company.characters;

public class Warrior {
    private int health = 50;
    private int attack = 5;
    private final int maxHealth = 50;
    private Warrior warriorBehind = null;
    private Warrior warriorBefore = null;

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(getMaxHealth(), health);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack(){
        return attack;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public Warrior getWarriorBefore() {
        return warriorBefore;
    }

    public Warrior getWarriorBehind() {
        return warriorBehind;
    }

    public void setWarriorBehind(Warrior warriorBehind) {
        this.warriorBehind = warriorBehind;
    }

    public void setWarriorBefore(Warrior warriorBefore) {
        this.warriorBefore = warriorBefore;
    }

    public void hit(Warrior opponent) {
        opponent.receiveDamage(getAttack());
        processCommand(new HitHappened() { }, this);
    }

    public void getHealed(int healPoints) {
        setHealth(getHealth()+healPoints);
    }

    public void receiveDamage(int damage) {
        int receivedDamage = Math.max(0, damage);
        setHealth(getHealth() - receivedDamage);
    }

    protected void processCommand(Command command, Warrior sender) {
        var next = getWarriorBehind();
        if(next != null) {
            next.processCommand(command, sender);
        }
    }
}
