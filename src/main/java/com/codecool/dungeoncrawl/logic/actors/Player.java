package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.setHealth(100);
        this.setAttack(10);
        this.setDefense(2);
    }

    public String getTileName() {
        return "player";
    }
}
