package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item {

    private int defenseBonus;

    public Shield(Cell cell, int defenseBonus) {
        super(cell, "Shield", defenseBonus);
    }

    @Override
    public String getTileName() {
        return "buckler";
    }
}
