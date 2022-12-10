package com.company.characters;

public class Vampire extends Warrior{
    private int health = 40;
    private final int maxHealth = 40;
    private int attack = 4;
    private final int vampirism = 50;

    @Override
    public int getMaxHealth() {

        return maxHealth;
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

        this.health = Math.min(getMaxHealth(), health);;
    }

    @Override
    public boolean isAlive(){

        return health > 0;
    }

    public int getVampirism() {

        return vampirism;
    }

    @Override
    public void hit(Warrior opponent) {

        var healthBefore = opponent.getHealth();

        super.hit(opponent);

        var healthAfter = opponent.getHealth();
        var dealtDamage = Math.max(0, healthBefore - healthAfter);

        int selfHealing = dealtDamage * getVampirism() / 100;
        getHealed(selfHealing);
    }
}
