package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.util.RandomHelper;

public class Shield extends Item {

    public Shield(Cell cell) {
        super(cell, "Shield", RandomHelper.getRandomNumber(1, 3));
        // TODO random name generator
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
