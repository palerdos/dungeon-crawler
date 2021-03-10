package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.util.RandomHelper;

public class Weapon extends Item{

    public Weapon(Cell cell) {
        super(cell, "Weapon", RandomHelper.getRandomNumber(2, 5));
        // TODO random name generator
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
