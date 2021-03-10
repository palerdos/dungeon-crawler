package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Actor{
    public Boss(Cell cell) {
        super(cell);
        this.type = "boss";
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}
