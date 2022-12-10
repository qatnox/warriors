package com.company;

import com.company.characters.Healer;
import com.company.characters.Warrior;

public class Battle {
    public static boolean fight(Warrior w1, Warrior w2) {
        while (true) {
            if(!w1.isAlive()) break;
            w1.hit(w2);

            if (!w2.isAlive()) break;
            w2.hit(w1);
        }
        return w1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        if(army1 == null || army2 == null) return false;
        if(army1.isEmpty() || army2.isEmpty()) return army1.isEmpty();

        while(!army1.isEmpty() && !army2.isEmpty()) {
            if(army1.troops.peekFirst() instanceof Healer && army2.troops.peekFirst() instanceof Healer) {
                army1.troops.removeFirst();
                army2.troops.removeFirst();
            }
            else {
                assert army1.troops.peekFirst() != null;
                if(fight(army1.troops.peekFirst(), army2.troops.peekFirst())) {
                    army2.troops.removeFirst();
                }
                else {
                    army1.troops.removeFirst();
                }
            }

        }

        return !army1.isEmpty();
    }

    public static boolean straightFight(Army army1, Army army2) {
        if(army1 == null || army2 == null) return false;
        if(army1.isEmpty()) return army1.isEmpty();
        if(army2.isEmpty())return army1.isEmpty();

        while(!army1.isEmpty() && !army2.isEmpty()) {
            int i = 0;
            var size = Math.min(army1.getSize(), army2.getSize());
            while (i < size) {
                if(army1.troops.get(i) instanceof Healer && army2.troops.get(i) instanceof Healer) {
                    army1.troops.get(i).setHealth(0);
                    army2.troops.get(i).setHealth(0);
                }

                army1.troops.get(i).setWarriorBehind(null);
                army1.troops.get(i).setWarriorBefore(null);
                army2.troops.get(i).setWarriorBehind(null);
                army2.troops.get(i).setWarriorBefore(null);

                fight(army1.troops.get(i), army2.troops.get(i));
                i++;
            }

            army1.troops.removeIf(war -> ! war.isAlive());
            army2.troops.removeIf(war -> ! war.isAlive());
        }

        return !army1.isEmpty();
    }
}
