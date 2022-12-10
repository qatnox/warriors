package com.company;

import com.company.characters.Warrior;

import java.util.LinkedList;
import java.util.function.Supplier;

public class Army {

    LinkedList<Warrior> troops = new LinkedList<>();

    public void addUnits(Supplier<Warrior> factory, int count) {
        for(int i = 0; i < count; i++)
            addUnit(factory.get());
    }

    public void addUnit(Warrior war) {
        if(!troops.isEmpty()) {
            war.setWarriorBefore(troops.getLast());
            troops.getLast().setWarriorBehind(war);
        }
        troops.addLast(war);
    }

    public boolean isEmpty() {
        return troops.isEmpty();

    }

    public int getSize() {
        return troops.size();}


}
