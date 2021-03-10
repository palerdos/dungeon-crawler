package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item {

    public Shield(Cell cell) {
        super(cell, "Shield", 1);
        // TODO random number & name generator
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
