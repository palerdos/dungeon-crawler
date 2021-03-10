package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item{

    public Weapon(Cell cell) {
        super(cell, "Weapon", 3);
        // TODO random number & name generator
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
