package com.company;

import com.company.characters.*;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void name(){

        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();

        assert Battle.fight(chuck, bruce);
        assert !Battle.fight(dave, carl);
        assert chuck.isAlive();
        assert !bruce.isAlive();
        assert carl.isAlive();
        assert !dave.isAlive();
        assert !Battle.fight(carl, mark);
        assert !carl.isAlive();
        assert !Battle.fight(bob, mike);
        assert Battle.fight(lancelot, rog);
        assert !Battle.fight(eric, richard);
        assert Battle.fight(ogre, adam);
        assert Battle.fight(freelancer, vampire);
        assert freelancer.isAlive();
        assert freelancer.getHealth() == 14;
        priest.heal(freelancer);
        assert freelancer.getHealth() == 16;

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        var Army3 = new Army();
        Army3.addUnits(Warrior::new, 1);
        Army3.addUnits(Lancer::new, 1);
        Army3.addUnits(Healer::new, 1);
        Army3.addUnits(Defender::new, 2);

        var Army4 = new Army();
        Army4.addUnits(Vampire::new, 3);
        Army4.addUnits(Warrior::new, 1);
        Army4.addUnits(Healer::new, 1);
        Army4.addUnits(Lancer::new, 2);

        var Army5 = new Army();
        Army5.addUnits(Warrior::new, 10);

        var Army6 = new Army();
        Army6.addUnits(Warrior::new, 6);
        Army6.addUnits(Lancer::new, 5);

        assert !Battle.fight(myArmy, enemyArmy);
        assert Battle.fight(Army3, Army4);
        assert !Battle.straightFight(Army5, Army6);
    }
}