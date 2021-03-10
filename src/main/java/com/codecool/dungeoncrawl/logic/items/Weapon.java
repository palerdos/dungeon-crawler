package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item{

    private int strengthBonus;

    public Weapon(Cell cell, int strengthBonus) {
        super(cell, "Weapon", strengthBonus);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
